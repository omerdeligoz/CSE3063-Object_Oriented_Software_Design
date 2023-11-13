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
    private byte gradeLevel;
    private Lecturer lecturer;
    private List<CourseSection> courseSections;
    private List<Course> preRequisiteCourses;
    private List<Course> preRequsitieToCourses;


    public Course(String courseName, String courseCode, int courseCredit, byte gradeLevel) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.gradeLevel = gradeLevel;
        courseSections = new ArrayList<>();
        preRequisiteCourses = new ArrayList<>();
        preRequsitieToCourses = new ArrayList<>();
    }

    public void addPreRequisiteCourse(Course course) {
        preRequisiteCourses.add(course);
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

    public String getCourseCode() {
        return courseCode;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public byte getGradeLevel() {
        return gradeLevel;
    }
}
