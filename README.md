
# ESC-software-testing-campaign

## Use case diagram
![image](https://user-images.githubusercontent.com/66866227/178204051-01ed0311-ed80-42e6-915a-3947e6fbc121.png)

## How to run the program
Download the whole project and open it using android studio. The class CSVReader is created to do the job. Run the main function of CSVReader in the terminal and you can claim your input file in the code `CSVReader reader = new CSVReader("input file1 path", "input file2 path", "output file path");`. If the files exist in the same directory as the CSVReader class, you will be required to choose columns as unique combination. You can choose columns in the provided choices based on the columns contained in your files.

## Requirements: 
1. Files must be in csv format separated with comma and each entity should not be packed with "".
2. Files should not contain missing values.
3. The columns should be in the same order.


### Note: 
1. Since my android studio crashed and I reconfigured the IDLE, I have to force push to the main branch and the commit history of main branch is cleared.
2. Tese cases are inside app/src/test/java/com/example/testing.
