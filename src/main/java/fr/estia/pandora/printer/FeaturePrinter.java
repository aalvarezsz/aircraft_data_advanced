package fr.estia.pandora.printer;

import fr.estia.pandora.model.Analysis;
import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.MultiAnalysis;

public class FeaturePrinter {
	private static String targetFeature ; 

	public static void setTargetFeature( String targetFeature ) {
		FeaturePrinter.targetFeature = targetFeature;
	}

	public static void print( Analysis analysis ) {
		System.out.println( analysis.getFeatureValue( targetFeature ) );	
		// System.out.println( analysis.toString() );
	}

	public static void print(MultiAnalysis analysis ) {
		System.out.println( analysis.getFeatureValue( targetFeature ) );
		// System.out.println( analysis.toString() );
	}
}
