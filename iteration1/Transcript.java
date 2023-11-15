package iteration1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Transcript {
    private List<Course> studentCourses;
    private double cgpa;
    private byte gradeLevel;
    private int takenCredits;

    private int completedCredits;
    private Student student;
    private Map<Course, Grade> courseGradeMap;


    /**
     * Constructs a new Transcript object for the given student.
     *
     * @param student the Student object for which the Transcript is being created
     */
    public Transcript(Student student) {
        this.studentCourses = new ArrayList<>();
        this.student = student;
        this.gradeLevel = student.getGradeLevel();
        this.cgpa = calculateCgpa();
        this.takenCredits = calculateTakenCredits();
        this.completedCredits = calculateCompletedCredits();
        this.courseGradeMap = new HashMap<>();
    }

    public void calculateValues() {
        this.cgpa = calculateCgpa();
        this.takenCredits = calculateTakenCredits();
        this.completedCredits = calculateCompletedCredits();
    }

    /**
     * Calculates the CGPA (Cumulative Grade Point Average) for the student.
     *
     * @return The calculated CGPA.
     */
    public double calculateCgpa() {
        if (studentCourses.isEmpty()) {
            return 0;
        }
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

    /**
     * Calculates the total number of credits taken by the student.
     *
     * @return The total number of credits taken.
     */
    public int calculateTakenCredits() {
        for (Course course : studentCourses) {
            takenCredits += course.getCourseCredit();
        }
        return takenCredits;
    }

    /**
     * Calculates the completed credits of a student based on their course grades and credits.
     *
     * @return The total completed credits.
     */
    public int calculateCompletedCredits() {
        completedCredits = takenCredits;
        for (Course course : studentCourses) {
            if (courseGradeMap.get(course) == null
                    || courseGradeMap.get(course).getLetterGrade().equals("FF")
                    || courseGradeMap.get(course).getLetterGrade().equals("FD")) {
                completedCredits = takenCredits - course.getCourseCredit();
            }
        }
        return completedCredits;
    }

    /**
     * Reads a transcript file and creates a new Student object based on the information in the file.
     *
     * @param file the path of the transcript file
     * @return a Student object with the information from the transcript file
     * @throws IOException if an I/O error occurs while reading the file
     */
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

    /**
     * Generates a JSON transcript for the student and saves it to a file.
     * The transcript includes information such as student name, ID, GPA, completed and taken credits,
     * current semester, department, and a list of courses taken by the student with their respective
     * course ID, credits, grade, and name.
     */
    public void showTranscript() {
        System.out.println("\nTranscript for " + student.getName() + " " + student.getSurname() + ":");
        System.out.println("Student ID: " + student.getID());
        System.out.println("CGPA: " + cgpa);
        System.out.println("Completed Credits: " + calculateCompletedCredits());
        System.out.println("Taken Credits: " + calculateTakenCredits());
        System.out.println("Grade Level: " + gradeLevel);
        System.out.println("Department: " + student.getDepartmentName());
        System.out.println("Courses:");

        //TODO fix printing
        System.out.format("%-15s%-38s%-20s%-15s\n", "Course Code", "Course Name", "Course Credit", "Grade");
        System.out.println("-------------------------------------------------------------------------------");
        for (Course course : studentCourses) {
            if (courseGradeMap.get(course) == null) {
                System.out.format("%-15s%-45s%-15s%-15s\n", course.getCourseCode(), course.getCourseName(), course.getCourseCredit(), "--");
//                System.out.format(course.getCourseCode() + "\t\t" + course.getCourseName() + "\t\t\t\t\t\t\t" + course.getCourseCredit());
            } else {
                System.out.format("%-15s%-45s%-15s%-15s\n", course.getCourseCode(), course.getCourseName(), course.getCourseCredit(), courseGradeMap.get(course).getLetterGrade());
//                System.out.println(course.getCourseCode() + "\t\t" + course.getCourseName() + "\t\t\t\t\t\t\t" + course.getCourseCredit() + "\t\t" + courseGradeMap.get(course).getLetterGrade());
            }
        }
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Map<Course, Grade> getCourseGradeMap() {
        return courseGradeMap;
    }

    public void courseStatusCheck() {
        List<Course> successfulCourses = new ArrayList<>();
        List<Course> failedCourses = new ArrayList<>();
        List<Course> ongoingCourses = new ArrayList<>();
        for (Course course : studentCourses) {
            if (courseGradeMap.get(course).getLetterGrade() == null) {
                ongoingCourses.add(course);
            } else if (courseGradeMap.get(course).getLetterGrade().equals("FF")
                    || courseGradeMap.get(course).getLetterGrade().equals("FD")) {
                failedCourses.add(course);
            } else {
                successfulCourses.add(course);
            }
        }
        System.out.println("\nSuccessful Courses:");
        for (Course course : successfulCourses) {
            System.out.println(course.getCourseCode() + " " + course.getCourseName());
        }
        System.out.println("Failed Courses:");
        for (Course course : failedCourses) {
            System.out.println(course.getCourseCode() + " " + course.getCourseName());
        }
        System.out.println("Ongoing Courses:");
        for (Course course : ongoingCourses) {
            System.out.println(course.getCourseCode() + " " + course.getCourseName());
        }
    }

    public List<Course> getStudentCourses() {
        return studentCourses;
    }

    public void setTakenCredits(int takenCredits) {
        this.takenCredits = takenCredits;
    }

    public void setCourseGradeMap(Map<Course, Grade> courseGradeMap) {
        this.courseGradeMap = courseGradeMap;
    }

    public void setStudentCourses(List<Course> studentCourses) {
        this.studentCourses = studentCourses;
    }
}


/*
 JSONObject transcriptJSON = new JSONObject();

 transcriptJSON.put("studentName", student.getName());
 transcriptJSON.put("studentSurname", student.getSurname());
 transcriptJSON.put("studentId", student.getID());
 transcriptJSON.put("studentGpa", cgpa);
 transcriptJSON.put("takenCredits", calculateTakenCredits());
 transcriptJSON.put("completedCredits", calculateCompletedCredits());
 transcriptJSON.put("currentSemester", gradeLevel);
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

 String filePath = "iteration1/jsons/Transcripts/" + student.getID() + ".json";
 try (FileWriter file = new FileWriter(filePath)) {
 file.write(transcriptJSON.toString(4));
 file.flush();
 System.out.println("JSON verisi başarıyla dosyaya yazıldı: " + filePath);
 } catch (IOException e) {
 e.printStackTrace();
 }
 */
