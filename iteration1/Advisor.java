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

    public Advisor(int ID, String name, String surname, String departmentName, String userName, String password) {
        super(ID, name, surname, departmentName, userName, password);
    }


    public void listRequests() {
        System.out.println("\nList Requests Menu");
        System.out.println("0. Back");
        for (int i = 0; i < requests.size(); i++) {
            System.out.println((i + 1) + ". " + requests.get(i).getStudent().getName() + " " + requests.get(i).getStudent().getSurname());
        }
        System.out.print("Please select a student: ");


        //TODO check if the input is valid
        Scanner scanner = new Scanner(System.in);
        int requestNumber = scanner.nextInt();

        if (requestNumber == 0) {
            return;
        } else {
            //TODO seçilen öğrencinin requestini göster
            System.out.println("1. Approve Request");
            System.out.println("2. Reject Request");
            System.out.println("3. Back");
            System.out.println("Please select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    requests.get(requestNumber).approveRequest(this);
                    listRequests();
                    break;
                case 2:
                    requests.get(requestNumber).rejectRequest(this);
                    listRequests();
                    break;
                case 3:
                    listRequests();
                    break;
                default:
                    System.out.println("Invalid choice Please select again");
                    break;
            }
        }
    }





    @Override
    public void menu(String menuType) {
        System.out.println("\nAdvisor Menu");
        System.out.println("Please select from the following options:");
        System.out.println("0. Exit");
        System.out.println("1. List requests");
        System.out.println("2. Log out");
        System.out.print("Enter your choice: ");
    }

    public List<Student> getStudentsAdvised() {
        return studentsAdvised;
    }

    public List<Registration> getRequests() {
        return requests;
    }

    @Override
    boolean login(String userName, String password) {
        return this.getUserName().equals(userName) && this.getPassword().equals(password);

    }
}
