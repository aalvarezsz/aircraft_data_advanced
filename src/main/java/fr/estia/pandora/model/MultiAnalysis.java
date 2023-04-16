package fr.estia.pandora.model;

import fr.estia.pandora.multi_analyses.FlightsAnalysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiAnalysis {
    private List<Flight> flights;
    private Map<String, String> featureValues;

    public MultiAnalysis(List<Flight> flights, String targetFeature) {
        this.flights = flights;
        this.featureValues = new HashMap<String, String>();

        if(targetFeature.equals("null")) this.executeAll();
        else this.execute(targetFeature);
    }

    public void execute(String targetFeature) {
        switch (targetFeature) {
            case "cumulDuration": this.featureValues.put("cumulDuration", FlightsAnalysis.totalCumulativeDuration(flights)); break;
            case "cumulDistance": this.featureValues.put("cumulDistance", String.format("%.2f", FlightsAnalysis.totalCumulativeFlightDistance(flights))); break;

            default: break;
        }
    }

    public void executeAll() {
        this.featureValues.put("cumulDuration", FlightsAnalysis.totalCumulativeDuration(flights));
        this.featureValues.put("cumulDistance", String.format("%.2f", FlightsAnalysis.totalCumulativeFlightDistance(flights)));
    }

    public String getFeatureValue( String feature ) {
        String value = "" ;
        if( featureValues.containsKey(feature)) {
            value = featureValues.get(feature) ;
        }
        return value ;
    }

    /**
     * @return a string that represents all the feature of the multi analysis and their value
     */
    @Override
    public String toString() {
        String description = "" ;
        for (String feature : featureValues.keySet() ) {
            description += feature + ": " + featureValues.get( feature ) + "\n";
        }
        return description;
    }
}
