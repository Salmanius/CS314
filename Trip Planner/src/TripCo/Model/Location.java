package TripCo.Model;

import java.util.DoubleSummaryStatistics;

/**
 * Created by mjdun on 2/19/2017.
 */
public class Location {
    protected String name;
    protected String stringLatitude;
    protected String stringLongitude;
    protected double dblLatitude;
    protected double dblLongitude;

    public Location(String ID, String name, String latitude, String longitude){
        this.name = name;
        this.stringLatitude = latitude;
        this.stringLongitude = longitude;

        parseLatitude();
        parseLongitude();
    }

    public double getDblLatitude(){
        return dblLatitude;
    }

    public double getDblLongitude(){
        return dblLongitude;
    }

    public String getName() {
        return name;
    }

    public String getStringLatitude() {
        return stringLatitude;
    }

    public String getStringLongitude() {
        return stringLongitude;
    }

    //converts string latitude to double representation
    protected void parseLatitude(){
        try{
            //If we can convert the string directly into a double, it is already in the proper format.
            Double.parseDouble(stringLatitude);
        } catch(NumberFormatException ex){
            if(tryParseDMSLatitude())
                return;

            if(!tryParseLatitudeWithUnits())
                dblLatitude = 0;
        }
    }

    //converts string latitude to double representation
    protected void parseLongitude(){
        try{
            //If we can convert the string directly into a double, it is already in the proper format.
            Double.parseDouble(stringLongitude);
        } catch(NumberFormatException ex){
            if(tryParseDMSLongitude())
                return;
            if(!tryParseLongitudeWithUnits())
                dblLongitude = 0;
        }
    }

    //DMS stands for "degrees, minutes, seconds"
    //Function will parse a string Latitude if it is in the form 106°47'35" N
    //Will return true if successful, false if not (should only fail if the string is not in that form)
    protected boolean tryParseDMSLatitude(){
        if(!(stringLatitude.contains("°") &&
                stringLatitude.contains("'") &&
                stringLatitude.contains("\"") &&
                (stringLatitude.toLowerCase().contains("n") || stringLatitude.toLowerCase().contains("s"))))
        {
            return false;
        }

        String[]tokens = stringLatitude.split("°|'|\"");

        if(tokens.length != 4)
            return false;

        dblLatitude = Double.parseDouble(tokens[0]) + Double.parseDouble(tokens[1])/60 + Double.parseDouble(tokens[2])/3600;
        if(tokens[3].toLowerCase().contains("s")){
            dblLatitude = dblLatitude * (-1);
        }

        return  true;
    }

    //DMS stands for "degrees, minutes, seconds"
    //Method will parse a string Longitude if it is in the form 106°47'35" W
    //Will return true if successful, false if not (should only fail if the string is not in that form)
    protected boolean tryParseDMSLongitude(){
        if(!(stringLongitude.contains("°") &&
                stringLongitude.contains("'") &&
                stringLongitude.contains("\"") &&
                (stringLongitude.toLowerCase().contains("w") || stringLongitude.toLowerCase().contains("e"))))
        {
            return false;
        }

        String[] tokens = stringLongitude.split("°|'|\"");

        if(tokens.length != 4)
            return false;

        dblLongitude = Double.parseDouble(tokens[0]) + Double.parseDouble(tokens[1])/60 + Double.parseDouble(tokens[2])/3600;
        if(tokens[3].toLowerCase().contains("w")){
            dblLongitude = dblLongitude * (-1);
        }

        return  true;
    }

    //Method will parse a string Latitude if it is in the form 36.33°S
    //Will return true if successful, false if not (should only fail if the string is not in that form)
    protected boolean tryParseLatitudeWithUnits(){
        if(!(stringLatitude.contains("°") &&
                (stringLatitude.toLowerCase().contains("n") || stringLatitude.toLowerCase().contains("s")))){
            return false;
        }

        String[] tokens = stringLatitude.split("°");

        dblLatitude = Double.parseDouble(tokens[0]);
        if(tokens[1].toLowerCase().contains("s"))
            dblLatitude = dblLatitude * (-1);

        return true;
    }

    //Method will parse a string Longitude if it is in the form 106.33°N
    //Will return true if successful, false if not (should only fail if the string is not in that form)
    protected boolean tryParseLongitudeWithUnits(){
        if(!(stringLongitude.contains("°") &&
                (stringLongitude.toLowerCase().contains("e") || stringLongitude.toLowerCase().contains("w")))){
            return false;
        }

        String[] tokens = stringLongitude.split("°");

        dblLongitude = Double.parseDouble(tokens[0]);
        if(tokens[1].toLowerCase().contains("w"))
            dblLongitude = dblLongitude * (-1);

        return true;
    }

}
