package com.example.testing;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class testCompare {

    @Test
    public void failDiffCols() throws IOException {
        CSVReader csvReader = new CSVReader("./failDiffCols/1.csv", "./failDiffCols/2.csv","./failDiffCols/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        assertEquals("Error: The orders of columns of two files are not same",csvReader.compare());
    }

    @Test
    public void failNoBalance() throws IOException {
        CSVReader csvReader = new CSVReader("./failNoBalance/1.csv", "./failNoBalance/2.csv","./failNoBalance/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        assertEquals("Error: there is no column 'Balance' in the files, can not compare",csvReader.compare());
    }

    @Test
    public void failColNotExist() throws IOException {
        CSVReader csvReader = new CSVReader("./failColNotExist/1.csv", "./failColNotExist/2.csv","./failColNotExist/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3,4,5".getBytes());
        System.setIn(in);
        assertEquals("Error: can not choose an index that is not inside given choices",csvReader.compare());
    }

    @Test
    public void failMissingValue() throws IOException {
        CSVReader csvReader = new CSVReader("./failMissingValue/1.csv", "./failMissingValue/2.csv","./failMissingValue/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        assertEquals("can not compare files with missing values",csvReader.compare());
    }

    @Test
    public void passEmpty() throws IOException {
        CSVReader csvReader = new CSVReader("./passEmpty/1.csv", "./passEmpty/2.csv","./passEmpty/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2".getBytes());
        System.setIn(in);
        assertEquals("finish comparing",csvReader.compare());
    }

    @Test
    public void passBalanceNotLast() throws IOException {
        CSVReader csvReader = new CSVReader("./passBalanceNotLast/1.csv", "./passBalanceNotLast/2.csv","./passBalanceNotLast/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,4".getBytes());
        System.setIn(in);
        assertEquals("finish comparing",csvReader.compare());
    }

    @Test
    public void passRepeatRecords() throws IOException {
        CSVReader csvReader = new CSVReader("./passRepeatRecords/1.csv", "./passRepeatRecords/2.csv","./passRepeatRecords/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        assertEquals("finish comparing",csvReader.compare());
    }

    @Test
    public void passIdNotOrdered() throws IOException {
        CSVReader csvReader = new CSVReader("./passIdNotOrdered/1.csv", "./passIdNotOrdered/2.csv","./passIdNotOrdered/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        assertEquals("finish comparing",csvReader.compare());
    }

    @Test
    public void passDiffLength() throws IOException {
        CSVReader csvReader = new CSVReader("./passDiffLength/1.csv", "./passDiffLength/2.csv","./passDiffLength/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        assertEquals("finish comparing",csvReader.compare());
    }
}
