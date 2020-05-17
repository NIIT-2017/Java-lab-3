import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    static Deanery deanery = Deanery.getInstance("Test");
    static Student unrolledStudent = deanery.createStudent("unrolledStudent");
    static Student enrolledStudent = deanery.createStudent("enrolledStudent");
    static Group startGroup = deanery.createGroup("startGroup");
    static Group anotherGroup = deanery.createGroup("anotherGroup");

    @BeforeAll
    static void prepare(){
        deanery.enrolling(enrolledStudent, "startGroup");
    }

    @Test
    void enrollTo() {
        Student testStudent = deanery.createStudent("testStudent");
        assertEquals("Without group", testStudent.sayGroup());
        testStudent.enrollTo("nonexistentGroup");
        assertEquals("Without group", testStudent.sayGroup());
        enrolledStudent.enrollTo("anotherGroup");
        assertEquals("startGroup", enrolledStudent.sayGroup());
        testStudent.enrollTo("startGroup");
        assertEquals("startGroup", testStudent.sayGroup());
    }

    @Test
    void transferTo() {
        enrolledStudent.transferTo("nonexistentGroup");
        assertEquals("startGroup", enrolledStudent.sayGroup());
        unrolledStudent.transferTo("startGroup");
        assertEquals("Without group", unrolledStudent.sayGroup());
        Student testStudent = deanery.createStudent("testStudent");
        testStudent.enrollTo("startGroup");
        assertEquals("startGroup", testStudent.sayGroup());
        testStudent.transferTo("anotherGroup");
        assertEquals("anotherGroup", testStudent.sayGroup());
    }

    @Test
    void addMark() {
        Student testStudent = deanery.createStudent("testStudent");
        testStudent.enrollTo("startGroup");
        testStudent.addMark(-1);
        assertEquals(0, testStudent.averageRating());
        testStudent.addMark(6);
        assertEquals(0, testStudent.averageRating());
        testStudent.addMark(4);
        assertEquals(4, testStudent.averageRating());
    }

    @Test
    void averageRating() {
        Student testStudent = deanery.createStudent("testStudent");
        testStudent.enrollTo("startGroup");
        assertEquals(0, testStudent.averageRating());
        testStudent.addMark(5);
        assertEquals(5, testStudent.averageRating());
        testStudent.addMark(3);
        assertEquals(4, testStudent.averageRating());
        testStudent.addMark(1);
        assertEquals(3, testStudent.averageRating());
    }
}