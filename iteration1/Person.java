package iteration1;

public abstract class Person {
    private int ID;
    private String name;
    private String surname;
    private Department departmentObject;
    private String departmentName;
    private String userName;
    private String password;

    public Person(int ID, String name, String surname, String departmentName, String userName, String password) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.departmentName = departmentName;
        this.userName = userName;
        this.password = password;
    }

    //
    //
    //No need these statements
    public Person(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //
    //
    //No need
    public Person() {
    }
    //
    //
    //

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartmentObject() {
        return departmentObject;
    }

    public void setDepartmentObject(Department departmentObject) {
        this.departmentObject = departmentObject;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    abstract boolean login(String userName, String password);

}
