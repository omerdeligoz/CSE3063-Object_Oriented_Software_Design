package iteration1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JSONFileCreator {

    public  void createCourses() {
        JSONArray coursesArray = new JSONArray();
        // Create a JSON object containing the "courses" key
        JSONObject coursesJSON = new JSONObject();
        coursesJSON.put("courses", coursesArray);

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            JSONObject courseJSON = new JSONObject();

            // Generate random data for each course
            String[] courseNames = {"Introduction to Computer Science", "Data Structures", "Algorithms", "Database Management", "Web Development", "Artificial Intelligence", "Machine Learning and Deep Learning", "Computer Networks", "Operating Systems", "Computer Architecture", "Software Engineering"};
            String courseName = courseNames[i - 1];
            String courseCode = "CSE" + (100 + i);
            int courseCredit = 3 + random.nextInt(3); // Course credits from 3 to 5
            byte semester = (byte) (1 + random.nextInt(4)); // Semester from 1 to 4

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


            //TODO course section eklenebilir ???
            courseJSON.put("courseName", courseName);
            courseJSON.put("courseCode", courseCode);
            courseJSON.put("courseCredit", courseCredit);
            courseJSON.put("semester", semester);
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

    public  void createStudents() {
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
            String departmentName = departments[i % 3];
            String userName = "user" + i;
            String password = "pass" + i;
            int advisorID = 3000 + random.nextInt(10);
            boolean hasRequest = false;
            byte semester = (byte) (1 + random.nextInt(4));

            studentJSON.put("studentID", studentID);
            studentJSON.put("name", name);
            studentJSON.put("surname", surname);
            studentJSON.put("userName", userName);
            studentJSON.put("password", password);
            studentJSON.put("semester", semester);
            studentJSON.put("advisorID", advisorID);
            studentJSON.put("departmentName", departmentName);
            studentJSON.put("hasRequest", hasRequest);

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

    public  void createAdvisors(){
        JSONArray advisorsArray = new JSONArray();

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            JSONObject advisorJSON = new JSONObject();

            // Generate random data for each student
            int advisorID = 3000 + i;
            String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ian", "Jessica"};
            String name = names[i - 1];
            String surname = "Smith";
            String[] departments = {"Computer Science", "Engineering", "Mathematics"};
            String department = departments[i % 3];
            String userName = "user" + i;
            String password = "pass" + i;

            advisorJSON.put("advisorID", advisorID);
            advisorJSON.put("name", name);
            advisorJSON.put("surname", surname);
            advisorJSON.put("userName", userName);
            advisorJSON.put("password", password);
            advisorJSON.put("department", department);

            advisorsArray.put(advisorJSON);
        }
        // Create a JSON object containing the "courses" key
        JSONObject coursesJSON = new JSONObject();
        coursesJSON.put("advisors", advisorsArray);

        // Write the JSON array to a file
        String jsonFileName = "iteration1/jsons/randomadvisors.json";
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(jsonFileName), advisorsArray.toString(4).getBytes());
            System.out.println("JSON file created successfully: " + jsonFileName);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public void createTranscripts(){
        //TODO
    }

    public void createCourseSections(){
        //TODO
    }
     
}
