package iteration1;


import java.util.ArrayList;
import java.util.Scanner;

public class Advisor extends Person implements ILogin {
    private ArrayList<Student> studentsAdvised;

    private ArrayList<Registration> requests;
    //TODO request type'ı belirlenecek

    Advisor() {
    }
    public Advisor(String userName, String password) {
        super(userName, password);
        studentsAdvised = new ArrayList<>();
        requests = new ArrayList<>();
    }

    public void listRequests() {
        System.out.println("List Requests");
        System.out.println("İşlem yapmak istediğiniz request'ın numarasını giriniz: ");
        Scanner scanner = new Scanner(System.in);
        int requestNumber = scanner.nextInt();
        requests.get(requestNumber).showRequest(); //TODO registration class'ında showRequest() methodu oluşturulacak

        System.out.println("Request'ı onaylamak için 1, reddetmek için 2 giriniz: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            requests.get(requestNumber).approveRequest(); //TODO registration class'ında approveRequest() methodu oluşturulacak
        } else if (choice == 2) {
            requests.get(requestNumber).rejectRequest(); //TODO registration class'ında rejectRequest() methodu oluşturulacak
        } else {
            System.out.println("Hatalı giriş yaptınız.");
        }
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }


}
