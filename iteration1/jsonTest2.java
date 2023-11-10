package iteration1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jsonTest2 {

    static List<Student> students = new ArrayList<>();
    static List<Course> courses = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        readJson();
        System.out.println("students size: " + students.size());
        System.out.println("\n\n");
        writeJson();

    }

    private static void writeJson() {
        writeJson("students");
        writeJson("courses");
    }

    private static void writeJson(String jsonType) {
        Gson gson = new Gson();
        String json = gson.toJson(jsonType);
        String fileName = "iteration1/jsons/" + jsonType + ".json";
        try {
            // Write the JSON string to a file
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readJson() throws IOException {
        readStudents();
        readCourses();
        readAdvisors();
    }

    static void readStudents() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Parse the JSON file into a Java object.
        JsonNode jsonNode = mapper.readTree(new File("iteration1/jsons/randomstudents.json"));

        // Get the students array.
        JsonNode studentsArray = jsonNode.get("students");


        for (JsonNode student : studentsArray) {
//            JsonNode coursesArray = student.get("coursesTaken");
//            System.out.println(coursesArray.get(0).get("courseName").asText());
            int id = 111111111;
            String name = student.get("name").asText();
            String surname = student.get("surname").asText();
            String userName = student.get("userName").asText();
            String password = student.get("password").asText();
            String department = student.get("department").asText();
            double cgpa = student.get("cgpa").asDouble();
            int gradeLevel = student.get("gradeLevel").asInt();
            Student student1 = new Student(id, name, surname, department, password, userName, (byte) gradeLevel);

            students.add(student1);
            System.out.printf("id: %d, name: %s, cgpa: %f, gradeLevel: %d\n", id, name, cgpa, gradeLevel);
        }
        System.out.println();
    }


    static void readCourses() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Parse the JSON file into a Java object.
        JsonNode jsonNode = mapper.readTree(new File("iteration1/jsons/randomcourses.json"));
        // Get the students array.
        JsonNode coursesArray = jsonNode.get("courses");

        for (JsonNode course : coursesArray) {
            String courseName = course.get("courseName").asText();
            String courseCode = course.get("courseCode").asText();
            int courseCredit = course.get("courseCredit").asInt();
            List<String> preRequisiteCourseCodes = new ArrayList<>();
            List<String> preRequsitieToCourseCodes = new ArrayList<>();
            JsonNode preRequisiteCourseCodesArray = course.get("preRequisiteCourseCodes");
            JsonNode preRequsitieToCourseCodesArray = course.get("preRequisiteToCourseCodes");
            for (JsonNode preRequisiteCourseCode : preRequisiteCourseCodesArray) {
                preRequisiteCourseCodes.add(preRequisiteCourseCode.asText());
            }
            for (JsonNode preRequsitieToCourseCode : preRequsitieToCourseCodesArray) {
                preRequsitieToCourseCodes.add(preRequsitieToCourseCode.asText());
            }
            Course course1 = new Course(courseName, courseCode, courseCredit, (byte) 7);
            courses.add(course1);
            System.out.printf("courseName: %s, courseCode: %s, courseCredit: %d\n", courseName, courseCode, courseCredit);

        }


    }

    static void readAdvisors() throws IOException {

    }
}
