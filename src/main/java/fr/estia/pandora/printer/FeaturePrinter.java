package fr.estia.pandora.printer;

import fr.estia.pandora.model.Analysis;
import fr.estia.pandora.model.Flight;

public class FeaturePrinter {
	private static String targetFeature ; 

	public static void setTargetFeature( String targetFeature ) {
		FeaturePrinter.targetFeature = targetFeature ;
	}
	public static void print( Flight flight, Analysis analysis ) {
<<<<<<< HEAD
		System.out.println( analysis.getFeatureValue( targetFeature ) );	
		// System.out.println( analysis.toString() );
=======
		System.out.println( analysis.getFeatureValue( targetFeature ) );
		//System.out.println( analysis.toString() );
>>>>>>> 6235aa9 (milestone 1 and 2 implementation in analysis)
	}
}
