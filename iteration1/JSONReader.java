package iteration1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONReader {
    ObjectMapper mapper;
    JsonNode jsonNode;
    Map<Course, List<String>> coursePrerequisiteCourseCodesMap = new HashMap<>();
    Map<Course, List<String>> courseSectionCodesMap = new HashMap<>();
    Map<Integer, List<String>> lecturerIDCoursesMap = new HashMap<>();
    Map<Integer, List<String>> advisorIDCoursesMap = new HashMap<>();
    Map<Integer, Integer> studentIDAdvisorIDMap = new HashMap<>();
    Map<Integer, List<String>> studentIDDraftMap = new HashMap<>();
    Map<Student, Registration> studentRegistrationMap = new HashMap<>();


    public void start(Department department) throws IOException {
        readJson(department);
        syncObjects(department);
//        writeJson();

    }

    public void writeJson() {
        writeJson("students");
        writeJson("courses");
    }

    public void writeJson(String jsonType) {
        Gson gson = new Gson();
        String json = gson.toJson(jsonType);
        String fileName = "iteration1/jsons/" + jsonType + ".json";
        try {
            // Write the JSON string to a file
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readJson(Department department) throws IOException {
        readCourses(department);
        readCourseSections(department); //No need to sync
        readLecturers(department);
        readStudents(department);
        readRequests(department);
        readAdvisors(department);
    }

    public void readCourses(Department department) throws IOException {
        mapper = new ObjectMapper();
        // Parse the JSON file into a Java object.
        jsonNode = mapper.readTree(new File("iteration1/jsons/courses.json"));
        // Get the students array.
        JsonNode coursesArray = jsonNode;

        for (JsonNode course : coursesArray) {
            //Construction part
            String courseName = course.get("courseName").asText();
            String courseCode = course.get("courseCode").asText();
            int courseCredit = course.get("courseCredit").asInt();
            byte gradeLevel = (byte) course.get("gradeLevel").asInt();

            Course course1 = new Course(courseName, courseCode, courseCredit, gradeLevel);
            department.getCourses().add(course1);
            department.getCourseCodeCourseMap().put(courseCode, course1);
            //////////////////////////////////////////////

            //sync part
            List<String> preRequisiteCourseCodes = new ArrayList<>();
            List<String> courseSectionCodes = new ArrayList<>();

//            readCourseSections(department);

            //TODO tüm courses
            JsonNode preRequisiteCourseCodesArray = course.get("preRequisiteCourseCodes");
            for (JsonNode preRequisiteCourseCode : preRequisiteCourseCodesArray) {
                preRequisiteCourseCodes.add(preRequisiteCourseCode.asText());
            }
            coursePrerequisiteCourseCodesMap.put(course1, preRequisiteCourseCodes);
            JsonNode courseSectionCodesArray = course.get("courseSectionCodes");
            for (JsonNode courseSectionCode : courseSectionCodesArray) {
                courseSectionCodes.add(courseSectionCode.asText());
            }
            courseSectionCodesMap.put(course1, courseSectionCodes);
            //////////////////////////////////////////////
            System.out.printf("courseName: %s, courseCode: %s, courseCredit: %d\n", courseName, courseCode, courseCredit);
        }
    }

    public void readCourseSections(Department department) throws IOException {
        mapper = new ObjectMapper();
        jsonNode = mapper.readTree(new File("iteration1/jsons/courseSections.json"));
        JsonNode courseSectionsArray = jsonNode;

        for (JsonNode courseSection : courseSectionsArray) {
            //Construction part
            String courseSectionCode = courseSection.get("courseSectionCode").asText();
            String courseCode = courseSection.get("courseCode").asText();
            String day = courseSection.get("day").asText();
            int hour = courseSection.get("hour").asInt();
            Course course = department.getCourseCodeCourseMap().get(courseCode);
            CourseSection courseSection1 = new CourseSection(course, courseSectionCode, day, hour);
            department.getCourseSections().add(courseSection1);
            department.getSectionCodeCourseMap().put(courseSectionCode, course);
            department.getSectionCourseMap().put(courseSection1, course);
            //////////////////////////////////////////////
            System.out.printf("courseSection: %s, courseCode: %s, day: %s, hour: %d\n", courseSectionCode, courseCode, day, hour);
        }
    }

    public void readLecturers(Department department) throws IOException {
        mapper = new ObjectMapper();
        jsonNode = mapper.readTree(new File("iteration1/jsons/lecturers.json"));
        JsonNode lecturersArray = jsonNode;

        for (JsonNode lecturer : lecturersArray) {
            //Construction part
            int id = lecturer.get("lecturerID").asInt();
            String name = lecturer.get("name").asText();
            String surname = lecturer.get("surname").asText();

            Lecturer lecturer1 = new Lecturer(id, name, surname);
            department.getLecturers().add(lecturer1);
            lecturer1.setDepartment(department);
            //////////////////////////////////////////////

            List<String> lessonsTaught = new ArrayList<>();

            JsonNode lessonsTaughtArray = lecturer.get("lessonsTaught");
            for (JsonNode lessonTaught : lessonsTaughtArray) {
                lessonsTaught.add(lessonTaught.asText());
            }
            lecturerIDCoursesMap.put(id, lessonsTaught);

            System.out.printf("lecturerID: %d, name: %s, surname: %s\n", id, name, surname);
        }
    }

    public void readStudents(Department department) throws IOException {
        mapper = new ObjectMapper();

        // Parse the JSON file into a Java object.
        jsonNode = mapper.readTree(new File("iteration1/jsons/students.json"));

        // Get the students array.
        JsonNode studentsArray = jsonNode;

        for (JsonNode student : studentsArray) {
            //Construction part
            int id = student.get("studentID").asInt();
            String name = student.get("name").asText();
            String surname = student.get("surname").asText();
            String userName = student.get("userName").asText();
            String password = student.get("password").asText();
            int gradeLevel = student.get("gradeLevel").asInt();
            Student student1 = new Student(id, name, surname, userName, password, (byte) gradeLevel);
            student1.setDepartment(department);
            department.getStudents().add(student1);
            department.getUserNamePersonMap().put(userName, student1);
            department.getStudentIDStudentMap().put(id, student1);
            //////////////////////////////////////////////

            //sync part
            int advisorID = student.get("advisorID").asInt();
            studentIDAdvisorIDMap.put(id, advisorID);

            List<String> draft = new ArrayList<>();
            JsonNode draftArray = student.get("draft");
            for (JsonNode draftCourse : draftArray) {
                draft.add(draftCourse.asText());
            }
            if (draft.isEmpty()) {
                student1.setHasRequest(false);
            } else {
                student1.setHasRequest(true);
                studentIDDraftMap.put(id, draft);
            }

            //////////////////////////////////////////////

            readTranscript(department, student1);

            System.out.printf("id: %d, name: %s, surname: %s, userName: %s, password: %s, gradeLevel: %d\n",
                    id, name, surname, userName, password, gradeLevel);
        }
        System.out.println();
    }

    public void readTranscript(Department department, Student student) throws IOException {
        mapper = new ObjectMapper();

        // Parse the JSON file into a Java object.
        jsonNode = mapper.readTree(new File("iteration1/jsons/Transcripts/" + student.getID() + ".json"));
        Map<Course, Grade> courseGradeMap = new HashMap<>();
        List<Course> studentCourses = new ArrayList<>();

        // Get the students array.
        JsonNode transcript = jsonNode;
        int studentID = transcript.get("studentID").asInt();
        JsonNode courses = transcript.get("courses");
        for (JsonNode course : courses) {
            String letterGrade = course.get("letterGrade").asText();
            String courseCode = course.get("courseCode").asText();
            Course course1 = department.getCourseCodeCourseMap().get(courseCode);
            studentCourses.add(course1);
            if (letterGrade.equals("null")) {
                courseGradeMap.put(course1, null);
            } else {
                courseGradeMap.put(course1, new Grade(letterGrade));
            }
        }
        Transcript transcript1 = new Transcript(student);
        department.getTranscripts().add(transcript1);
        transcript1.setStudentCourses(studentCourses);
        student.setTranscript(transcript1);
        student.getTranscript().setCourseGradeMap(courseGradeMap);
        transcript1.calculateValues();
    }


    public void readRequests(Department department) throws IOException {
        mapper = new ObjectMapper();

        // Parse the JSON file into a Java object.
        jsonNode = mapper.readTree(new File("iteration1/jsons/requests.json"));

        // Get the students array.
        JsonNode requestsArray = jsonNode;
        List<Course> draftCourses = new ArrayList<>();
        for (JsonNode request : requestsArray) {
            int studentID = request.get("studentID").asInt();
            JsonNode courseCodesArray = request.get("courseCodes");
            for (JsonNode courseCode : courseCodesArray) {
                Course course1 = department.getCourseCodeCourseMap().get(courseCode.asText());
                draftCourses.add(course1);
            }
            Student student = department.getStudentIDStudentMap().get(studentID);
            student.setDraft(draftCourses);
            studentRegistrationMap.put(student, new Registration(student, draftCourses));
        }
    }


    public void readAdvisors(Department department) throws IOException {
        mapper = new ObjectMapper();

        // Parse the JSON file into a Java object.
        jsonNode = mapper.readTree(new File("iteration1/jsons/advisors.json"));

        // Get the students array.
        JsonNode advisorsArray = jsonNode;

        for (JsonNode advisor : advisorsArray) {
            //Construction part
            int id = advisor.get("advisorID").asInt();
            String name = advisor.get("name").asText();
            String surname = advisor.get("surname").asText();
            String userName = advisor.get("userName").asText();
            String password = advisor.get("password").asText();
            Advisor advisor1 = new Advisor(id, name, surname, userName, password);
            department.getAdvisors().add(advisor1);
            advisor1.setDepartment(department);
            department.getAdvisorIDAdvisorMap().put(id, advisor1);
            department.getUserNamePersonMap().put(userName, advisor1);

            List<String> lessonsTaught = new ArrayList<>();

            JsonNode lessonsTaughtArray = advisor.get("lessonsTaught");
            for (JsonNode lessonTaught : lessonsTaughtArray) {
                lessonsTaught.add(lessonTaught.asText());
            }
            advisorIDCoursesMap.put(id, lessonsTaught);
            //////////////////////////////////////////////

            System.out.printf("id: %d, name: %s, surname: %s, userName: %s, password: %s\n", id, name, surname, userName, password);
        }
    }

    public void syncObjects(Department department) throws IOException {
        //sync for courses
        for (Course course : department.getCourses()) {
            for (String courseCode : coursePrerequisiteCourseCodesMap.get(course)) {
                course.getPreRequisiteCourses().add(department.getCourseCodeCourseMap().get(courseCode));
            }
            for (String courseSectionCode : courseSectionCodesMap.get(course)) {
                course.getCourseSections().add(department.getSectionCodeCourseMap().get(courseSectionCode));
            }
        }

        //sync for lecturers
        for (Lecturer lecturer : department.getLecturers()) {
            for (String courseCode : lecturerIDCoursesMap.get(lecturer.getID())) {
                lecturer.getLessonsTaught().add(department.getCourseCodeCourseMap().get(courseCode));
            }
            for (Course course : lecturer.getLessonsTaught()) {
                course.setLecturer(lecturer);
            }
        }

        //sync for students
        for (Student student : department.getStudents()) {
            student.setAdvisor(department.getAdvisorIDAdvisorMap().get(studentIDAdvisorIDMap.get(student.getID())));

            if (studentIDDraftMap.get(student.getID()) != null) {
                for (String courseCode : studentIDDraftMap.get(student.getID())) {
                    student.getDraft().add(department.getCourseCodeCourseMap().get(courseCode));
                }
            }
        }

        //sync for advisors
        for (Student student : department.getStudents()) {
            Advisor advisor = department.getAdvisorIDAdvisorMap().get(studentIDAdvisorIDMap.get(student.getID()));
            advisor.getStudentsAdvised().add(student);
            if (studentRegistrationMap.get(student) != null)
                advisor.getRequestList().add(studentRegistrationMap.get(student));
        }
        for (Advisor advisor : department.getAdvisors()) {
            for (String courseCode : advisorIDCoursesMap.get(advisor.getID())) {
                advisor.getLessonsTaught().add(department.getCourseCodeCourseMap().get(courseCode));
            }
        }

    }

}
