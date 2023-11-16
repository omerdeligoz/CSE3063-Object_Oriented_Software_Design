package iteration1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONWriter {
    Department department;
    ObjectMapper objectMapper;
    JsonNode jsonNode;

    public void start(Department department) {
        this.department = department;
        writeJson();
    }

    public void writeJson() {
        writeStudents();
        writeLecturers();
        writeAdvisors();
        writeCourses();
        writeCourseSections();
        writeRequests();
//        writeTranscripts();
    }

    public void writeTranscripts() {
        // Specify the path to your JSON file
        String filePath;

        for (Student student : department.getStudents()) {
//            filePath = "src/iteration1/JSONFiles/Transcripts/" + student.getID() + ".json";
            filePath = "iteration1/jsons/Transcripts/test.json";
            try {
                // Create ObjectMapper
                objectMapper = new ObjectMapper();

                // Read JSON file into JsonNode
                jsonNode = objectMapper.readTree(new File(filePath));

                // Edit the value of "takenCredits"
                int newTakenCredits = student.getTranscript().getTakenCredits();
                int newCompletedCredits = student.getTranscript().getCompletedCredits();
                double newCgpa = student.getTranscript().getCgpa();
//                TODO for testing
//                int newTakenCredits = 123; // Replace with the desired value
//                int newCompletedCredits = 456;
//                double newCgpa = 789;

                ((ObjectNode) jsonNode).put("takenCredits", newTakenCredits);
                ((ObjectNode) jsonNode).put("completedCredits", newCompletedCredits);
                ((ObjectNode) jsonNode).put("cgpa", newCgpa);

                // Create a new array of courses
                ArrayNode newCoursesArray = JsonNodeFactory.instance.arrayNode();

                for (Course course : student.getTranscript().getStudentCourses()) {
                    ObjectNode courseNode = JsonNodeFactory.instance.objectNode();
                    courseNode.put("courseCode", course.getCourseCode());
                    if (student.getTranscript().getCourseGradeMap().get(course) != null) {
                        courseNode.put("grade", (JsonNode) null);
                    } else {
                        courseNode.put("letterGrade", student.getTranscript().getCourseGradeMap().get(course).getLetterGrade());
                    }
                    newCoursesArray.add(courseNode);
                }

                // Replace the existing "courses" array with the new array
                ((ObjectNode) jsonNode).set("courses", newCoursesArray);

                // Write the updated JsonNode back to the file
                objectMapper.writeValue(new File(filePath), jsonNode);

                System.out.println("JSON file updated successfully.");
            } catch (IOException e) {
                System.out.println("File not found");
                System.exit(0);
            }
        }
    }

    public void writeRequests() {
        // Specify the path to your JSON file
        String filePath = "iteration1/jsons/requests.json";

        try {
            objectMapper = new ObjectMapper();
            ArrayNode jsonArray = (ArrayNode) objectMapper.readTree(new File(filePath));

            for(Student student : department.getStudents()){
                List<String> courseCodes = new ArrayList<>();

                for(Course course : student.getDraft()){
                    ObjectNode requestNode = JsonNodeFactory.instance.objectNode();
                    requestNode.put("studentID", student.getID());
                    courseCodes.add(course.getCourseCode());
                    jsonArray.add(requestNode);
                }
                JsonNode jsonNode1 = jsonArray.get(jsonArray.size()-1);
            }




            List<String> courseCodes = new ArrayList<>();
            for (JsonNode jsonNode : jsonArray) {
                int studentID = jsonNode.get("studentID").asInt();
                ArrayNode courseCodesArray = (ArrayNode) jsonNode.get("courses");
                for (JsonNode courseCode : courseCodesArray) {
                    courseCodes.add(courseCode.asText());
                }
            }

            // Write the updated ArrayNode back to the file
            objectMapper.writeValue(new File(filePath), jsonArray);

            System.out.println("JSON file updated successfully.");
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    public void writeCourseSections() {
    }

    public void writeCourses() {
    }

    public void writeAdvisors() {
    }

    public void writeLecturers() {
    }

    public void writeStudents() {
    }

}


