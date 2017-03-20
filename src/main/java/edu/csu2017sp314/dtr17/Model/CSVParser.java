package main.java.edu.csu2017sp314.dtr17.Model;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by mjdun on 2/19/2017.
 */
public class CSVParser {
    public CSVParser(String fileName){
        data = new CSVData();
        this.fileName = fileName;
    }

    public void printData(){
        data.printData();
    }

    public boolean parse(){
        File file = new File(fileName);
        boolean isFirstLine = true;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if(line != null){
                processFirstLine(line);
            }

            while ((line = br.readLine()) != null) {
                processLine(line);
            }

        } catch(IOException exception){
            System.out.println("Could not open file: " + fileName);
            return false;
        }

        return true;
    }

    protected void processLine(String line){
        String[] lineValues = line.split(",");

        for(int i = 0; i < lineValues.length; ++i){
            if(lineValues.length != columnTitles.length) {
                System.out.println("CSV Format error. Row is not the same size as the amount of columns");
            }else {
                data.addDataToColumn(columnTitles[i], lineValues[i]);
            }
        }
    }

    protected void processFirstLine(String line){
        columnTitles = line.split(",");

        for(int i = 0; i < columnTitles.length; ++i){
            data.addColumn(columnTitles[i]);
        }
    }

    public CSVData getCSVData() {
        return data;
    }

    protected String fileName;
    protected CSVData data;
    protected String[] columnTitles;
}
