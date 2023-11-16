package iteration1;

import java.util.ArrayList;
import java.util.List;

public class Lecturer extends Person {

    private List<Course> lessonsTaught;

    //For creating lecturer
    public Lecturer(int ID, String name, String surname) {
        super(ID, name, surname, null,null);
        lessonsTaught = new ArrayList<>();
    }

    //For creating advisor
    public Lecturer(int ID, String name, String surname, String userName, String password) {
        super(ID, name, surname, userName, password);
        lessonsTaught = new ArrayList<>();
    }

    public List<Course> getLessonsTaught() {
        return lessonsTaught;
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }
}
