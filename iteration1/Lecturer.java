package iteration1;

import java.util.ArrayList;
import java.util.List;

public class Lecturer extends Person {

    List<Course> lessonsTaught;
    public Lecturer(int ID, String name, String surname, String userName, String password) {
        super(ID, name, surname, userName, password);
        lessonsTaught = new ArrayList<>();
    }


    @Override
    boolean login(String userName, String password) {
        return false;
    }
}
