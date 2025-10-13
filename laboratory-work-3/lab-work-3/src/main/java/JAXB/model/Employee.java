package JAXB.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    private String employeeNumber;
    private String employeeName;
    private String managerNumber;
    private String position;
    private double salary;

    public Employee() {}

    public Employee(String employeeNumber, String employeeName, String managerNumber, String position, double salary) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.managerNumber = managerNumber;
        this.position = position;
        this.salary = salary;
    }

    // Геттеры и сеттеры
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getManagerNumber() {
        return managerNumber;
    }

    public void setManagerNumber(String managerNumber) {
        this.managerNumber = managerNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "number='" + employeeNumber + '\'' +
                ", name='" + employeeName + '\'' +
                ", manager='" + managerNumber + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}