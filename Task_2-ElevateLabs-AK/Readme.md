# Student Record Management System

## Objective
Create a CLI-based CRUD system for managing student records.

## Tools
- Java
- VS Code / IntelliJ Community Edition

## Features
- Add Student
- View All Students
- Update Student
- Delete Student

## Student Class Fields
- `rollno` : int (Student Roll Number)
- `firstname` : String (Student First Name)
- `lastname` : String (Student Last Name)
- `DOB` : Date (Date of Birth, format: dd-MM-yyyy)
- `course` : String (Course enrolled)
- `marksScored` : float (Marks scored by student)

## How to Run
1. Open VS Code or IntelliJ CE.
2. Create a new Java project.
3. Add the `StudentRecords.java` file with both `StudentRecords` and `Student` classes.
4. Compile and run `StudentRecords.java`.
5. Use the CLI menu to perform CRUD operations.

## CRUD Operations

### 1. Add Student
- Prompts for roll number, first name, last name, DOB (dd-MM-yyyy), course, and marks scored.
- Adds a new student object to the `ArrayList`.

### 2. View All Students
- Displays all student records in a readable format.

### 3. Update Student
- Prompts for roll number to update.
- Updates student details (first name, last name, DOB, course, marks scored).
- Handles incorrect date formats gracefully.

### 4. Delete Student
- Prompts for roll number to delete.
- Removes student from `ArrayList` if found.

## Notes
- `DOB` is stored as `java.util.Date`. Only the date is entered; time defaults to 00:00:00.
- The system uses a `SimpleDateFormat` of `dd-MM-yyyy` for parsing dates.
- Input validation for dates and duplicate roll numbers is recommended for production use.

## Sample CLI Menu
```
=== Student Record Management System ===
1. Add Student
2. View All Students
3. Update Student
4. Delete Student
5. Exit
Enter your choice:
```

