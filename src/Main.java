import controller.UserController;
import model.User;
import model.UserService;
import model.UserServiceImp;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;
import view.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private final static UserService userService = new UserServiceImp(); // Create an instance of UserService
    private final static UserController userController = new UserController(userService);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            View.ui();
            System.out.println("===");
            int option = getUserOption();
            switch (option) {
                case 1 -> {
                    Table table = new Table(7, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE);
                    table.addCell("ID");
                    table.addCell("UUID");
                    table.addCell("Username");
                    table.addCell("UserEmail");
                    table.addCell("User Password");
                    table.addCell("Is Deleted");
                    table.addCell("Is Verified");
                    userController.getAllUsers().forEach(e -> {
                        table.addCell(e.getId().toString());
                        table.addCell(e.getUuid());
                        table.addCell(e.getUserName());
                        table.addCell(e.getUserEmail());
                        table.addCell(e.getUserPassword());
                        table.addCell(e.getIsDeleted().toString());
                        table.addCell(e.getIsVerified().toString());
                    });
                    System.out.println(table.render());
                }
                case 2 -> {
                    System.out.print("> Insert user ID: ");
                    System.out.println(userController.searchUserByID(new Scanner(System.in).nextInt()));
                }
                case 3 -> createUser();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                case 6 -> {
                    exit = true;
                    System.out.println("Exiting the program. Thank you for using it!");
                }
                default -> {
                    System.out.println("No option.");
                }
            }
        }
    }
    private static int getUserOption() {
        int option = 0;
        boolean isValidInput = false;
        Scanner scanner = new Scanner(System.in);
        while (!isValidInput) {
            try {
                System.out.print("Enter your choice: ");
                option = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return option;
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
