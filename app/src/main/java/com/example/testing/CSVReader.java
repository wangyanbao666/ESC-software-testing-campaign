package com.example.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class CSVReader {
    private String path1;
    private String path2;
    private String path3;
    private File file1;
    private File file2;
    private BufferedReader br1;
    private BufferedReader br2;
    private ArrayList<Integer> colsToCompare = new ArrayList<>();

    public HashMap getOtherProps() {
        return otherProps;
    }

    public void setOtherProps(HashMap otherProps) {
        this.otherProps = otherProps;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    private HashMap otherProps = new HashMap();
    private Integer balance;


    public CSVReader(String path1, String path2, String path3){
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
    }

    public String read(){
        try{
            this.file1 = new File(this.path1);
            FileReader fr1 = new FileReader(this.file1);
            this.br1 = new BufferedReader(fr1);
        }
        catch (Exception e){
            System.out.println("error: file not found");
            return "file not found";
        }
        try{
            this.file2 = new File(this.path2);
            FileReader fr2 = new FileReader(this.file2);
            this.br2 = new BufferedReader(fr2);
        }
        catch (Exception e){
            System.out.println("error: file not found");
            return "file not found";
        }
        return "finish reading";
    }

    public String chooseCols() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Iterator iterator = otherProps.keySet().iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
            Object value = otherProps.get(key);
            System.out.println(String.format("%d. %s",key,value));
        }
        System.out.println("Choose index of columns as combination (separate by comma):");

        String colString = myObj.nextLine();  // Read user input
        String[] cols = colString.split(",");
        try {
            for (String col: cols) {
                this.colsToCompare.add(Integer.parseInt(col));
            }
        }catch (Exception e){
            return "the input must be a list of integers";
        }
        return "finish choosing cols";
    }

    @SuppressWarnings("DefaultLocale")
    public String compare() throws IOException {

        read();

        String line1 = br1.readLine();
        String line2 = br2.readLine();
        ArrayList<String> doc1Copy = new ArrayList<String>();
        ArrayList<String> doc1 = new ArrayList<String>();
        ArrayList<String> doc2 = new ArrayList<String>();
        ArrayList<String> exceptions = new ArrayList<String>();

        if (line1 == null || line2 == null){
            System.out.println("Error: Can not compare a file that is empty");
            return "Error: Can not compare a file that is empty";
        }

        else if (!line1.equals(line2)){
            System.out.println("Error: The orders of columns of two files are not same");
            return "Error: The orders of columns of two files are not same";
        }

        String[] cols = line1.split(",");
        boolean flag = true;
        for (int i=0;i<cols.length;i++){
            if (!Objects.equals(cols[i], "Balance")){
                otherProps.put(i, cols[i]);
            }
            else {
                flag = false;
                balance = i;
            }
        }
        if (flag){
            System.out.println("Error: there is no column 'Balance' in the files, can not compare");
            return "Error: there is no column 'Balance' in the files, can not compare";
        }

        chooseCols();

//        check whether column index > num of columns
        for (int colIndex: this.colsToCompare){
            if (!this.otherProps.containsKey(colIndex)){
                System.out.println(
                 "Error: can not choose an index that is not inside given choices");
                return "Error: can not choose an index that is not inside given choices";
            }
        }

//        add records to a list
        while ((line1 = br1.readLine()) != null) {
            doc1.add(line1);
        }

        while ((line2 = br2.readLine()) != null) {
            doc2.add(line2);
        }

        //        create copy of doc1
        for (String line: doc1){
            doc1Copy.add(line);
        }

        Iterator<String> itr1 = doc1Copy.iterator();
        while (itr1.hasNext()){
            String lineOfDoc1 = itr1.next();
            String[] splitLine1 = lineOfDoc1.split(",");
            boolean deleteLine = false;
            Iterator<String> itr2 = doc2.iterator();
            while (itr2.hasNext()){
                String lineOfDoc2 = itr2.next();
                String[] splitLine2 = lineOfDoc2.split(",");
                boolean tag = true;
                try {
                    for (int i: this.colsToCompare){
                        if (!splitLine1[i].equals(splitLine2[i])){
                            tag = false;
                            break;
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    return "can not compare files with missing values";
                }

                try {
                    if (tag){
                        if (splitLine1[this.balance].equals(splitLine2[this.balance])){
                            itr2.remove();
                            deleteLine = true;
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    return "can not compare files with missing values";
                }
            }

            if (deleteLine){
                Iterator<String> itr3 = doc1.iterator();
                while (itr3.hasNext()){
                    String line = itr3.next();
                    if (line.equals(lineOfDoc1)){
                        itr3.remove();
                    }
                }
            }
        }

        for (String line: doc1){
            exceptions.add(line);
        }
        for (String line: doc2){
            exceptions.add(line);
        }

        br1.close();
        br2.close();
        for (int i=0;i<exceptions.size();i++) {
            System.out.println(exceptions.get(i));
        }
        output(exceptions);
        return "finish comparing";
    }

    private void output(ArrayList<String> exceptions){
        File file = new File(this.path3);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);
            for (String exception : exceptions) {
                outputFile.append(exception);
                outputFile.append("\n");
            }
            outputFile.flush();
            outputFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("sample_file_1.csv","sample_file_3.csv","output.csv");
        reader.compare();
    }
}
