package iteration2;

import java.util.ArrayList;
import java.util.List;

public class Registration {
    /**
     * Represents a student.
     */
    private Student student;
    /**
     * Represents a list of courses.
     * The list is stored as a private member variable of type {@code List<Course>} and can be accessed using appropriate getter and setter methods.
     */
    private List<Course> courses;

    /**
     * Constructs a new Registration object.
     *
     * @param student the student for whom the registration is being created
     * @param courses the list of courses the student is registering for
     */
    public Registration(Student student, List<Course> courses) {
        this.student = student;
        this.courses = courses;
    }

    /**
     * Approves the request for registration.
     * This method updates the student's transcript and sets the status of the request to "approved".
     * It also clears the student's draft and prints a message confirming the approval.
     */
    public void approveRequest() {
        ConsoleColours.paintYellowMenu();
        System.out.println("Request approved.");
        ConsoleColours.resetColour();
        System.out.println();

        for (Course course : courses) {
            // İlk defa daha önce hiç almadığı ders eklemesi yapıcak.
            if (!student.getTranscript().getStudentCourses().contains(course)) {
                ArrayList<Grade> tempGrade = new ArrayList<Grade>();
                tempGrade.add(null);
                student.getTranscript().getCourseGradeMap().put(course, tempGrade);
                student.getTranscript().getStudentCourses().add(course);
                //TODO add to schedule
                course.setNumberOfStudents(course.getNumberOfStudents() + 1);
            } else {

                List<Grade> myTempList = student.getTranscript().getCourseGradeMap().get(course);

                //Tek bir grade varsa null ise az önce add ile transcripte ekledi şimdi vazgeçti silecek.
                if (myTempList.size() == 1 && myTempList.getFirst() == null) {
                    student.getTranscript().getCourseGradeMap().remove(course);
                    student.getTranscript().getStudentCourses().remove(course);
                    course.setNumberOfStudents(course.getNumberOfStudents() - 1);
                }

                //Daha önce bir notu var ve dersi az önce almış şimdi vazgeçti bu ders ağır gelir dedi sildi
                else if (myTempList.size() > 1 && myTempList.getLast() == null) {
                    myTempList.remove(myTempList.getLast());
                    course.setNumberOfStudents(course.getNumberOfStudents() - 1);
                }

                //Daha önce bir notu var son not -- değil demek ki bir daha dersi alma kararı verecek.
                else if (myTempList.size() >= 1 && myTempList.getLast() != null) {
                    myTempList.add(null);
                    course.setNumberOfStudents(course.getNumberOfStudents() + 1);
                    //TODO add to schedule

                }
//!!
            }
        }
        student.setHasRequest(false);
        student.getDraft().clear();
        (new Notification(student, "Your request has been approved.")).sendNotification();
    }

    /**
     * Rejects a request by a student.
     * Clears the draft and marks the request as rejected.
     */
    public void rejectRequest() {
        ConsoleColours.paintYellowMenu();
        System.out.println("Request rejected.");
        ConsoleColours.resetColour();
        System.out.println();

        student.setHasRequest(false);
        student.getDraft().clear();

        //!!
        (new Notification(student, "Your request has been rejected.")).sendNotification();
    }

    /**
     * Adds a request to the advisor's list of requests and sets the student's request status to true.
     * Prints a message indicating that the request has been sent to the advisor.
     *
     * @param advisor the advisor to send the request to
     */
    public void addRequest(Advisor advisor) {
        advisor.getRequests().add(this);
        student.setHasRequest(true);
        ConsoleColours.paintYellowMenu();
        System.out.println("Request sent to advisor.");
        ConsoleColours.resetColour();
        System.out.println();
    }

    public Student getStudent() {
        return student;
    }

    public List<Course> getCourses() {
        return courses;
    }
}