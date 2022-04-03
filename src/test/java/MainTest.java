import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void input1() {
        Main main = new Main("src/test/resources/input1.txt", "src/test/resources/output1.txt");
        main.readFileIntoLists();
        Map<String, String> resultedMap = main.getResult();
        Map<String, String> expectedMap = new LinkedHashMap<>();
        expectedMap.put("гвоздь", "?");
        expectedMap.put("шуруп", "шуруп 3x1.5");
        expectedMap.put("краска синяя", "краска");
        expectedMap.put("ведро для воды", "корыто для воды");
        Assert.assertEquals(expectedMap, resultedMap);
    }

    @Test
    public void input2() {
        Main main = new Main("src/test/resources/input2.txt", "src/test/resources/output2.txt");
        main.readFileIntoLists();
        Map<String, String> resultedMap = main.getResult();
        Map<String, String> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Бетон с присадкой", "Цемент");
        Assert.assertEquals(expectedMap, resultedMap);
    }

    @Test
    public void input3() {
        Main main = new Main("src/test/resources/input3.txt", "src/test/resources/output3.txt");
        main.readFileIntoLists();
        Map<String, String> resultedMap = main.getResult();
        Map<String, String> expectedMap = new LinkedHashMap<>();
        expectedMap.put("Бетон с присадкой", "присадка для бетона");
        expectedMap.put("доставка", "?");
        Assert.assertEquals(expectedMap, resultedMap);
    }
}