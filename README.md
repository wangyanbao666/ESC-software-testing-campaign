# ESC-software-testing-campaign

# Equivalence class:

## 1. 2 file both exist/at lease one file does not exist:
check whether the system can handle the error if there is missing file

### Boundary value: 
	provide one invalid file name
### Middle value: 
	provide two invalid file names
	both file names are valid

## 2. 2 files have equal/different number of records:
If the two files are not of equal length, there might be some indexing error in the code which can result in the crash of the whole software.

### Boundary value: 
	test_2_boundary (one file has one record fewer than the other)
### Middle value: 
	test_2_middle (one file has 10 records while the other one has no record)
	test_2_middle2 (one file has 10 records while the other one has 1 record)

## 3. The ids are ordered/unordered:
The question didn’t specify whether the ids should be ordered or not. If it is, we can deal it with some simple solutions such as comparing the two documents line by line, which might result in an error in more complex cases.

### Boundary value: 
	test_3_boundary (id of two rows is not ordered)
	test_3_boundary2 (id are in reversed order)
### Middle value: 
	test_3_middle (all the ids are randomized)

## 4. Have repetitive ids/have no repetitive ids:
Repetitive ids may result in some error in the code.

### Boundary value: 
	test_4_boundary (only one id is repeated)
### Middle value: 
	test_4_middle (most of the ids are repeated, some more than one time)

## 5. Contain records with missing values/all records have no missing value:
If there are records with some missing values, the way to compare the records might produce an error.

### Boundary value: 
	test_5_boundary (only one record contains one missing value)
### Middle value: 
	test_5_middle (many records contain missing values, even missing a whole line)
