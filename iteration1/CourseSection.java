package iteration1;

import java.util.ArrayList;
import java.util.List;

public class CourseSection extends Course {
    private String courseSectionCode;
    private String day;
    private int hour;
    private List students;


    public CourseSection(Course course, String courseSessionCode, String day, int hour) {
        super(course.getCourseName(), course.getCourseCode(), course.getCourseCredit(), course.getGradeLevel());
        this.courseSectionCode = courseSessionCode;
        this.day = day;
        this.hour = hour;
        students = new ArrayList<>();
    }

    public String getCourseSectionCode() {
        return courseSectionCode;
    }
}
