package main.API; /**
 * @author Max Day
 * Created At: 2023/08/18
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class MainPage extends Thread {

    public MainPage() {
    }

    public static void mainPage() {
        welcome();
        new MainPage();
        Thread t1 = new Thread();
        MainPage main = new MainPage();
        main.start();
        new Thread(t1).start();
    }

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter \u001B[34m(1)\033[0m to launch the menu or any other key to exit");
            String[] stInput = reader.readLine().split(" ");
            if (stInput[0].equalsIgnoreCase("1")) {
                printOption();
            } else {
                System.err.println("Alternate key was entered. Exiting program");
                System.exit(420);
            }
            while (true) {// better than !stopped
                String[] strInput = reader.readLine().split(" ");
                if (strInput.length <= 0) continue;// if the line is greater than 0 then proceed with check
                switch (strInput[0].toLowerCase(Locale.ROOT)) {
                    case "1" -> {
                        String[] str = new String[6]; //TODO: change this to prob do the correct thing which is check the age and then re ask users to input
                        System.out.print("\nEnter the student id: "); // 0
                        str[0] = reader.readLine();
                        System.out.print("Enter the student name: "); // 1
                        str[1] = reader.readLine();
                        System.out.print("Enter the student age: "); //2
                        str[2] = reader.readLine();
                        System.out.print("Enter the student email: ");// 3
                        str[3] = reader.readLine();
                        System.out.print("Enter the student course: "); // 4
                        str[4] = reader.readLine();
                        if (!StudentManager.validate(str[0], str[2], str[3])) break;
                        StudentManager.saveStudent(str[0], str[1], str[2], str[3], str[4]);
                        printOption();
                    }
                    case "2" -> {
                        System.out.print("\nEnter the student ID to search: ");
                        String index = reader.readLine();
                        String[] response = StudentManager.searchStudent(index).toArray(new String[0]);
                        if (response.length > 0)
                            System.out.printf("\u001B[0;36m----------------------------------\nSTUDENT ID: %s\nSTUDENT NAME: %s\nSTUDENT AGE: %s\nSTUDENT EMAIL: %s\nSTUDENT COURSE: %s\n----------------------------------\u001B[0m%n", response);
                        else
                            System.err.printf("Student with student Id: %s was not found!", index);
                        printOption();
                    }
                    case "3" -> {
                        System.out.print("\nEnter the student ID to delete: ");
                        String index = reader.readLine();
                        ArrayList<String> response = StudentManager.searchStudent(index);
                        if (response.size() > 0) { //TODO idk if this is needed but we will see
                            System.out.println(StudentManager.searchStudent(index).toArray(new String[0])[2]);
                            System.out.printf("Are you sure you want to delete student with ID: %s ? Press Y to continue or any other input to discard changes", index);
                            if (reader.readLine().equalsIgnoreCase("y")) {
                                StudentManager.deleteStudent(response);
                                System.out.println("Student was deleted");
                            } else
                                System.err.println("procedure canceled");
                        } else
                            System.err.printf("Student with student Id: %s was not found!", index);
                        printOption();

                    }
                    case "4" -> {
                        StudentManager.studentReport();
                    }

                    case "exit","5" -> StudentManager.exitStudentApplication();
                    default -> System.err.println("Unknown Input");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void welcome() { // a nice ascii big lettering that 90 percent of console apps have that make it just a little nicer
        System.out.println("\u001B[32mSTUDENT MANAGEMENT APPLICATION\n************************************************\033[0m");
    }

    public static void printOption() {
        System.out.println("""
                \nPlease select one of the following menu items:	\u001B[34m
                (1) Capture a new student
                (2) Search for a student
                (3) Delete a student
                (4) Print student report
                (5) Exit Application""");
    }
}