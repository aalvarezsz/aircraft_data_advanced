
package fr.estia.pandora.readers.commandLine;

import java.io.File;

import fr.estia.pandora.readers.commandLine.Configuration.InputMode;
import fr.estia.pandora.readers.commandLine.exceptions.InvalidOptionException;
import fr.estia.pandora.readers.commandLine.exceptions.MissingParameterException;
import fr.estia.pandora.readers.commandLine.exceptions.NoOpException;
import fr.estia.pandora.readers.commandLine.exceptions.OptionException;
import fr.estia.pandora.readers.commandLine.exceptions.UnhandledOptionException;
import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

/**
 * @author <@dhmmasson>
 * Read parameter from the command line and configure an Option object
 */
/**
 * @author Dimitri
 *
 */
/**
 * @author Dimitri
 *
 */
public class CLI {
	/** Default values if initialize is not called  */
	private static String appName = "pandora" ;
	private static int VERSION_MAJOR = 1;
	private static int VERSION_MINOR = 0;
	private static int VERSION_DELTA = 0;

	/** Default of possible options for the command line, see Options class for more details */
	static Option options[] = {
			new Option( 'o', "Output - Print only the specified feature at the end", Option.REQUIRED_ARGUMENT , "output"),
			new Option( 'b', "Batch - Select a folder location to analyse every file inside", Option.REQUIRED_ARGUMENT , "batch"),
			new Option( 'h', "Help - print this help message" , "help"),
			new Option( 'v', "Version - print the version of the application ", "version"),
			new Option( 'd', "Debug - print additional debug information on Unhandled error", "debug")
	};
	/** Resulting configuration */
	private static Configuration configuration;

	//
	/**
	 * Modify CLI information
	 * @param name app name, should stay pandora if you want to validate test
	 * @param major ( google SemVer )
	 * @param minor ( google SemVer )
	 * @param delta ( google SemVer )
	 * @param options [Optional] set of possible options
	 */
	public static void initialize(String name, int major, int minor, int delta) {
		appName = name ;
		VERSION_MAJOR = major ;
		VERSION_MINOR = minor ;
		VERSION_DELTA = delta ;
	}

	/**
	 * Read arguments provided on the command line, parse them and produce a neat Config (@see fr.estia.model.Config ) object for the rest of the apllicaiton
	 * @param arguments Arguments from the command line
	 * @return configuration of the run
	 * @throws InvalidOptionException    One option was not listed as a possible options
	 * @throws MissingParameterException One option was expecting parameters
	 * @throws UnhandledOptionException  Option is listed in the interface, but no handler where defined
	 * @throws NoOpException 			 Option is a no-op ( no further operation are expected ) e.g. version or help
	 */
	public static Configuration read( String arguments[] ) throws OptionException, MissingParameterException, UnhandledOptionException, NoOpException {
		int code;
		Getopt g = createOpt( arguments ) ;
		g.setOpterr(false); // We'll do our own error handling
		configuration = new Configuration() ;		

		while ((code = g.getopt()) != -1) {
			// System.out.println("COMMAND: " + String.valueOf((char)code));
			// System.out.println("VALUE: " + g.getOptarg());
			// System.out.println("==========================");
			switch (code) {
				case 'o':	// Feature
					configuration.setFeature(g.getOptarg()) ;
					break;
				case 'b':	// Batch folder
					configuration.setInputMode(Configuration.InputMode.batch);
					configuration.setBatchFolder(g.getOptarg());
					break;
				case 'd':	// Debug session
					configuration.setDebugSession(true) ;
					break ;
				case 'v':
					printVersion() ;
					throw new NoOpException("version") ;
				case 'h':
					printUsage();
					throw new NoOpException( "help" ) ;
				case ':': throw new MissingParameterException(String.valueOf((char)g.getOptopt()));	//Missing parameters
				case '?': throw new InvalidOptionException   (String.valueOf((char)g.getOptopt())); //Invalid Options
				default:  throw new UnhandledOptionException (String.valueOf(code));
			}
		}
		
		switch (configuration.getInputMode()) {
			case mono:
				for (int i = g.getOptind(); i < arguments.length; i++) {
					if (!arguments[i].split("\\.")[arguments[i].split("\\.").length - 1].equals("frd")) continue;
					configuration.addSource(arguments[i]);
				}
				if(configuration.getSources().size() >= 1) configuration.setInputMode(InputMode.multi);
				break;
			case batch:
				File folder = new File(configuration.getBatchFolder());
				File[] files = folder.listFiles();

				for (File file : files) {
				    if (!file.isFile() || !file.getName().split("\\.")[file.getName().split("\\.").length - 1].equals("frd")) continue;
				    configuration.addSource(configuration.getBatchFolder() + "/" + file.getName());
				}
				break;
			default: break;
		}
		
		return configuration ;
	}

	/**
	 * Print name and version number with the format name@M.m.d
	 */
	public static void printVersion() {
		System.out.println( appName + "@" + VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_DELTA  );
	}


	/**
	 *Print how to use the command line, automatically generated from provided options
	 */
	public static void printUsage() {
		System.out.println( "java -jar "+appName + " [OPTIONS] ...source" );

		System.out.println( "" ) ;
		System.out.println( "...source - path to flightRecord files or folder containing flightRecord files" ) ;

		System.out.println( "" ) ;
		System.out.print( "OPTIONS " ) ;
		System.out.println( getShortOptions().substring(1 )); //remove pesky : at beggining that is only useful to distinguish betweeen missing param and invalid options
		for (Option commandLineOption : options) {
			System.out.println( commandLineOption );
		}
	}


	/**
	 * Provide a string decribing possible short option in the gnu OPTION_STRING format
	 * @see https://www.gnu.org/software/gnuprologjava/api/gnu/getopt/Getopt.html
	 * @return option string
	 */
	private static String getShortOptions() {
		String shortOption = ":" ;
		for (Option commandLineOption : options) {
			shortOption += commandLineOption.getCode() ;
		}
		return shortOption;
	}


	/**
	 * Provide the an array of long Options for the gnu.getOpt
	 * @return
	 */
	private static LongOpt[] getLongOptions() {
		LongOpt overSizeArray[], longOptions[] ;

		//Temporary array of the size options will we wait to see how many have a long options
		overSizeArray = new LongOpt[ options.length ] ;
		int longOptionCount = 0 ;
		for (Option commandLineOption : options) {
			//Count number of long options
			if( commandLineOption.getLongOption() != null ) overSizeArray[ longOptionCount++ ] = commandLineOption.getLongOption() ;
		}

		//Final array of the right length
		longOptions = new LongOpt[ longOptionCount ] ;
		for (int i = 0 ; i < longOptionCount ; i++ ) {
			longOptions[ i ] = overSizeArray[ i ] ;
		}
		return longOptions ;
	}


	/**
	 * Create a parser for the given arguments
	 * @param arguments arguments passed on the command line
	 * @return the GetOpt parser
	 */
	private static Getopt createOpt( String arguments[] ) {
		return new Getopt( appName, arguments, getShortOptions(), getLongOptions() ) ;
	}

	public static Configuration getConfiguration() {
		return CLI.configuration ;
	}

}
