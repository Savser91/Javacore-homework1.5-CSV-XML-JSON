import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.*;

import java.util.*;

public class HamcrestTesting {

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
