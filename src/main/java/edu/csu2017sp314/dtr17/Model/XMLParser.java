package main.java.edu.csu2017sp314.dtr17.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mjdun on 4/11/2017.
 */
public class XMLParser {

    public ArrayList<String> parseXML(String fileName){
        File file = new File(fileName);
        String idString = "<id>";
        String endString = "</id>";
        ArrayList<String> IDs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if(line != null){
                if(line.toLowerCase().contains(idString.toLowerCase())){
                    String result = line.toLowerCase().substring(line.toLowerCase().indexOf(idString.toLowerCase()) + 4, line.toLowerCase().indexOf(endString.toLowerCase()));
                    IDs.add(result);
                }
            }

            while ((line = br.readLine()) != null) {
                if(line.toLowerCase().contains(idString.toLowerCase())){
                    String result = line.toLowerCase().substring(line.toLowerCase().indexOf(idString.toLowerCase()) + 4, line.toLowerCase().indexOf(endString.toLowerCase()));
                    IDs.add(result);
                }
            }

            br.close();
            return IDs;

        } catch(IOException exception){
            System.out.println("Could not open file: " + fileName);
            return null;
        }
    }
}
