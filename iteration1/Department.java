package iteration1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Department {
    private String departmentName;
    private List<Advisor> advisors;
    private List<Transcript> transcripts;
    private List<Course> courses;
    private List<Student> students;
    private List<CourseSection> courseSections;
    private List<Lecturer> lecturers;
    private Map<String, Person> userNamePersonMap;  //it holds username and password of users
    private Map<String, Course> courseCodeCourseMap; //it holds course code and course
    private Map<String, Student> studentIDStudentMap; //it holds student id and student
    private Map<String, Advisor> advisorIDAdvisorMap; //it holds advisor id and advisor
    private byte maxCourseNumber = 5;



    public Department(String departmentName) {
        this.departmentName = departmentName;
        advisors = new ArrayList<>();
        transcripts = new ArrayList<>();
        courses = new ArrayList<>();
        students = new ArrayList<>();
        courseSections = new ArrayList<>();
        lecturers = new ArrayList<>();
        userNamePersonMap = new HashMap<>();
        courseCodeCourseMap = new HashMap<>();
        studentIDStudentMap = new HashMap<>();
        advisorIDAdvisorMap = new HashMap<>();
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

}
