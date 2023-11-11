package iteration1;

public class Lecturer extends Person {

    public Lecturer(int ID, String name, String surname, String departmentName, String userName, String password) {
        super(ID, name, surname, departmentName, userName, password);
    }

    public Lecturer() {

    }

    @Override
    boolean login(String userName, String password) {
        return false;
    }
}
