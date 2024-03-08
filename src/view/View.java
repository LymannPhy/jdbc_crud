// View.java

package view;

import model.User;

import java.util.Locale;
import java.util.Scanner;

public class View {
    public static void ui() {
        System.out.println(".".repeat(30));
        System.out.println("User Data".toUpperCase(Locale.ROOT));
        System.out.println(".".repeat(30));
        System.out.println("1/ Get All users".toUpperCase(Locale.ROOT));
        System.out.println("2/ Search users by ID".toUpperCase(Locale.ROOT));
        System.out.println("3/ Create a new user".toUpperCase(Locale.ROOT));
        System.out.println("4/ Update user by ID".toUpperCase(Locale.ROOT));
        System.out.println("5/ Delete user by ID".toUpperCase(Locale.ROOT));
    }

    public static int option() {
        System.out.print("Insert Option: ");
        return new Scanner(System.in).nextInt();
    }

    public static User inputUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter User Email: ");
        String userEmail = scanner.nextLine();
        System.out.print("Enter User Password: ");
        String userPassword = scanner.nextLine();
        System.out.print("Enter User Deleted (true/false): ");
        boolean isDeleted = scanner.nextBoolean();
        System.out.print("Enter User Verified (true/false): ");
        boolean isVerified = scanner.nextBoolean();

        return new User(null, null, userName, userEmail, userPassword, isDeleted, isVerified);
    }

    public static int inputUserID() {
        System.out.print("Enter User ID: ");
        return new Scanner(System.in).nextInt();
    }
}
