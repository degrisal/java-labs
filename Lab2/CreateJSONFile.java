
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class CreateJSONFile {
    public static void main(String[] args) {
        JSONArray people = new JSONArray();

        JSONObject person1 = new JSONObject();
        person1.put("name", "Alex");
        person1.put("age", 24);
        person1.put("city", "Samara");

        JSONObject person2 = new JSONObject();
        person2.put("name", "Peter");
        person2.put("age", 23);
        person2.put("city", "Berlin");

        JSONObject person3 = new JSONObject();
        person3.put("name", "Oleg");
        person3.put("age", 25);
        person3.put("city", "Moscow");

        JSONObject person4 = new JSONObject();
        person4.put("name", "John");
        person4.put("age", 22);
        person4.put("city", "New York");

        people.put(person1);
        people.put(person2);
        people.put(person3);
        people.put(person4);

        try (FileWriter file = new FileWriter("data.json")) {
            file.write(people.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}