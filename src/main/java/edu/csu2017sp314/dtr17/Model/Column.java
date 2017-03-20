package main.java.edu.csu2017sp314.dtr17.Model;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by mjdun on 2/19/2017.
 */
public class Column {
    public Column(String columnName){
        this.columnName = columnName;
        columnData = new ArrayList<String>();
    }

    public void add(String newData) {
        columnData.add(newData);
    }

    public String getDataAt(int index){
        if(index > (columnData.size() - 1)){
            return null;
        }

        return columnData.get(index);
    }

    public String getColumnName(){
        return columnName;
    }

    public int getHeight(){
        return columnData.size();
    }

    protected ArrayList<String> columnData;
    protected String columnName;
}
