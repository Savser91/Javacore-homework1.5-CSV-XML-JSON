import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;


import java.util.List;


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
    public void parseCSV_AddWrongName_ReturnsNull () {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.css";
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        Assertions.assertTrue(list == null);
    }



}
