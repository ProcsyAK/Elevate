
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Scanner;



public class StudentRecords {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        
        int choice;

        do {
            System.out.println("\n=== Student Record Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> removeStudent();
                case 5 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);
    }


    private static void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int rollno = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter First Name: ");
            String firstname = scanner.nextLine();

            System.out.print("Enter Last Name: ");
            String lastname = scanner.nextLine();

            System.out.print("Enter DOB (dd-MM-yyyy): ");
            String dobInput = scanner.nextLine();
            Date dob= dateFormat.parse(dobInput);
            
            

            System.out.print("Enter Course: ");
            String course = scanner.nextLine();

            System.out.print("Enter Marks Scored: ");
            float marksScored = scanner.nextFloat();

            students.add(new Student() {{
                setRollno(rollno);
                setFirstname(firstname);
                setLastname(lastname);
                setDOB(dob);
                setCourse(course);
                setMarksScored(marksScored);
            }});

            System.out.println("Student added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter DOB as dd-MM-yyyy.");
        }
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\nStudent Records:");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Roll Number to update: ");
        int rollno = scanner.nextInt();
        scanner.nextLine();  

        Student s = findStudentByRollno(rollno);
        if (s != null) {
            try {
                System.out.print("Enter new First Name: ");
                s.setFirstname(scanner.nextLine());

                System.out.print("Enter new Last Name: ");
                s.setLastname(scanner.nextLine());

                System.out.print("Enter new DOB (dd-MM-yyyy): ");
                String dobInput = scanner.nextLine();
                s.setDOB(dateFormat.parse(dobInput));

                System.out.print("Enter new Course: ");
                s.setCourse(scanner.nextLine());

                System.out.print("Enter new Marks Scored: ");
                s.setMarksScored(scanner.nextFloat());

                System.out.println("Student updated successfully!");
            } catch (ParseException e) {
                System.out.println("Invalid date format. Update failed.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void removeStudent() {
        System.out.print("Enter Roll Number to delete: ");
        int rollno = scanner.nextInt();

        Student s = findStudentByRollno(rollno);
        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudentByRollno(int rollno) {
        for (Student s : students) {
            if (s.getRollno() == rollno) return s;
        }
        return null;
    }



}
class Student{
    private int rollno;
    private String firstname;
    private String lastname;
    private Date DOB;
    private String course;
    private float marksScored;



    public Student() {
        
    }


    public int getRollno() {
        return rollno;
    }
    public void setRollno(int rollno) {
        this.rollno = rollno;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Date getDOB() {
        return DOB;
    }
    public void setDOB(Date dOB) {
        DOB = dOB;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public float getMarksScored() {
        return marksScored;
    }
    
    public void setMarksScored(float marksScored) {
        this.marksScored = marksScored;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{");
        sb.append("rollno=").append(rollno);
        sb.append(", firstname=").append(firstname);
        sb.append(", lastname=").append(lastname);
        sb.append(", DOB=").append(DOB);
        sb.append(", course=").append(course);
        sb.append(", marksScored=").append(marksScored);
        sb.append('}');
        return sb.toString();
    }

}

