package JAXB.test;

import JAXB.model.Department;
import JAXB.model.Employee;
import JAXB.model.Organization;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestExample {

    private static final String XML_FILE = "D:\\Study\\Семестр5\\MSSDS\\laboratory-work-3\\lab-work-3\\src\\main\\java\\JAXB\\organization.xml";

    public static void main(String[] args) {

        Employee emp1 = new Employee("E01", "Tom", null, "Director", 80000);
        Employee emp2 = new Employee("E02", "Mary", "E01", "Accountant", 60000);
        Employee emp3 = new Employee("E03", "John", null, "IT Manager", 75000);
        Employee emp4 = new Employee("E04", "Anna", "E03", "Developer", 55000);
        Employee emp5 = new Employee("E05", "Mike", "E03", "Developer", 52000);

        List<Employee> accountingEmployees = new ArrayList<>();
        accountingEmployees.add(emp1);
        accountingEmployees.add(emp2);

        List<Employee> itEmployees = new ArrayList<>();
        itEmployees.add(emp3);
        itEmployees.add(emp4);
        itEmployees.add(emp5);

        Department dept1 = new Department("D01", "ACCOUNTING", "NEW YORK", 500000);
        dept1.setEmployees(accountingEmployees);

        Department dept2 = new Department("D02", "IT", "SAN FRANCISCO", 800000);
        dept2.setEmployees(itEmployees);

        List<Department> departments = new ArrayList<>();
        departments.add(dept1);
        departments.add(dept2);

        Organization organization = new Organization("TechCorp Inc", "TC001", "123 Business Ave, New York", "+1-555-0123");
        organization.setDepartments(departments);

        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            System.out.println("=== Organization to XML ===");
            m.marshal(organization, System.out);

            File outFile = new File(XML_FILE);
            m.marshal(organization, outFile);
            System.err.println("Write to file: " + outFile.getAbsolutePath());

            System.out.println("\n=== Reading from XML ===");

            Unmarshaller um = context.createUnmarshaller();
            Organization orgFromFile = (Organization) um.unmarshal(new FileReader(XML_FILE));

            System.out.println("Organization: " + orgFromFile);

            List<Department> deptsFromFile = orgFromFile.getDepartments();
            if (deptsFromFile != null) {
                for (Department dept : deptsFromFile) {
                    System.out.println("\nDepartment: " + dept);

                    List<Employee> emps = dept.getEmployees();
                    if (emps != null) {
                        for (Employee emp : emps) {
                            System.out.println("  - " + emp);
                        }
                    }
                }
            }

            System.out.println("\n=== Statistics ===");
            System.out.println("Total departments: " + (deptsFromFile != null ? deptsFromFile.size() : 0));

            int totalEmployees = 0;
            double totalSalary = 0;
            if (deptsFromFile != null) {
                for (Department dept : deptsFromFile) {
                    if (dept.getEmployees() != null) {
                        totalEmployees += dept.getEmployees().size();
                        for (Employee emp : dept.getEmployees()) {
                            totalSalary += emp.getSalary();
                        }
                    }
                }
            }
            System.out.println("Total employees: " + totalEmployees);
            System.out.println("Total salary budget: $" + totalSalary);
            System.out.println("Average salary: $" + (totalEmployees > 0 ? totalSalary / totalEmployees : 0));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}