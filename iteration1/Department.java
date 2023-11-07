package iteration1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Department {
    String departmentName;

   private List<Course> courses = new ArrayList<>();
   private List<Student> students = new ArrayList<>();
    List<Advisor> advisors = new ArrayList<>();
    List<Transcript> transcripts = new ArrayList<>();
    Map<String, Student> studentPasswordMap = new HashMap<>();
    Map<String, Person> userNamePersonMap = new HashMap<>();  //it holds username and password of users
    Map<String, Advisor> advisorPasswordMap = new HashMap<>();

    byte maxCourseNumber = 5;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public List<Transcript> getTranscripts() {
        return transcripts;
    }

    public Map<String, Student> getStudentPasswordMap() {
        return studentPasswordMap;
    }

    public Map<String, Advisor> getAdvisorPasswordMap() {
        return advisorPasswordMap;
    }

    public Map<String, Person> getUserNamePersonMap() {
        return userNamePersonMap;
    }

    public byte getMaxCourseNumber() {
        return maxCourseNumber;
    }
}
