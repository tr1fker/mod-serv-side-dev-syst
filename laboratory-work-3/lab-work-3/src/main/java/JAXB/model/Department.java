package JAXB.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
public class Department {

    private String departmentNumber;
    private String departmentName;
    private String location;
    private int budget;

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public Department() {
    }

    public Department(String departmentNumber, String departmentName, String location, int budget) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
        this.location = location;
        this.budget = budget;
    }

    // Геттеры и сеттеры
    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "number='" + departmentNumber + '\'' +
                ", name='" + departmentName + '\'' +
                ", location='" + location + '\'' +
                ", budget=" + budget +
                ", employeesCount=" + (employees != null ? employees.size() : 0) +
                '}';
    }
}