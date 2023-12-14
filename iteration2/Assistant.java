package iteration2;

public class Assistant extends Person {

    Assistant(int id, String name, String surname) {
        super(id, name, surname, null, null);
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }
}
