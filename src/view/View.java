// View.java

package view;

import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Locale;
import java.util.Scanner;

public class View {
    public static void ui() {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        System.out.println("********************** Application Menu **********************");
        table.addCell("1. Display All Users");
        table.addCell("2. Search User By ID");
        table.addCell("3. Create User");
        table.addCell("4. Update User Info");
        table.addCell("5. Delete");
        table.addCell("6. Exit the program");
        System.out.println(table.render());
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
