package view;

import java.awt.desktop.PreferencesEvent;
import java.util.Locale;
import java.util.Scanner;

public class View{
    public static void ui(){
        System.out.println(".".repeat(30));
        System.out.println("User Data".toUpperCase(Locale.ROOT));
        System.out.println(".".repeat(30));
        System.out.println("1/ Get All users".toUpperCase(Locale.ROOT));
        System.out.println("2/ Search users by ID".toUpperCase(Locale.ROOT));
    }
    public static int option(){
        System.out.print("Insert Option: ");
        return new Scanner(System.in).nextInt();
    }
}
