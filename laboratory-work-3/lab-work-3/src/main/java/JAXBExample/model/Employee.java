package JAXBExample.model;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    private String employeeNumber;
    private String employeeName;
    private String managerNumber;
    public Employee() {}

    public Employee(String employeeNumber, String employeeName, String managerNumber) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.managerNumber = managerNumber;
    }

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

    public void setManager(String managerNo) {
        this.managerNumber = managerNo;
    }
}