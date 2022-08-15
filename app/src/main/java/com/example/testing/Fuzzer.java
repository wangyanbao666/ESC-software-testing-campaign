package com.example.testing;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Fuzzer {
    private int doc_len;
    private int num_of_cols;
    private int missing_value;
    private int missing_value_num;
    private int num_of_generation;
    private String[] cols = new String[]{"Account No.","Currency","Type"};
    private String[][] choose_2 = new String[][]{new String[]{"Account No.","Currency"},
            new String[]{"Currency", "Type"}, new String[]{"Account No.","Type"}};

    Fuzzer(){
        Random random = new Random();
        doc_len = random.nextInt(100);
        num_of_cols = random.nextInt(5);
        missing_value = random.nextInt(2);
        missing_value_num = (int)Math.floor(random.nextDouble()*doc_len*num_of_cols);
        num_of_generation = random.nextInt(3);
    }

//    public ArrayList generateFile1(){
//        int count = 1;
//
//    }

    public void generateFile2(){

    }

    public void mutate(){

    }


}
