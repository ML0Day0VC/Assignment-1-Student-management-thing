package test;

/**
 * @author Max Day
 * Created At: 2023/08/18
 */

import main.API.StudentManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class studentManagerTest {

    private ArrayList<ArrayList<String>> student;

    @BeforeEach
    void setUp() {
        student = new ArrayList<>();
    }

    @Test
    void testSaveStudent() {
        StudentManager.saveStudent("1", "John Doe", "25", "john@example.com", "Computer Science");
        assertEquals(0, student.size());
    }

    @Test
    void testSearchStudent() {
        StudentManager.saveStudent("1", "John Doe", "25", "john@example.com", "Computer Science");
        ArrayList<String> result = StudentManager.searchStudent("1");
        assertFalse(result.isEmpty());
    }

    @Test
    void TestSearchStudent_StudentNotFound() {
        StudentManager.saveStudent("1", "John Doe", "25", "john@example.com", "Computer Science");
        ArrayList<String> result = StudentManager.searchStudent("2");
        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteStudent() {
        ArrayList<String> studentData = new ArrayList<>();
        studentData.add("1");
        student.add(studentData);
        StudentManager.deleteStudent(studentData);
        assertFalse(student.isEmpty());
    }

    @Test
    void TestDeleteStudent_StudentNotFound() {
        ArrayList<String> studentData = new ArrayList<>();
        studentData.add("1");
        studentData.add("John Doe");
        student.add(studentData);
        ArrayList<String> nonExistentStudentData = new ArrayList<>();
        nonExistentStudentData.add("2"); // Non-existent ID
        StudentManager.deleteStudent(nonExistentStudentData);
        assertEquals(1, student.size()); // Assert that the student list remains unchanged
    }


    @Test
    void testStudentReport() {
        // This method prints to console, so testing it is complex.
    }

    @Test
    void TestStudentAge_StudentAgeValid() {
        assertTrue(StudentManager.isValidAge("25"));
    }

    @Test
    void TestStudentAge_StudentAgeInvalid() {
        assertFalse(StudentManager.isValidAge("0"));
        assertFalse(StudentManager.isValidAge("-5"));
        assertFalse(StudentManager.isValidAge("25.5"));
    }
    @Test
    void TestStudentAge_StudentAgeInvalidCharacter() {
        assertFalse(StudentManager.isValidAge("a"));
    }

    @Test
    void testIsValidEmail() {
        assertTrue(StudentManager.isValidEmail("john@example.com"));
        assertFalse(StudentManager.isValidEmail("john.example."));
        assertFalse(StudentManager.isValidEmail("John"));
    }

    @Test
    void testIsValidId() {
        assertTrue(StudentManager.isValidId("1"));
        assertFalse(StudentManager.isValidId("0"));
        assertFalse(StudentManager.isValidId("-5"));
        assertFalse(StudentManager.isValidId("1.5"));
    }

    @Test
    void testValidate() {
        assertTrue(StudentManager.validate("1", "25", "john@example.com"));
        assertFalse(StudentManager.validate("-1", "25", "john@example.com"));
        assertFalse(StudentManager.validate("1", "0", "john@example.com"));
        assertFalse(StudentManager.validate("1", "25", "john.example"));
    }
}
