package com.example.testing;


import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class systemTest {

    public boolean checkResult(String path1, String path2) throws IOException {
        File file1 = new File(path1);
        FileReader fr1 = new FileReader(file1);
        BufferedReader br1 = new BufferedReader(fr1);
        File file2 = new File(path2);
        FileReader fr2 = new FileReader(file2);
        BufferedReader br2 = new BufferedReader(fr2);

        String line1 = br1.readLine();
        String line2 = br2.readLine();

        if (line1 == null || line2 == null){
            return line1 == null && line2 == null;
        }

        while (line1 != null && line2 != null){
            if (!line1.equals(line2)){
                return false;
            }
            line1 = br1.readLine();
            line2 = br2.readLine();
        }
        return line1 == null && line2 == null;
    }

    @Test
    public void failInvalidFile() throws IOException {
        CSVReader csvReader = new CSVReader("invalid1","invalid2","output.csv");
        String state = csvReader.compare();
        assertEquals("file not found",state);
    }

    @Test
    public void failChooseInvalidCol1() throws IOException {
        CSVReader csvReader = new CSVReader("./failColNotExist/1.csv","./failColNotExist/2.csv","output1.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("1,2,3,4,5".getBytes());
        System.setIn(in);
        String state = csvReader.compare();
        assertEquals("Error: can not choose an index that is not inside given choices",state);
    }

    @Test
    public void failChooseInvalidCol2() throws IOException {
        CSVReader csvReader = new CSVReader("./failColNotExist/1.csv","./failColNotExist/2.csv","output2.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("-11,-2,3,4,5".getBytes());
        System.setIn(in);
        String state = csvReader.compare();
        assertEquals("Error: can not choose an index that is not inside given choices",state);
    }

    @Test
    public void passBalanceNotLastCol() throws IOException {
        CSVReader csvReader = new CSVReader("./passBalanceNotLast/1.csv", "./passBalanceNotLast/2.csv","./passBalanceNotLast/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,4".getBytes());
        System.setIn(in);
        csvReader.compare();
        boolean state = checkResult("./passBalanceNotLast/output.csv","./passBalanceNotLast/answer.csv");
        System.out.println(state);
        assertTrue(state);
    }

    @Test
    public void passDiffLength() throws IOException {
        CSVReader csvReader = new CSVReader("./passDiffLength/1.csv", "./passDiffLength/2.csv","./passDiffLength/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        csvReader.compare();
        boolean state = checkResult("./passDiffLength/output.csv","./passDiffLength/answer.csv");
        System.out.println(state);
        assertTrue(state);
    }

    @Test
    public void passEmpty() throws IOException {
        CSVReader csvReader = new CSVReader("./passEmpty/1.csv", "./passEmpty/2.csv","./passEmpty/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        csvReader.compare();
        boolean state = checkResult("./passEmpty/output.csv","./passEmpty/answer.csv");
        System.out.println(state);
        assertTrue(state);
    }

    @Test
    public void passIdNotOrdered() throws IOException {
        CSVReader csvReader = new CSVReader("./passIdNotOrdered/1.csv", "./passIdNotOrdered/2.csv","./passIdNotOrdered/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        csvReader.compare();
        boolean state = checkResult("./passIdNotOrdered/output.csv","./passIdNotOrdered/answer.csv");
        System.out.println(state);
        assertTrue(state);
    }

    @Test
    public void passRepeatRecords() throws IOException {
        CSVReader csvReader = new CSVReader("./passRepeatRecords/1.csv", "./passRepeatRecords/2.csv","./passRepeatRecords/output.csv");
        ByteArrayInputStream in = new ByteArrayInputStream("0,1,2,3".getBytes());
        System.setIn(in);
        csvReader.compare();
        boolean state = checkResult("./passRepeatRecords/output.csv","./passRepeatRecords/answer.csv");
        System.out.println(state);
        assertTrue(state);
    }


}
