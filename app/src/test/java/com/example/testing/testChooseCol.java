package com.example.testing;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;


public class testChooseCol {
    @Test
    public void testSingleValid(){
        CSVReader csvReader = new CSVReader("file1","file2","output");
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        assertEquals("finish choosing cols",csvReader.chooseCols());
    }

    @Test
    public void testMultipleValid(){
        CSVReader csvReader = new CSVReader("file1","file2","output");
        ByteArrayInputStream in = new ByteArrayInputStream("1,2,3,10".getBytes());
        System.setIn(in);
        assertEquals("finish choosing cols",csvReader.chooseCols());
    }

    @Test
    public void testSingleInvalid(){
        CSVReader csvReader = new CSVReader("file1","file2","output");
        ByteArrayInputStream in = new ByteArrayInputStream("k".getBytes());
        System.setIn(in);
        assertEquals("the input must be a list of integers",csvReader.chooseCols());
    }

    @Test
    public void testMultipleInvalid(){
        CSVReader csvReader = new CSVReader("file1","file2","output");
        ByteArrayInputStream in = new ByteArrayInputStream("a,b,c,d".getBytes());
        System.setIn(in);
        assertEquals("the input must be a list of integers",csvReader.chooseCols());
    }
}
