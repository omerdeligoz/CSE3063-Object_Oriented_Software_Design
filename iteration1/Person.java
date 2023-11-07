package iteration1;

public abstract class Person {
    private int ID;
    private String name;
    private String surname;
    private Department departmentObject;
    private String department;
    private String userName;
    private String password;

    public Person(int ID, String name, String surname, String department, String userName, String password) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.department = department;
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
    public Person() {}
    //
    //
    //

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartmentObject(Department departmentObject) {
        this.departmentObject = departmentObject;
    }

    public Department getDepartmentObject() {
        return departmentObject;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
