import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.util.*;

public class ProjectTesting {

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running Test");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Test completed");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");
    }

    @AfterEach
    public void testFinilized() {
        System.out.println("Test finalized");
    }

    @Test
    public void parseCSV_AddWrongFileName_ReturnsNull() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.css";
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        Assertions.assertTrue(list == null);
    }

    @Test
    public void parseCSV_AddEmptyFile_ReturnsEmptyList() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "empty.csv";
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        Assertions.assertAll(
                () -> Assertions.assertTrue(list.isEmpty()),
                () -> Assertions.assertFalse(list == null)
        );
    }

    @Test
    public void readString_NullString_ReturnsNull() {
        Assertions.assertTrue(Main.readString(null) == null);
    }


    //ДОРАБОТКА

    // Проверяем, что метод ListToJson возвращает пустую строку, если на входе пустой лист
    @Test
    public void check_ListToJsonReturnsEmptyStringIfInputListEmpty() {
        List<Employee> list = new ArrayList<>();
        String expected = "[]";

        Assertions.assertEquals(expected, Main.listToJson(list));
    }

    // Создаем лист с 1-м сотрудником, вручную генерим строку и проверяем, что метод отдает такую же строку
    @Test
    public void check_ListToJsonReturnsCorrectString() {
        Employee employee = new Employee(0, "Ivan", "Ivanov", "RUS", 29);
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        String expected = "[{\"id\":0,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"country\":\"RUS\",\"age\":29}]";
        Assertions.assertEquals(expected, Main.listToJson(list));
    }

    // Проверка, что все ок, если в листе больше 1 сотрудника
    @Test
    public void check_ListToJsonReturnsCorrectStringIfTwoOrMore() {
        Employee employee = new Employee(0, "Ivan", "Ivanov", "RUS", 29);
        Employee employee2 = new Employee(1, "Johannes", "Klaebo", "NOR", 26);
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);

        String expected = "[{\"id\":0,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"country\":\"RUS\",\"age\":29}," +
                "{\"id\":1,\"firstName\":\"Johannes\",\"lastName\":\"Klaebo\",\"country\":\"NOR\",\"age\":26}]";
        Assertions.assertEquals(expected, Main.listToJson(list));
    }



}
