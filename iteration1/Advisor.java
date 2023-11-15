/*
package iteration1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Advisor extends Lecturer implements IDisplayMenu {
    private List<Student> studentsAdvised ;

    private List<Registration> requests;



    public Advisor(int ID, String name, String surname, String userName, String password) {
        super(ID, name, surname, userName, password);
        studentsAdvised = new ArrayList<>();
        requests = new ArrayList<>();
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
    public void printMenu(String menuType) {
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

    public void addRequest(Registration registration) {
        requests.add(registration);
    }
}
*/
package iteration1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Advisor extends Lecturer implements IDisplayMenu {

    private List<Student> studentsAdvised;
    private List<Registration> requestList;
    private int requestNumber;// 2 farklı metodda kullandığım için ekledim



    public Advisor(int ID, String name, String surname, String userName, String password) {
        super(ID, name, surname, userName, password);
        studentsAdvised = new ArrayList<>();
        requestList = new ArrayList<>();
    }

    public void printRequests() {
        System.out.println("List of Requests:");
        System.out.println("0. Back");
        if (requestList.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                System.out.println((i + 1) + ". " + requestList.get(i).getStudent().getName() + " " + requestList.get(i).getStudent().getSurname());
            }
        } else {
            for (int i = 0; i < requestList.size(); i++) {
                System.out.println((i + 1) + ". " + requestList.get(i).getStudent().getName() + " " + requestList.get(i).getStudent().getSurname());
            }
        }
        // Advisor secim yapacak
        System.out.print("Enter the request number you want to evaluate: ");
        Scanner scanner = new Scanner(System.in);
        requestNumber = scanner.nextInt();

        if (requestNumber == 0) {
            return;
        } else {
            //seçilen öğrencinin requestini göster
            System.out.println(requestList.get(requestNumber - 1).getStudent().getName() + " " + requestList.get(requestNumber - 1).getStudent().getSurname() + " wants to take these courses:");
            for (Course course : requestList.get(requestNumber - 1).getStudent().getDraft()) {
                System.out.println(course.getCourseCode() + " " + course.getCourseName());
            }
            replyRequests();
        }
    }

    public void replyRequests() {
        System.out.println("1. Approve Request");
        System.out.println("2. Reject Request");
        System.out.println("3. Back");
        System.out.println("Please select an option: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                requestList.get(requestNumber -1).approveRequest();
                requestList.remove(requestNumber - 1);
                printRequests();
                break;
            case 2:
                requestList.get(requestNumber -1).rejectRequest();
                requestList.remove(requestNumber - 1);
                printRequests();
                break;
            case 3:
                printRequests();
                break;
            default:
                System.out.println("Invalid choice Please select again");
                break;
        }
    }
/* şu an için advisor değerlendirme yapmıyor sonrasında işe yarayabilir
    public void assistWithRegistration(Student student, Course course) {
        boolean result = true;
        if (student.getSelectedCourses.size() > maxNumOfCourses) {// max REGİSTRATİON classta tanımlanabilir
            System.out.println("The number of selected courses is above the limit.");
            result = false;
        }
        if (!(course.hasCapacity)) {
            System.out.println("Course capacity is full");
            result = false;
        }
        if (result)
            System.out.println("There is no problem with requests.");
    }
*/

    @Override
    public void printMenu (String menuType){
        System.out.println("\nAdvisor Menu");
        System.out.println("Please select from the following options:");
        System.out.println("0. Exit");
        System.out.println("1. List requests");
        System.out.println("2. Log out");
        System.out.print("Enter your choice: ");
    }

    public List<Student> getStudentsAdvised () {
        return studentsAdvised;
    }

    public List<Registration> getRequests () {
        return requestList;
    }

    @Override
    boolean login (String userName, String password){
        return this.getUserName().equals(userName) && this.getPassword().equals(password);

    }

    public void addRequest (Registration registration){
        requestList.add(registration);

    }

    public List<Registration> getRequestList() {
        return requestList;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }
}
