import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class task3 {
    private static void makeReport(ObjectNode object,HashMap<Integer,String> values) {
        if (object.has("values")) {
            System.out.println(object);
            for (JsonNode value : object.get("values")) {
                makeReport((ObjectNode) value, values);
            }
        }
        changeValue(object, values);
    }
    private static void changeValue(ObjectNode object, HashMap<Integer,String> values) {
        int id = object.get("id").asInt();
        if (values.containsKey(id)) {
            object.put("value", values.get(id));
        }
    }
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode values = (ArrayNode) objectMapper
                .readTree(new File(args[0]))
                .get("values");
        ObjectNode tests = (ObjectNode) objectMapper.readTree(new File(args[1]));
        HashMap<Integer,String> hashMap = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            hashMap.put(values.get(i).get("id").asInt(), values.get(i).get("value").asText());
        }
        System.out.print(hashMap);

        for (int i = 0; i < tests.get("tests").size(); i++) {
            ObjectNode objectNode = (ObjectNode) tests.get("tests").get(i);
            System.out.println(objectNode);
            makeReport(objectNode, hashMap);
        }
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("report.json"), tests);
    }
}
