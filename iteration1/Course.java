package iteration1;

import java.util.ArrayList;
import java.util.List;

/**
 * The Course class represents a course offered by an educational institution.
 * It contains information about the name, code, credit, semester, sessions, and prerequisites of the course.
 */
public class Course {
    private String courseName;
    private String courseCode;
    private int courseCredit;
    private byte semester;
    private Lecturer lecturer;
    private List<CourseSection> courseSections = new ArrayList<>();
    private List<Course> preRequisiteCourses = new ArrayList<>();
    private List<Course> preRequsitieToCourses = new ArrayList<>();


    public Course(String courseName, String courseCode, int courseCredit, byte semester) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.semester = semester;
    }

    public Course(String courseName, String courseCode, int courseCredit) {
    }

    public String getCourseName() {
        return courseName;
    }

    public List getCourseSessions() {
        return courseSections;
    }

    public List<Course> getPreRequisiteCourses() {
        return preRequisiteCourses;
    }

    public void addPreRequisiteCourse(Course course) {
        preRequisiteCourses.add(course);
    }
    public String getCourseCode() {
        return courseCode;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public byte getSemester() {
        return semester;
    }
}
