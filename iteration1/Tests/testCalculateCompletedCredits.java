
package iteration1.Tests;

import iteration1.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranscriptTest {
    @Test
    void testCalculativeCompletedCredits() {

        // Prepare data
        Course course1 = new Course("courseName1", "CSE100", 3, (byte) 3);
        Course course2 = new Course("courseName2", "CSE101", 3, (byte) 5);
        Course course3 = new Course("courseName3", "CSE102", 3, (byte) 7);
        Student student = new Student(150120000,"name","surname","username","password", (byte) 3);
        Transcript transcript = new Transcript(student);

        transcript.getStudentCourses().add(course1);
        transcript.getStudentCourses().add(course2);
        transcript.getStudentCourses().add(course3);

        transcript.getCourseGradeMap().put(course1, new Grade("AA"));
        transcript.getCourseGradeMap().put(course2, new Grade("FD")); // This course is not completed
        transcript.getCourseGradeMap().put(course3, new Grade("BA"));

        transcript.setTakenCredits(9);

        // Call the method and compare the result
        int result = transcript.calculateCompletedCredits();

        // Assert the result
        assertEquals(6, result, "The method should calculate completed credits correctly.");
    }
}
