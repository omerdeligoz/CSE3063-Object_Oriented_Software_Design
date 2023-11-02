package iteration1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class jsonTest {
    public static void main(String[] args) throws Exception {
        readJson();
//        writeJson();

    }


    private static void readJson() throws Exception {


        readStudents();
        readCourses();
        readAdvisors();
    }

    private static void readAdvisors() {
    }

    private static void readCourses() {
    }

    private static void readStudents() throws IOException, ParseException {
        FileReader fileReader = new FileReader("iteration1/jsons/students.json");
        // Create a JSONParser object
        JSONParser jsonParser = new JSONParser();

        // Parse the JSON file
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;


        //for objects
        String departmantname = (String) jsonObject.get("departmentName");
        System.out.println(departmantname);
        System.out.println("\n");


        //For arrays
        JSONArray students = (JSONArray) jsonObject.get("students");

        JSONObject deneme = (JSONObject) students.get(0);


        for (Object student : students) {
            JSONObject studentJson = (JSONObject) student;
            int id = Integer.parseInt(studentJson.get("id") + "");
            String name = studentJson.get("name") + "";
            double cgpa = Double.parseDouble(studentJson.get("cgpa") + "");
            int gradeLevel = Integer.parseInt(studentJson.get("gradeLevel") + "");
            System.out.printf("id: %d, name: %s, cgpa: %f, gradeLevel: %d\n", id, name, cgpa, gradeLevel);
//            Student studentObject = new Student(id, name, cgpa, gradeLevel);
//            department.add(studentObject);
        }


    }

    private static void writeJson() throws Exception {
        JSONObject jsonObject = new JSONObject();

        // Add a "name" key to the JSONObject object
        jsonObject.put("name", "Jasdasd  sadae");
        jsonObject.put("2nd name", "ali xyz");
        jsonObject.put("3rd name", "veli asd");

        // Create a JSONArray object
        JSONArray jsonArray = new JSONArray();

        // Add the JSONObject object to the JSONArray object
        jsonArray.add(jsonObject);

        // Write the JSON object to a file
        FileWriter fileWriter = new FileWriter("iteration1/jsons/students.json");
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.close();
    }
}
