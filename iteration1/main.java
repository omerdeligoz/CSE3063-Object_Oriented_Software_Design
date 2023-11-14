package iteration1;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
//        JSONFileCreator jsonFileCreator = new JSONFileCreator();
//        jsonFileCreator.createCourseSections();
        CourseRegistrationSystem courseRegistrationSystem = new CourseRegistrationSystem();
        courseRegistrationSystem.start();

    }
}
