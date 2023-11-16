package unnecessary;

import iteration1.Course;
import iteration1.CourseSection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JSONFileCreator {


    public void createCourses() {
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
            List<String> courseSectionCodes = new ArrayList<>();
            int numSections = random.nextInt(3); // Random number of prerequisites (0 to 2)
            for (int j = 0; j < numSections; j++) {
                courseSectionCodes.add(courseCode + "." + (1 + random.nextInt(3)));
            }

            int courseCredit = 3 + random.nextInt(3); // Course credits from 3 to 5
            int lecturerID = 3000 + random.nextInt(10);
            byte gradeLevel = (byte) (1 + random.nextInt(4)); // Semester from 1 to 4

            List<String> preRequisiteCourseCodes = new ArrayList<>();
            int numPrerequisites = random.nextInt(3); // Random number of prerequisites (0 to 2)
            for (int j = 0; j < numPrerequisites; j++) {
                preRequisiteCourseCodes.add("CSE" + (100 + random.nextInt(10)));
            }



            //TODO course section eklenebilir ???
            courseJSON.put("courseName", courseName);
            courseJSON.put("courseCode", courseCode);
            courseJSON.put("courseCredit", courseCredit);
            courseJSON.put("gradeLevel", gradeLevel);
            courseJSON.put("lecturerID", lecturerID);
            courseJSON.put("preRequisiteCourseCodes", preRequisiteCourseCodes);
            courseJSON.put("courseSectionCodes", courseSectionCodes);
            coursesArray.put(courseJSON);
        }


        // Write the JSON array to a file
        String jsonFileName = "iteration1/jsons/courses.json";
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(jsonFileName), coursesArray.toString(4).getBytes());
            System.out.println("JSON file created successfully: " + jsonFileName);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void createStudents() {
        JSONArray studentsArray = new JSONArray();

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            JSONObject studentJSON = new JSONObject();
            Course course1 = new Course("courseName1", "CSE100", 3, (byte) 3);
            Course course2 = new Course("courseName2", "CSE101", 3, (byte) 5);
            Course course3 = new Course("courseName3", "CSE102", 3, (byte) 7);

            Course courseSection1 = new CourseSection(course1, "CSE100.1", "Monday", 3);
            Course courseSection2 = new CourseSection(course1, "CSE100.1", "Tuesday", 4);
            Course courseSection3 = new CourseSection(course2, "CSE101.1", "Wednesday", 5);

            // Generate random data for each student
            int studentID = 150120000 + i;
            String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ian", "Jessica"};
            String[] surnames = { "Yılmaz", "Kaya", "Demir", "Şahin", "Çelik", "Yıldız", "Korkmaz", "Polat", "Öztürk", "Yıldırım" };
            String name = names[i - 1];
            String surname = "Smith";
            String departmentName = "CSE";
            String userName = "user" + i;
            String password = "pass" + i;
            int advisorID = 3000 + random.nextInt(10);
            boolean hasRequest = false;
            byte gradeLevel = (byte) (1 + random.nextInt(4));
            List<String> draft = new ArrayList<>(); //TODO maybe CourseSections
            draft.add(((CourseSection) courseSection1).getCourseSectionCode());
            draft.add(((CourseSection) courseSection2).getCourseSectionCode());

            studentJSON.put("studentID", studentID);
            studentJSON.put("name", name);
            studentJSON.put("surname", surname);
            studentJSON.put("userName", userName);
            studentJSON.put("password", password);
            studentJSON.put("gradeLevel", gradeLevel);
            studentJSON.put("advisorID", advisorID);
            studentJSON.put("departmentName", departmentName);
            studentJSON.put("hasRequest", hasRequest);
            studentJSON.put("draft", draft);


            studentsArray.put(studentJSON);
        }
        // Create a JSON object containing the "courses" key
        JSONObject coursesJSON = new JSONObject();
        coursesJSON.put("students", studentsArray);

        // Write the JSON array to a file
        String jsonFileName = "iteration1/jsons/students.json";
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(jsonFileName), studentsArray.toString(4).getBytes());
            System.out.println("JSON file created successfully: " + jsonFileName);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void createAdvisors() {
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
            //TODO add studentIDs when writing to file
            advisorsArray.put(advisorJSON);
        }
        // Create a JSON object containing the "courses" key
        JSONObject coursesJSON = new JSONObject();
        coursesJSON.put("advisors.json", advisorsArray);

        // Write the JSON array to a file
        String jsonFileName = "iteration1/jsons/randomadvisors.json";
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(jsonFileName), advisorsArray.toString(4).getBytes());
            System.out.println("JSON file created successfully: " + jsonFileName);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void createTranscripts() {
        org.json.simple.JSONArray transcripts = new org.json.simple.JSONArray();

        for(int i = 0; i < 10; i++) {
            org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();

            jsonObject.put("student_id", "student" + i);

            org.json.simple.JSONArray courses = new org.json.simple.JSONArray();

            for(int j = 0; j < 5; j++) {
                org.json.simple.JSONObject course = new org.json.simple.JSONObject();
                course.put("course_code", "course" + j);
                course.put("grade", new Random().nextInt(100));
                courses.add(course);
            }
            jsonObject.put("courses", courses);

            transcripts.add(jsonObject);
        }
    }

    public void createCourseSections() {
        JSONArray courseSectionsArray = new JSONArray();
        // Create a JSON object containing the "courses" key
        JSONObject coursesJSON = new JSONObject();
        coursesJSON.put("courseSections", courseSectionsArray);

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            JSONObject courseSectionJSON = new JSONObject();

            String courseCode = "CSE" + (100 + i);
            String courseSectionCode = courseCode + "." + (1 + random.nextInt(3));
            int hour = 9 + random.nextInt(5);
            String day = "Monday";

            courseSectionJSON.put("courseCode", courseCode);
            courseSectionJSON.put("courseSectionCode", courseSectionCode);
            courseSectionJSON.put("hour", hour);
            courseSectionJSON.put("day", day);

            courseSectionsArray.put(courseSectionJSON);
        }


        // Write the JSON array to a file
        String jsonFileName = "iteration1/jsons/courseSections.json";
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(jsonFileName), courseSectionsArray.toString(4).getBytes());
            System.out.println("JSON file created successfully: " + jsonFileName);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
