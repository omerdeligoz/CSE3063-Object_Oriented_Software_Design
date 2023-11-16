package unnecessary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class StudentJsonGenerator {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode studentsArray = objectMapper.createArrayNode();

        Random random = new Random();
        int numStudents = 10;

        for (int i = 1; i <= numStudents; i++) {
            ObjectNode studentNode = objectMapper.createObjectNode();
            studentNode.put("studentID", i);
            studentNode.put("name", "Student" + i);
            studentNode.put("advisor", "Advisor" + (i % 3 + 1));
            studentNode.put("coursesTaken", getRandomCourses());
            studentNode.put("transcript", "Transcript for Student" + i);
            studentNode.put("gradeLevel", random.nextInt(4) + 1);

            studentsArray.add(studentNode);
        }

        ObjectNode root = objectMapper.createObjectNode();
        root.set("students", studentsArray);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("randomStudents.json"), root);
            System.out.println("JSON file 'students.json' generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayNode getRandomCourses() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode coursesArray = objectMapper.createArrayNode();

        Random random = new Random();
        int numCourses = random.nextInt(5) + 1;

        for (int i = 0; i < numCourses; i++) {
            ObjectNode courseNode = objectMapper.createObjectNode();
            courseNode.put("courseID", "Course" + (i + 1));
            courseNode.put("grade", getRandomGrade());
            coursesArray.add(courseNode);
        }

        return coursesArray;
    }

    private static ObjectNode getRandomGrade() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode gradeNode = objectMapper.createObjectNode();

        Random random = new Random();
        gradeNode.put("letter", getRandomLetterGrade());
        gradeNode.put("points", 2.0 + random.nextDouble() * 2.0);

        return gradeNode;
    }

    private static String getRandomLetterGrade() {
        String[] letterGrades = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
        Random random = new Random();
        int index = random.nextInt(letterGrades.length);
        return letterGrades[index];
    }
}
