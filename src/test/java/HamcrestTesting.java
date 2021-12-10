import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.*;
import java.util.List;

public class HamcrestTesting {

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
