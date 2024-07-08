import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Person {
    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}

public class Main {
    private static final String FILE_PATH = "data.json";
    private static List<Person> people = new ArrayList<>();

    public static void main(String[] args) {
        loadFromFile();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Show all");
            System.out.println("2. Add");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Sort by Name");
            System.out.println("6. Sort by Age");
            System.out.println("7. Sort by City");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> showAll();
                case 2 -> addEntry(scanner);
                case 3 -> updateEntry(scanner);
                case 4 -> deleteEntry(scanner);
                case 5 -> sortByName();
                case 6 -> sortByAge();
                case 7 -> sortByCity();
                case 8 -> {
                    saveToFile();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void showAll() {
        people.forEach(System.out::println);
    }

    private static void addEntry(Scanner scanner) {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter city:");
        String city = scanner.nextLine();

        people.add(new Person(name, age, city));
    }

    private static void updateEntry(Scanner scanner) {
        System.out.println("Enter index to update:");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (index < 0 ||  index >= people.size()) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter new city:");
        String city = scanner.nextLine();

        Person person = people.get(index);
        person.setName(name);
        person.setAge(age);
        person.setCity(city);
    }

    private static void deleteEntry(Scanner scanner) {
        System.out.println("Enter index to delete:");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (index < 0 || index >= people.size()) {
            System.out.println("Invalid index.");
            return;
        }

        people.remove(index);
    }

    private static void sortByName() {
        people.sort(Comparator.comparing(Person::getName));
    }
    private static void sortByAge() {
        people.sort(Comparator.comparingInt(Person::getAge));
    }

    private static void sortByCity() {
        people.sort(Comparator.comparing(Person::getCity));
    }

    private static void loadFromFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            StringBuilder jsonContent = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonContent.append((char) i);
            }
            JSONArray jsonArray = new JSONArray(jsonContent.toString());
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String name = jsonObject.getString("name");
                int age = jsonObject.getInt("age");
                String city = jsonObject.getString("city");
                people.add(new Person(name, age, city));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile() {
        JSONArray jsonArray = new JSONArray();
        for (Person person : people) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", person.getName());
            jsonObject.put("age", person.getAge());
            jsonObject.put("city", person.getCity());
            jsonArray.put(jsonObject);
        }

        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(jsonArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
