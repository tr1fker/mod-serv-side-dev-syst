package DOM.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import DOM.model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXML {
    public static void main(String[] args) throws IOException {
        BufferedReader input = null;

        try {
            input = new BufferedReader(new InputStreamReader(System.in));

            File file = new File("D:\\Study\\Семестр5\\MSSDS\\laboratory-work-3\\lab-work-3\\src\\main\\java\\DOM\\employees.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("Employees");
            document.appendChild(root);

            System.out.println("Enter how many employees data you want to add? : ");
            int count = Integer.parseInt(input.readLine());

            for (int i = 1; i <= count; i++) {
                System.out.println("******* Employee : " + i + " ********");

                System.out.println("Enter name : ");
                String name = input.readLine();
                System.out.println("Enter gender : (male/female)");
                String gender = input.readLine();
                System.out.println("Enter age : ");
                int age = Integer.parseInt(input.readLine());
                System.out.println("Enter role : ");
                String role = input.readLine();
                System.out.println("Enter salary : ");
                double salary = Double.parseDouble(input.readLine());
                System.out.println("Enter department : ");
                String department = input.readLine();
                System.out.println("Enter department location (for attribute): ");
                String departmentLocation = input.readLine();
                System.out.println("Enter email : ");
                String email = input.readLine();

                Employee employee = new Employee();

                employee.setName(name);
                employee.setGender(gender);
                employee.setAge(age);
                employee.setRole(role);
                employee.setSalary(salary);
                employee.setDepartment(department);
                employee.setEmail(email);

                Element element = getEmployeeNode(employee, i, document, departmentLocation);
                root.appendChild(element);
            }

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            System.out.println("Employee data has been added successfully.");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }

    }

    private static Element getEmployeeNode(Employee employee, int id, Document document, String departmentLocation) {
        Element element = document.createElement("Employee");

        element.setAttribute("id", String.valueOf(id));

        Element name = getPropertyNode("name", document, employee.getName());
        element.appendChild(name);

        Element gender = getPropertyNode("gender", document, employee.getGender());
        element.appendChild(gender);

        Element age = getPropertyNode("age", document, String.valueOf(employee.getAge()));
        element.appendChild(age);

        Element role = getPropertyNode("role", document, employee.getRole());
        element.appendChild(role);

        Element salary = getPropertyNode("salary", document, String.valueOf(employee.getSalary()));
        element.appendChild(salary);

        // Department с атрибутом location
        Element department = document.createElement("department");
        department.setAttribute("location", departmentLocation);
        department.setTextContent(employee.getDepartment());
        element.appendChild(department);

        Element email = getPropertyNode("email", document, employee.getEmail());
        element.appendChild(email);

        return element;
    }

    private static Element getPropertyNode(String property, Document document, String value) {
        Element element = document.createElement(property);
        element.setTextContent(value);
        return element;
    }
}