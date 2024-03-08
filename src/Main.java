import controller.UserController;
import model.User;
import model.UserService;
import model.UserServiceImp;
import view.View;

import java.util.Scanner;

public class Main {
    private final static UserService userService = new UserServiceImp(); // Create an instance of UserService
    private final static UserController userController = new UserController(userService);

    public static void main(String[] args) {
        while (true) {
            View.ui();
            System.out.println("===");
            switch (View.option()) {
                case 1 -> {
                    userController.getAllUsers().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.print("> Insert user ID: ");
                    System.out.println(userController.searchUserByID(new Scanner(System.in).nextInt()));
                }
                case 3 -> createUser();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                default -> {
                    System.out.println("No option.");
                }
            }
        }
    }


    private static void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter user email: ");
        String userEmail = scanner.nextLine();
        System.out.print("Enter user password: ");
        String userPassword = scanner.nextLine();
        // You may add further inputs as needed

        User newUser = new User(null, null, userName, userEmail, userPassword, false, false);
        boolean success = userController.createUser(newUser);
        if (success) {
            System.out.println("User created successfully.");
        } else {
            System.out.println("Failed to create user.");
        }
    }

    private static void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        User userToUpdate = userController.searchUserByID(userId);
        if (userToUpdate != null) {
            System.out.print("Enter new user name: ");
            String newUserName = scanner.nextLine();
            System.out.print("Enter new user email: ");
            String newUserEmail = scanner.nextLine();

            userToUpdate.setUserName(newUserName);
            userToUpdate.setUserEmail(newUserEmail);

            boolean success = userController.updateUser(userToUpdate);
            if (success) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("Failed to update user.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID to delete: ");
        int userId = scanner.nextInt();
        boolean success = userController.deleteUser(userId);
        if (success) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
}
