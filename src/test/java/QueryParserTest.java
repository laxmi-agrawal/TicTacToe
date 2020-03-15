import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

public class QueryParserTest {
    @Test
    public void testQueryParser(){
        Assertions.assertEquals(  new HashMap<String, String>() {{
            put("a", "b");
        }}, Handlers.queryParser("a=b"));

        Assertions.assertEquals(  new HashMap<String, String>() {{
            put("abc", "1");
            put("def","2");
        }}, Handlers.queryParser("abc=1&def=2"));

        Assertions.assertEquals(  new HashMap<String, String>() {{
            put("a", "");
        }}, Handlers.queryParser("a="));

    }
}
