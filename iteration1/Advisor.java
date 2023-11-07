package iteration1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Advisor extends Lecturer implements ILogin {
    private List<Student> studentsAdvised = new ArrayList<>();

    private List<Registration> requests = new ArrayList<>();

    public Advisor() {
        super();

    }

    public Advisor(int ID, String name, String surname, String department, String userName, String password) {
        super(ID, name, surname, department, userName, password);
    }


    public void listRequests() {
        System.out.println("\nList Requests Menu");
        for (int i = 0; i < requests.size(); i++) {
            System.out.println((i + 1) + ". " + requests.get(i).getStudent().getName() + " " + requests.get(i).getStudent().getSurname() + " - " + requests.get(i).getStudent().getDraft().toString());
        }
        System.out.print("Please select a request: ");
        Scanner scanner = new Scanner(System.in);
        int requestNumber = scanner.nextInt();
        System.out.println("1. Approve Request");
        System.out.println("2. Reject Request");
        System.out.println("3. Back");
        System.out.println("Please select an option: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                requests.get(requestNumber).approveRequest();
                break;
            case 2:
                requests.get(requestNumber).rejectRequest();
                break;
            case 3:
                listRequests();
                break;
            default:
                System.out.println("Invalid choice Please select again");
                break;
        }
    }

    @Override
    public boolean login(String userName, String password) {
        return this.getUserName().equals(userName) && this.getPassword().equals(password);
    }

    @Override
    public void showMenu() {

    }

    public List<Student> getStudentsAdvised() {
        return studentsAdvised;
    }

    public List<Registration> getRequests() {
        return requests;
    }
}
