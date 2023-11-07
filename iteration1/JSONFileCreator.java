package iteration1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JSONFileCreator {
    public static void main(String[] args) {
        createStudents();
        createCourses();
    }

    private static void createCourses() {
        JSONArray coursesArray = new JSONArray();
        // Create a JSON object containing the "courses" key
        JSONObject coursesJSON = new JSONObject();
        coursesJSON.put("courses", coursesArray);

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            JSONObject courseJSON = new JSONObject();

            // Generate random data for each course
            String[] courseNames = {"Introduction to Computer Science", "Data Structures", "Algorithms", "Database Management", "Web Development", "Artificial Intelligence" , "Machine Learning and Deep Learning", "Computer Networks", "Operating Systems", "Computer Architecture", "Software Engineering"};
            String courseName = courseNames[i - 1];
            String courseCode = "CSE" + (100 + i);
            int courseCredit = 3 + random.nextInt(3); // Course credits from 3 to 5

            List<String> preRequisiteCourseCodes = new ArrayList<>();
            int numPrerequisites = random.nextInt(3); // Random number of prerequisites (0 to 2)
            for (int j = 0; j < numPrerequisites; j++) {
                preRequisiteCourseCodes.add("CSE" + (100 + random.nextInt(10)));
            }

            List<String> preRequisiteToCourseCodes = new ArrayList<>();
            int numPrerequisiteTo = random.nextInt(3); // Random number of courses for which this is a prerequisite (0 to 2)
            for (int j = 0; j < numPrerequisiteTo; j++) {
                preRequisiteToCourseCodes.add("CSE" + (100 + random.nextInt(10)));
            }

            courseJSON.put("courseName", courseName);
            courseJSON.put("courseCode", courseCode);
            courseJSON.put("courseCredit", courseCredit);
            courseJSON.put("preRequisiteCourseCodes", preRequisiteCourseCodes);
            courseJSON.put("preRequisiteToCourseCodes", preRequisiteToCourseCodes);

            coursesArray.put(courseJSON);
        }


        // Write the JSON array to a file
        String jsonFileName = "iteration1/jsons/randomcourses.json";
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(jsonFileName), coursesArray.toString(4).getBytes());
            System.out.println("JSON file created successfully: " + jsonFileName);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private static void createStudents() {
        JSONArray studentsArray = new JSONArray();

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            JSONObject studentJSON = new JSONObject();

            // Generate random data for each student
            int studentID = 1000 + i;
            String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ian", "Jessica"};
            String name = names[i - 1];
            String surname = "Smith";
            String[] departments = {"Computer Science", "Engineering", "Mathematics"};
            String department = departments[i % 3];
            String userName = "user" + i;
            String password = "pass" + i;
            double cgpa = 2.0 + (3.0 - 2.0) * random.nextDouble();
            byte gradeLevel = (byte) (1 + random.nextInt(4));

            studentJSON.put("studentID", studentID);
            studentJSON.put("name", name);
            studentJSON.put("surname", surname);
            studentJSON.put("department", department);
            studentJSON.put("userName", userName);
            studentJSON.put("password", password);
            studentJSON.put("cgpa", cgpa);
            studentJSON.put("gradeLevel", gradeLevel);

            studentsArray.put(studentJSON);
        }
        // Create a JSON object containing the "courses" key
        JSONObject coursesJSON = new JSONObject();
        coursesJSON.put("students", studentsArray);

        // Write the JSON array to a file
        String jsonFileName = "iteration1/jsons/randomstudents.json";
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(jsonFileName), studentsArray.toString(4).getBytes());
            System.out.println("JSON file created successfully: " + jsonFileName);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
