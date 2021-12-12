import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

public class HamcrestTesting {

    // ДОРАБОТКА
    @Test
    public void hamcrest_check_ListToJsonReturnsEmptyStringIfInputListEmpty() {
        List<Employee> list = new ArrayList<>();
        String expected = "[]";
        String result = Main.listToJson(list);
        MatcherAssert.assertThat(expected, Matchers.equalTo(result));
    }

    //Попробовал параметризованный тест
    @ParameterizedTest
    @ValueSource(strings = "[{\"id\":0,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"country\":\"RUS\",\"age\":29}]")
    public void hamcrest_check_ListToJsonReturnsCorrectString(String jsonString) {
        Employee employee = new Employee(0, "Ivan", "Ivanov", "RUS", 29);
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        String result = Main.listToJson(list);
        MatcherAssert.assertThat(jsonString, Matchers.equalTo(result));
    }

    @ParameterizedTest
    @ValueSource(strings = "[{\"id\":0,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"country\":\"RUS\",\"age\":29}," +
            "{\"id\":1,\"firstName\":\"Johannes\",\"lastName\":\"Klaebo\",\"country\":\"NOR\",\"age\":26}]")
    public void hamcrest_check_ListToJsonReturnsCorrectStringIfTwoOrMore(String jsonString) {
        Employee employee = new Employee(0, "Ivan", "Ivanov", "RUS", 29);
        Employee employee2 = new Employee(1, "Johannes", "Klaebo", "NOR", 26);
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);
        String result = Main.listToJson(list);
        MatcherAssert.assertThat(jsonString, Matchers.equalTo(result));
    }

    @Test
    public void newHamcrest_ParseCSV_checkListIsNotEmpty() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        MatcherAssert.assertThat(list, is(not(empty())));
    }

    @Test
    public void newHamcrest_ListToJson_check() {
        List<Employee> list = new ArrayList<>();
        String result = Main.listToJson(list);

        MatcherAssert.assertThat(result, is(not((nullValue()))));
    }


    @Test
    public void newHamcrest_ParseCSV_checkColumnMappingSize() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        //list.g
        MatcherAssert.assertThat(list, is(not(empty())));
    }

    @Test
    public void hamcrest_ParseCSV_AddWrongName_ReturnsNull() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.css";
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        MatcherAssert.assertThat(list, equalTo(null));
    }


    @Test
    public void hamcrest_parseCSV_AddEmptyFile_ReturnsEmptyList() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "empty.csv";
        List<Employee> list = Main.parseCSV(columnMapping, fileName);
        MatcherAssert.assertThat(list, empty());
    }

    @Test
    public void hamcrest_readString_NullString_ReturnsNull() {
        MatcherAssert.assertThat(Main.readString(null), equalTo(null));
    }

}
