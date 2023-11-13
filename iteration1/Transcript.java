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

    //Mehrin
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
import java.util.List;
import java.util.Map;

public class Transcript {
    private List<Course> studentCourses;
    private double cgpa;
    private byte semester;
    private int takenCredits;

    private int completedCredits;
    private Student student;
    private Map<Course, Grade> courseGradeMap;


    public Transcript() {
        this.student = new Student();
        this.cgpa = 0;
        semester = 0;
        takenCredits = 0;
        studentCourses = new ArrayList<>();
    }

    public Transcript(Student student) {
        this.student = student;
        this.semester = student.getSemester();
        this.cgpa = calculateCgpa();
        this.takenCredits = calculateTakenCredits();
        this.courseGradeMap = new HashMap<>();
    }

    private double calculateCgpa() {
        double totalGrade = 0;
        int totalCredits = 0;
        for (Course course : studentCourses) {
            if (courseGradeMap.get(course) != null) {
                totalGrade += courseGradeMap.get(course).getGradeOver4() * course.getCourseCredit();
                totalCredits += course.getCourseCredit();
            }
        }
        cgpa = totalGrade / totalCredits;
        return cgpa;
    }

    public int calculateTakenCredits() {
        for (Course course : studentCourses) {
            takenCredits += course.getCourseCredit();
        }
        return takenCredits;
    }

    public int calculativeCompletedCredits() {
        completedCredits = takenCredits;
        for (Course course : studentCourses) {
            if (courseGradeMap.get(course).getLetterGrade().equals("FF")
                    || courseGradeMap.get(course).getLetterGrade().equals("FD")
                    || courseGradeMap.get(course) == null) {
                completedCredits = takenCredits - course.getCourseCredit();
            }
        }
        return completedCredits;
    }

    public Student readTranscript(String file) throws IOException {
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
        Student newStudent = new Student(id, name, surname, userName, password, (byte) gradeLevel);
        System.out.println(newStudent.getName());
        return newStudent;

    }

    public void showTranscript() {
        JSONObject transcriptJSON = new JSONObject();

        transcriptJSON.put("studentName", student.getName());
        transcriptJSON.put("studentSurname", student.getSurname());
        transcriptJSON.put("studentId", student.getID());
        transcriptJSON.put("studentGpa", cgpa);
        transcriptJSON.put("takenCredits", calculateTakenCredits());
        transcriptJSON.put("completedCredits", calculativeCompletedCredits());
        transcriptJSON.put("currentSemester", semester);
        transcriptJSON.put("studentDepartment", student.getDepartmentName());

        JSONArray courses = new JSONArray();

        for (Course course : studentCourses) {
            JSONObject courseData = new JSONObject();
            courseData.put("Course ID", course.getCourseCode());
            courseData.put("Credits", course.getCourseCredit());
            courseData.put("Grade", courseGradeMap.get(course).getLetterGrade());
            courseData.put("Course Name", course.getCourseName());
            courses.put(courseData);
        }

        transcriptJSON.put("Courses", courses);

        String filePath = "iteration1\\jsons\\Transcripts" + student.getID() + ".json";
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(transcriptJSON.toString(4));
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


