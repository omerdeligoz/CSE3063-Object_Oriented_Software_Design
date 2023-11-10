package iteration1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Department {
    String departmentName;
    List<Advisor> advisors = new ArrayList<>();
    List<Transcript> transcripts = new ArrayList<>();
    Map<String, Person> userNamePersonMap = new HashMap<>();  //it holds username and password of users
    byte maxCourseNumber = 5;
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

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
    

    public Map<String, Person> getUserNamePersonMap() {
        return userNamePersonMap;
    }

    public byte getMaxCourseNumber() {
        return maxCourseNumber;
    }
}
