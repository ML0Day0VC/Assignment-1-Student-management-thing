/**
 * @author Max Day
 * Created At: 2023/08/18
 */
package main.API;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class StudentManager {

    private static ArrayList<ArrayList<String>> student = new ArrayList<>();

    public static void saveStudent(String id, String name, String age, String email, String course) {
        ArrayList<String> newEntry = new ArrayList<>();
        newEntry.add(id); //id
        newEntry.add(name); //name
        newEntry.add(age); //age
        newEntry.add(email); //email
        newEntry.add(course); //course
        student.add(newEntry); // add to main list
        System.out.println("\033[0;36mAdded Student to memory\033[0m");
    }

    public static ArrayList<String> searchStudent(String target) {//TODO explain how this works
        ArrayList<String> result = new ArrayList<>();
        for (ArrayList<String> studentData : student)
            for (String data : studentData)
                if (data.equals(target)) {
                    result.addAll(studentData);
                    return result;
                }
        return result;
    }

    public static void deleteStudent(ArrayList<String> selectedStudent) {
        Iterator<ArrayList<String>> iterator = student.iterator();
        while (iterator.hasNext()) {
            ArrayList<String> studentData = iterator.next();
            if (studentData.equals(selectedStudent)) {
                iterator.remove();
                return;
            }
        }
    }

    public static void studentReport() { //TODO remove test methods
        int count = 1;
        for (ArrayList<String> innerList : student) {
            System.out.printf("\033[0;36m\n----------------------------------------\nSTUDENT %d:\n----------------------------------------\n" + "STUDENT ID: %s\n" + "STUDENT NAME: %s\n" + "STUDENT AGE: %s\n" + "STUDENT EMAIL: %s\n" + "STUDENT COURSE: %s\n\033[0m", count, innerList.get(0), innerList.get(1), innerList.get(2), innerList.get(3), innerList.get(4));
            count++;
        }
    }

    public static void exitStudentApplication() {
        System.exit(420); // exits the program
    }


    public static boolean isValidAge(String strAge) { // goofy way of checking if the age doesn't have a decimal (otherwise throws error) and is greater than 16 (isn't a negative)
        try {
            int age = Integer.parseInt(strAge);
            return age >= 16;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) { // regex to check i the pattern of an email is valid. the pattern follows as: <letters/numbers> <@> <letters/numbers> <.> <letters/numbers>
        return Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches();
    }

    public static boolean isValidId(String iD) { // checks for if it is  an int and that it is greater than 0. u can easily filterer out character or decimals by trying to parse it
        try {
            int age = Integer.parseInt(iD);
            return age > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validate(String id, String age, String email) { // While u can make it so that the user has to specifically re enter the TODO: make it so u can re enter like the task asks for 
        if (!isValidId(id)) System.err.println("Invalid ID. Please check credentials and retry.");
        else if (!isValidAge(age)) System.err.println("Invalid Age. Please check credentials and retry.");
        else if (!isValidEmail(email)) System.err.println("Invalid Email. Please check credentials and retry.");
        else return true;
        return false;
    }
}
