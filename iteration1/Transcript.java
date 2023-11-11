/*

package iteration1;

import java.util.HashMap;
import java.util.Map;

public class Transcript {
    Student student;
    double cgpa;
    Map<Course, Grade> courseGradeMap = new HashMap<>();

    public void setStudent(Student student) {
        this.student = student;
    }

    public Map<Course, Grade> getCourseGradeMap() {
        return courseGradeMap;
    }

    public void showTranscript() {
        System.out.println("Showing Transcript ...\n\n");
        //TODO a to string method for transcript
    }

    public void courseStatusCheck(Student student) {
        //TODO
        System.out.println("Course Status  Check ...");
    }
}

*/


package iteration1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transcript {
    private ArrayList<Course> studentCourses;
    private double gpa;
    private int currentSemester;
    private int takenCredits;

    private int completedCredits;
    private Student student;
    Map<Course, Grade> courseGradeMap = new HashMap<>();


    public Transcript() {
        this.student = new Student();
        this.gpa = 0;
        currentSemester = 0;
        takenCredits = 0;
        studentCourses = new ArrayList<>();
    }
    public Transcript(Student student) {

        this.student = student;
        this.gpa = student.getCgpa();
        currentSemester = 5;
        takenCredits = 120;
        /* this.studentCourses = student.getSelectedCourses();*/
        Course course1 = new Course("Data Structures", "CSE2225.1", 7, "BA", null, null);
        Course course2 = new Course("Numerical Methods", "MATH2059.1", 4, "FF", null, null);
        Course course3 = new Course("Electric Circuits", "EE2031.1", 5, "CB", null, null);
        studentCourses = new ArrayList<>();
        studentCourses.add(course1);
        studentCourses.add(course2);
        studentCourses.add(course3);

    }


    public int calculateTakenCredits() {
        for (Course course : studentCourses) {
            takenCredits = takenCredits + course.getCourseCredit();
        }
        return takenCredits;
    }

    public int calculativeCompletedCredits() {
        completedCredits = takenCredits;
        for (Course course : studentCourses) {
            if (course.getGrade() == "FF" || course.getGrade() == "TT") {
                completedCredits = takenCredits - course.getCourseCredit();
            }
        }
        return completedCredits;
    }

    public Student readTranskript(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(new File(file));
        int lastIndex = file.lastIndexOf('\\');
        String fileNumber = "0";
        if (lastIndex >= 0) {
            String fileName = file.substring(lastIndex + 1);
            String[] parts = fileName.split("\\.");
            if (parts.length > 0) {
                fileNumber = parts[0];

            }
        }
        int id = Integer.parseInt(fileNumber);
        String name = jsonNode.get("studentName").asText();
        String surname = jsonNode.get("studentSurname").asText();
        String userName = name;
        String password = "123456";
        String department = jsonNode.get("studentDepartment").asText();
        double cgpa = jsonNode.get("studentGpa").asDouble();
        int gradeLevel = (byte) 3;
        Student newStudent = new Student(id, name, surname, department, password, userName, cgpa, (byte) gradeLevel);
        System.out.println(newStudent.getName());
        return newStudent;

    }

    public void showTranscript() {
        JSONObject transkriptJSON = new JSONObject();

        transkriptJSON.put("studentName", student.getName());
        transkriptJSON.put("studentSurname", student.getSurname());
        transkriptJSON.put("studentId", student.getID());
        transkriptJSON.put("studentGpa", student.getCgpa());
        transkriptJSON.put("takenCredits", calculateTakenCredits());
        transkriptJSON.put("currentCredits", calculativeCompletedCredits());
        transkriptJSON.put("currentSemester", currentSemester);
        transkriptJSON.put("studentDepartment", student.getDepartment());

        JSONArray courses = new JSONArray();

        for (Course course : studentCourses) {
            JSONObject courseData = new JSONObject();
            courseData.put("Course ID", course.getCourseCode());
            courseData.put("Credits", course.getCourseCredit());
            courseData.put("Grade", course.getGrade());
            courseData.put("Course Name", course.getCourseName());
            courses.put(courseData);
        }

        transkriptJSON.put("Courses", courses);

        String filePath = "C:\\Users\\MEHRİN\\Downloads\\CSE3063-Object_Oriented_Software_Design\\iteration1\\jsons\\" + student.getID() + ".json";
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(transkriptJSON.toString(4));
            file.flush();
            System.out.println("JSON verisi başarıyla dosyaya yazıldı: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Map<Course, Grade> getCourseGradeMap() {
        return courseGradeMap;
    }

    public void courseStatusCheck(Student student) {
        //TODO
        System.out.println("Course Status  Check ...");
    }
}


