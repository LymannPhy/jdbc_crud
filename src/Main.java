import controller.UserController;
import view.View;

import java.util.Scanner;

public class Main {
    private final static UserController userController = new UserController();
    public static void main(String[] args) {
        while (true){
            View.ui();
            System.out.println("===");
            switch (View.option()){
                case 1->{
                    userController.getAllUsers().forEach(System.out::println);
                }
                case 2->{
                    System.out.print("> Insert user ID: ");
                    System.out.println(userController.searchUserByID(new Scanner(System.in).nextInt()));
                }
                default -> {
                    System.out.println("No option.");
                }
            }
        }
    }
}
