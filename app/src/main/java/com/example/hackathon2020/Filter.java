package com.example.hackathon2020;

import java.util.ArrayList;

public class Filter {

    private ArrayList<Bathroom> allBathrooms;

    /**
     * The constructor for the Filter class.
     * An ArrayList of all of the bathrooms on campus must be supplied
     * to filter from.
     *
     * @param bathrooms
     */
    public Filter(ArrayList<Bathroom> bathrooms){
        this.allBathrooms = bathrooms;
    }

    /**
     *Filters out bathrooms from the ArrayList of all possible bathrooms on campus
     * to return the bathrooms that satisfy the boolean conditions.
     * NOTE** A user can specify that they want gender neutral results to show up
     * along side male / female results by toggling settings.
     *
     *
     * @param maleStatus
     * @param femaleStaus
     * @param genderNeutralStatus
     * @param requiresWheelchair
     * @param requiresFamily
     * @return an ArrayList of Bathrooms that satisfy the filter criteria
     */
    public ArrayList<Bathroom> getApplicableBathrooms(boolean maleStatus, boolean femaleStaus, boolean genderNeutralStatus, boolean requiresWheelchair, boolean requiresFamily) {

        ArrayList<Bathroom> applicable = new ArrayList<Bathroom>();
        for(Bathroom bathroom : allBathrooms){

            if (requiresWheelchair && (! bathroom.isWheelchair())) {      //Skip if requires wheelchair access and bathroom does not have
                continue;
            }
            if (requiresFamily && (! bathroom.isFamily())) {              //Skip if needs family and bathroom is not
                continue;
            }
            if (genderNeutralStatus) {
                if (maleStatus){                                          //Can have male OR GN
                    if (! (bathroom.isMale() || bathroom.isOther())){
                        continue;
                    }
                } else if (femaleStaus) {                                 //Can have female OR GN
                    if (! (bathroom.isFemale() || bathroom.isOther())){
                        continue;
                    }
                } else {                                                  //Can ONLY have GN
                    if (! bathroom.isOther()){
                        continue;
                    }
                }

            } else if (maleStatus && (! bathroom.isMale())){ //Non-gender neutral male needs male bathroom
                continue;
            } else if (femaleStaus && (! bathroom.isFemale())) { //Non-gender neutral female needs female bathroom
                continue;
            }

//            Passed all tests to get here
            applicable.add(bathroom);
        }

        return applicable;
    }
}
