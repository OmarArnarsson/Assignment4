package modelflight;

import java.util.ArrayList;

public class SearchResult {

    private ArrayList<ConnectedFlight> results;

    public SearchResult(ArrayList<ConnectedFlight> results) {
        this.results = results;
    }

    public ConnectedFlight findCheapestFlight(Boolean economy) {
        if(economy) {
            double lowestPrice = results.get(0).getTotalEconomyPrice();
            ConnectedFlight cheapestFlight = results.get(0);

            for(int i = 1; i < results.size(); i++) {
                if(results.get(i).getTotalEconomyPrice() < lowestPrice) {
                    lowestPrice = results.get(i).getTotalEconomyPrice();
                    cheapestFlight = results.get(i);
                }
            }
            return cheapestFlight;
        } else {
            double lowestPrice = results.get(0).getTotalBusinessPrice();
            ConnectedFlight cheapestFlight = results.get(0);

            for(int i = 1; i < results.size(); i++) {
                if(results.get(i).getTotalBusinessPrice() < lowestPrice) {
                    lowestPrice = results.get(i).getTotalBusinessPrice();
                    cheapestFlight = results.get(i);
                }
            }
            return cheapestFlight;
        }
    }

    public ConnectedFlight findQuickestFlight(Boolean economy) {
        long quickestFlightTime  = results.get(0).getTotalFlightTime().getTimeInMillis();
        ConnectedFlight quickestFlight = results.get(0);

        for(int i = 1; i < results.size(); i++) {
            if(results.get(i).getTotalFlightTime().getTimeInMillis() < quickestFlightTime) {
                quickestFlightTime  = results.get(i).getTotalFlightTime().getTimeInMillis();
                quickestFlight = results.get(i);
            }
        }
        return quickestFlight;
    }

    // aukaföll HARRI

    public int getResultCount() {
        return this.results.size();
    }

    public void printAllResults() {
        System.out.println("------------------------------------------");
        for(int i = 0; i < results.size(); i++) {
            results.get(i).printFlight();
            System.out.println("------------------------------------------");
        }
    }

    public ConnectedFlight getConnectedFlight(int i) {
        return results.get(i);
    }



    // útfæra röðunaralgorithma á þessu? - kannski nota comparable() interface sem við búum til eða bara hafa cheapest og quickest
}
