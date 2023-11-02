package iteration1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class jsonTest2 {
    public static void main(String[] args) throws Exception {
        readJson();
    }

    static void readJson() throws IOException {
        readStudents();
        readCourses();
        readAdvisors();
    }

    static void readStudents() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Parse the JSON file into a Java object.
        JsonNode students = mapper.readTree(new File("iteration1/jsons/students.json"));

        // Get the students array.
        JsonNode studentsArray = students.get("students");




        for (JsonNode student : studentsArray) {
            JsonNode coursesArray = student.get("coursesTaken");
            System.out.println(coursesArray.get(0).get("courseName").asText());

            int id = student.get("id").asInt();
            String name = student.get("name").asText();
            double cgpa = student.get("cgpa").asDouble();
            int gradeLevel = student.get("gradeLevel").asInt();

            Student studentObject = new Student(id, name, cgpa, gradeLevel);
        }
    }


    static void readCourses() throws IOException {

    }

    static void readAdvisors() throws IOException {

    }
}
