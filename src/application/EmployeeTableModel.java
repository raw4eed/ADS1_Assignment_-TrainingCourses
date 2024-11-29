package application;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {

    private List<Employee> employees;
    private String[] columnNames = {"Employee Number", "Employee Name", "Years of Experience", "Course Name"};

    public EmployeeTableModel() {
        this.employees = null;
    }

    /**
     * updates the list
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        fireTableDataChanged();
    }

    public int getRowCount() {
        if (employees == null) {
            return 0;
        }
        return employees.size();  // return the number of employees
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employees.get(rowIndex);
        switch (columnIndex) {
            case 0:  // employee number
                return employee.getempNum();
            case 1:  // employee name
                return employee.getName();
            case 2:  // eears of experience
                return employee.getempNum();
            case 3:  // course name
                return employee.getCourseName();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
