import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<String, Object>(10);
        for (int i = 0; i <20 ; i++) {
            map.put(""+i, i);
        }

    }
}
