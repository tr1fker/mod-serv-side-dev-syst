package DOM.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import DOM.model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {
    public static void main(String[] args) {
        File file = new File("D:\\Study\\Семестр5\\MSSDS\\laboratory-work-3\\lab-work-3\\src\\main\\java\\DOM\\employees.xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            document.getDocumentElement().normalize();

            String rootNode = document.getDocumentElement().getNodeName();
            System.out.println("Root Element : " + rootNode);

            List<Employee> employees = getEmployeesData(document);
            for (Employee emp : employees) {
                System.out.println(emp);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private static List<Employee> getEmployeesData(Document document) {
        NodeList list = document.getElementsByTagName("Employee");
        int length = list.getLength();
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            Node node = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Employee emp = getEmployee(element);

                employees.add(emp);
            }
        }

        return employees;
    }

    private static Employee getEmployee(Element element) {
        String id = element.getAttribute("id");
        String name = getEmployeeDetails(element, "name");
        String gender = getEmployeeDetails(element, "gender");
        int age = Integer.parseInt(getEmployeeDetails(element, "age"));
        String role = getEmployeeDetails(element, "role");
        double salary = Double.parseDouble(getEmployeeDetails(element, "salary"));

        // Department с атрибутом
        Element departmentElement = (Element) element.getElementsByTagName("department").item(0);
        String department = departmentElement.getTextContent();
        String departmentLocation = departmentElement.getAttribute("location");

        String email = getEmployeeDetails(element, "email");

        Employee emp = new Employee();

        emp.setId(Integer.parseInt(id));
        emp.setName(name);
        emp.setAge(age);
        emp.setGender(gender);
        emp.setRole(role);
        emp.setSalary(salary);
        emp.setDepartment(department + " (Location: " + departmentLocation + ")");
        emp.setEmail(email);

        return emp;
    }

    private static String getEmployeeDetails(Element element, String property) {
        return element.getElementsByTagName(property).item(0).getTextContent();
    }
}