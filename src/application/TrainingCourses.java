/*
 * File Name: TrainingCourses.java
 * Author: Lesbek Tamirlan
 * Description: Manages employee details for FoodCos training courses Assignment.
 */

package application;

import util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class TrainingCourses {

    private LinkedList<Employee> empList; // Linked list to store employees
    private JFrame frame;
    private JTextField EmplNum, EmpName, YExp, CourseName;
    private JTextArea Display;
    private EmployeeTableModel tableModel;
    private JTable table;
    private int maxEmployees;

    public static void main(String[] args) {
        TrainingCourses trainingCourses = new TrainingCourses();
        trainingCourses.setMaxEmp(); // Prompt user for maximum inputs, 10 max
        trainingCourses.createGUI();
    }

    public TrainingCourses() {
        empList = new LinkedList<>();
    }


    public void setMaxEmp() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Enter the maximum number of employees (1-10):", "Set Limit", JOptionPane.QUESTION_MESSAGE);
            try {
                maxEmployees = Integer.parseInt(input);
                if (maxEmployees >= 1 && maxEmployees <= 10) {
                    JOptionPane.showMessageDialog(null, "You can now add up to " + maxEmployees + " employees.", "Limit Set", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 10.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * GUI interface for the application
     */
    public void createGUI() {
        frame = new JFrame("Training Courses");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = createIPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel tablePanel = createTablePanel();
        JPanel textAreaPanel = createTxtPanel();

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(tablePanel, BorderLayout.WEST);
        frame.add(textAreaPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Creates the input panel for employee details
     */
    private JPanel createIPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel lblEmployeeNumber = new JLabel("Employee Number:");
        EmplNum = new JTextField();
        JLabel lblEmployeeName = new JLabel("Employee Name:");
        EmpName = new JTextField();
        JLabel lblYearsExperience = new JLabel("Years of Experience:");
        YExp = new JTextField();
        JLabel lblCourseName = new JLabel("Course Name:");
        CourseName = new JTextField();

        panel.add(lblEmployeeNumber);
        panel.add(EmplNum);
        panel.add(lblEmployeeName);
        panel.add(EmpName);
        panel.add(lblYearsExperience);
        panel.add(YExp);
        panel.add(lblCourseName);
        panel.add(CourseName);

        return panel;
    }

    /**
     * Creates the button panel with all functionality
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JButton btnAddEmployee = new JButton("Add Employee");
        JButton btnDisplayEmployees = new JButton("Display Employees");
        JButton btnRemoveEmployee = new JButton("Remove Employee");
        JButton btnDeleteAllFromCourse = new JButton("Delete All From Course");

        btnAddEmployee.addActionListener(e -> addEmployee());
        btnDisplayEmployees.addActionListener(e -> displayEmployees());
        btnRemoveEmployee.addActionListener(e -> removeEmployee());
        btnDeleteAllFromCourse.addActionListener(e -> deleteAllFromCourse());

        panel.add(btnAddEmployee);
        panel.add(btnDisplayEmployees);
        panel.add(btnRemoveEmployee);
        panel.add(btnDeleteAllFromCourse);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tableModel = new EmployeeTableModel();
        table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTxtPanel() {
        JPanel panel = new JPanel();
        Display = new JTextArea(10, 30);
        Display.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(Display);
        panel.add(scrollPane);

        return panel;
    }

    /**
     * Adds a new employee to the list with validation
     */
    private void addEmployee() {
        if (empList.size() >= maxEmployees) {
            showError("Cannot add more than " + maxEmployees + " employees!");
            return;
        }

        String empNumber = EmplNum.getText().trim();
        String empName = EmpName.getText().trim();
        String yearsExp = YExp.getText().trim();
        String course = CourseName.getText().trim();

        if (empNumber.isEmpty() || empName.isEmpty() || yearsExp.isEmpty() || course.isEmpty()) {
            showError("All fields are required.");
            return;
        }

        try {
            int years = Integer.parseInt(yearsExp);
            validateDetails(empNumber, empName, years, course);

            Employee employee = new Employee(empNumber, empName, years, course);
            empList.addSorted(employee);
            showSuccess("Employee added successfully!");
            displayEmployees();

        } catch (NumberFormatException ex) {
            showError("Years of experience must be a number.");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    private void validateDetails(String empNumber, String empName, int years, String course) {
        for (Employee employee : empList.getAllEmployees()) {
            if (employee.getempNum().equals(empNumber)) {
                throw new IllegalArgumentException("Employee number must be unique.");
            }
        }

        if (years <= 5) {
            throw new IllegalArgumentException("Employee must have more than 5 years of experience.");
        }

        if (!course.startsWith("FOOD")) {
            throw new IllegalArgumentException("Course name must start with 'FOOD'.");
        }
    }

    private void displayEmployees() {
        List<Employee> empListAsList = new ArrayList<>(empList.getAllEmployees());
        tableModel.setEmployees(empListAsList);
        updateTextDisplay();
    }

    /**
     * Removes an employee from the list by employee number
     */
    private void removeEmployee() {
        String empNumber = JOptionPane.showInputDialog(frame, "Enter Employee Number to remove:");
        if (empNumber != null && !empNumber.trim().isEmpty()) {
            empNumber = empNumber.trim();
            Employee foundEmployee = null;

            // Search manually for the employee
            for (Employee employee : empList.getAllEmployees()) {
                if (employee.getempNum().equals(empNumber)) {
                    foundEmployee = employee;
                    break;
                }
            }

            if (foundEmployee != null) {
                empList.delete(foundEmployee);
                showSuccess("Employee removed successfully.");
                displayEmployees();
            } else {
                showError("Employee not found.");
            }
        } else {
            showError("Invalid input.");
        }
    }

    private void deleteAllFromCourse() {
        String courseName = JOptionPane.showInputDialog(frame, "Enter the course name to delete all employees from:");
        if (courseName != null && !courseName.trim().isEmpty()) {
            List<Employee> employeesToDelete = new ArrayList<>(empList.getAllEmployees());
            for (Employee employee : employeesToDelete) {
                if (employee.getCourseName().equals(courseName)) {
                    empList.delete(employee);
                }
            }
            showSuccess("All employees from course '" + courseName + "' have been deleted.");
            displayEmployees();
        } else {
            showError("Invalid input.");
        }
    }

    private void updateTextDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Employee emp : empList.getAllEmployees()) {
            sb.append(emp).append("\n------------\n");
        }
        Display.setText(sb.toString());
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
