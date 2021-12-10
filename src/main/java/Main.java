import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"ad", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        String fileXmlName = "data.xml";
        //Задача 1
        writeString(listToJson(parseCSV(columnMapping, fileName)));
        //Задача 2
        writeString(listToJson(parseXML(fileXmlName)));
        //Задача 3
        System.out.println(jsonToList(readString("data.json")).toString());
    }

    public static List<Employee> parseCSV(String[] columnMapping, String name) {
        try (CSVReader csvreader = new CSVReader(new FileReader(name))) {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean toBean = new CsvToBeanBuilder<Employee>(csvreader)
                    .withMappingStrategy(strategy)
                    .build();
            return toBean.parse();
        } catch (Exception exception) {
            exception.getMessage();
            return null;
        }
    }

    public static List<Employee> parseXML(String name) {
        List<Employee> list = new ArrayList<>();
        try {
            Employee employee;
            long id = 0;
            String firstName = null;
            String lastName = null;
            String country = null;
            int age = 0;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            NodeList nodeList = builder.parse(new File(name)).getDocumentElement().getElementsByTagName("employee");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList employeeNodeList = node.getChildNodes();
                    for (int j = 0; j < employeeNodeList.getLength(); j++) {
                        if (employeeNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (employeeNodeList.item(j).getNodeName()) {
                                case ("id"):
                                    id = Integer.parseInt(employeeNodeList.item(j).getTextContent());
                                    break;
                                case ("firstName"):
                                    firstName = employeeNodeList.item(j).getTextContent();
                                    break;
                                case ("lastName"):
                                    lastName = employeeNodeList.item(j).getTextContent();
                                    break;
                                case ("country"):
                                    country = employeeNodeList.item(j).getTextContent();
                                    break;
                                case ("age"):
                                    age = Integer.parseInt(employeeNodeList.item(j).getTextContent());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    employee = new Employee(id, firstName, lastName, country, age);
                    list.add(employee);
                }
            }
        } catch (Exception exception) {
            exception.getMessage();
        }
        return list;
    }

    public static String listToJson(List<Employee> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.toJson(list, listType);
    }

    public static void writeString(String string) {
        try (FileWriter writer = new FileWriter("data.json")) {
            writer.write(string);
        } catch (Exception exception) {
            exception.getMessage();
        }
    }

    public static String readString(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.readLine();
        } catch (Exception exception) {
            exception.getMessage();
            return null;
        }
    }

    public static List<Employee> jsonToList(String string) {
        List<Employee> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonParser parser = new JsonParser();
        JsonArray array = (JsonArray) parser.parse(string);
        for (int i = 0; i < array.size(); i++) {
            Employee employee = gson.fromJson(array.get(i).getAsJsonObject(), Employee.class);
            list.add(employee);
        }
        return list;
    }
}
