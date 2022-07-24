package com.example.testing;

import org.junit.Test;

import static org.junit.Assert.*;

public class testRead {
    @Test
    public void failNoFile(){
        CSVReader csvReader = new CSVReader("file1","file2","output");
        assertEquals("file not found",csvReader.read());
    }

    @Test
    public void passFileExist(){
        CSVReader csvReader = new CSVReader("sample_file_1.csv","sample_file_2.csv","output");
        assertEquals("finish reading",csvReader.read());
    }
}
