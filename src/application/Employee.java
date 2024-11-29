/*
 * File Name: Employee.java
 * Author: Tamirlan Lesbek
 * Description:  Employees enrolled in a training course check
 */


package application;

public class Employee implements Comparable<Employee> {

    private final String empNum; 
    private String name;
    private int YExp;
    private String courseName;

    // constructor
    public Employee() {
        this.empNum = "";
        this.name = "";
        this.YExp = 0;
        this.courseName = "ERROR"; //set to ERROR if course name not set
    }

    // constructor with parameters
    public Employee(String empNum, String name, int YExp, String courseName) {
        if (YExp <= 5) {
            throw new IllegalArgumentException("Error: Employee must have more than 5 years of experience.");
        }

        this.empNum = empNum.trim(); // remove white spaces
        this.name = name.trim();
        this.YExp = YExp;

        if (!courseName.startsWith("FOOD")) {
            this.courseName = "ERROR";
            throw new IllegalArgumentException("Error: Course name must start with 'FOOD'.");
        } else {
            this.courseName = courseName.trim();
        }
    }

    // getters
    public String getempNum() {
        return empNum;
    }

    public String getName() {
        return name;
    }

    public int getYExp() {
        return YExp;
    }

    public String getCourseName() {
        return courseName;
    }

    // setters
    public void setName(String name) {
        this.name = name.trim();
    }

    public void setYExp(int YExp) {
        if (YExp > 5) {
            this.YExp = YExp;
        } else {
            throw new IllegalArgumentException("Error: Employee must have more than 5 years of experience.");
        }
    }

    public void setCourseName(String courseName) {
        if (!courseName.startsWith("FOOD")) {
            this.courseName = "ERROR";
            throw new IllegalArgumentException("Error: Course name must start with 'FOOD'.");
        } else {
            this.courseName = courseName.trim();
        }
    }

    // Method to display employee details
    public void displayEmployeeDetails() {
        System.out.println(this); // Calls toString
    }

    // method to check employee uniqueness
    public boolean isSameEmployee(Employee other) {
        return this.empNum.equals(other.getempNum());
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false; // Null or not same class

        Employee other = (Employee) obj;
        return this.empNum.equals(other.empNum); // Compare unique employee number
    }

    public int hashCode() {
        return empNum.hashCode(); // unique hash code based on employee number
    }

    // sort employees by their employee number
    public int compareTo(Employee other) {
        return this.empNum.compareTo(other.empNum); // comparison
    }

    // string of the Employee object
    public String toString() {
        return "Employee Number: " + empNum + "\n" +
               "Name: " + name + "\n" +
               "Years of Experience: " + YExp + "\n" +
               "Course Name: " + courseName;
    }
}
