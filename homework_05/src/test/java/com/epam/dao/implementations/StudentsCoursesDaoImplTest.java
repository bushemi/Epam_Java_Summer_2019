package com.epam.dao.implementations;

import com.epam.dao.entity.Course;
import com.epam.dao.entity.Person;
import com.epam.dao.entity.Student;
import com.epam.dao.entity.StudentCourse;
import com.epam.dao.interfaces.CourseDao;
import com.epam.dao.interfaces.PersonDao;
import com.epam.dao.interfaces.StudentDao;
import com.epam.dao.interfaces.StudentsCoursesDao;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StudentsCoursesDaoImplTest {

    private static StudentsCoursesDao studentsCoursesDao = new StudentsCoursesDaoImpl();
    private static PersonDao personDao = new PersonDaoImpl();
    private static StudentDao studentDao = new StudentDaoImpl();
    private static CourseDao courseDao = new CourseDaoImpl();

    @AfterClass
    public static void clean() {
        for (int i = 9; i <= 18; i++) {
            for (int j = 9; j <= 18; j++) {
                studentsCoursesDao.delete(i, j);
            }
        }
        deleteUsedCourses();
        deleteUsedStudents();
        deleteUsedPersons();
    }

    private static void deleteUsedCourses() {
        for (int i = 9; i <= 18; i++) {
            courseDao.delete(i);
        }
    }

    private static void deleteUsedStudents() {
        for (int i = 9; i <= 18; i++) {
            studentDao.delete(i);
        }
    }

    private static void deleteUsedPersons() {
        for (int i = 18; i <= 27; i++) {
            personDao.delete(i);
        }
    }

    @Test
    public void save() {
        //GIVEN
        Person person = new Person(18, "Vasil", "Stus");
        personDao.save(person);
        Student student = new Student(9, person);
        studentDao.save(student);
        Course course = new Course(9, "French");
        courseDao.save(course);
        StudentCourse studentCourse = new StudentCourse(9, 9);

        //WHEN
        studentsCoursesDao.save(studentCourse);

        //THEN
        Collection<StudentCourse> all = studentsCoursesDao.findAll();
        assertThat(all, is(notNullValue()));
        assertThat(all, is(not(empty())));
        assertThat(all, hasItem(studentCourse));
    }

    @Test
    public void saveAll() {
        //GIVEN
        Person person1 = new Person(19, "Xrust", "Team");
        Person person2 = new Person(20, "Zolotoi", "Vek");
        personDao.saveAll(Arrays.asList(person1, person2));
        Student student1 = new Student(10, person1);
        Student student2 = new Student(11, person2);
        studentDao.saveAll(Arrays.asList(student1, student2));
        Course course = new Course(10, "MathAn");
        courseDao.save(course);
        StudentCourse studentCourse1 = new StudentCourse(10, 10);
        StudentCourse studentCourse2 = new StudentCourse(11, 10);

        //WHEN
        studentsCoursesDao.saveAll(Arrays.asList(studentCourse1, studentCourse2));

        //THEN
        Collection<StudentCourse> all = studentsCoursesDao.findAll();
        assertThat(all, is(notNullValue()));
        assertThat(all, is(not(empty())));
        assertThat(all, hasItem(studentCourse1));
        assertThat(all, hasItem(studentCourse2));
    }

    @Test
    public void findAll() {
        //GIVEN
        Person person1 = new Person(21, "Trent", "Reznor");
        Person person2 = new Person(22, "Jack", "Black");
        personDao.saveAll(Arrays.asList(person1, person2));
        Student student1 = new Student(12, person1);
        Student student2 = new Student(13, person2);
        studentDao.saveAll(Arrays.asList(student1, student2));
        Course course = new Course(11, "DataBases");
        courseDao.save(course);
        StudentCourse studentCourse1 = new StudentCourse(12, 11);
        StudentCourse studentCourse2 = new StudentCourse(13, 11);
        studentsCoursesDao.saveAll(Arrays.asList(studentCourse1, studentCourse2));

        //WHEN
        Collection<StudentCourse> all = studentsCoursesDao.findAll();

        //THEN
        assertThat(all, is(notNullValue()));
        assertThat(all, is(not(empty())));
        assertThat(all, hasItem(studentCourse1));
        assertThat(all, hasItem(studentCourse2));
    }

    @Test
    public void findByStudentId() {
        //GIVEN
        Person person = new Person(23, "John", "Dimagio");
        personDao.save(person);
        Student student = new Student(14, person);
        studentDao.save(student);
        Course course1 = new Course(12, "Physics");
        Course course2 = new Course(13, "Chemistry");
        courseDao.saveAll(Arrays.asList(course1, course2));
        StudentCourse studentCourse1 = new StudentCourse(14, 12);
        StudentCourse studentCourse2 = new StudentCourse(14, 13);
        studentsCoursesDao.saveAll(Arrays.asList(studentCourse1, studentCourse2));

        //WHEN
        Collection<StudentCourse> coursesByStudent = studentsCoursesDao.findByStudentId(14);

        //THEN
        assertThat(coursesByStudent, is(notNullValue()));
        assertThat(coursesByStudent, is(not(empty())));
        assertThat(coursesByStudent, hasItem(studentCourse1));
        assertThat(coursesByStudent, hasItem(studentCourse2));
    }

    @Test
    public void findByCourseId() {
        //GIVEN
        Person person1 = new Person(24, "Tom", "Hanks");
        Person person2 = new Person(25, "Nickolas", "Cage");
        personDao.saveAll(Arrays.asList(person1, person2));
        Student student1 = new Student(15, person1);
        Student student2 = new Student(16, person2);
        studentDao.saveAll(Arrays.asList(student1, student2));
        Course course = new Course(14, "Flying");
        courseDao.save(course);
        StudentCourse studentCourse1 = new StudentCourse(15, 14);
        StudentCourse studentCourse2 = new StudentCourse(16, 14);
        studentsCoursesDao.saveAll(Arrays.asList(studentCourse1, studentCourse2));

        //WHEN
        Collection<StudentCourse> coursesByStudent = studentsCoursesDao.findByCourseId(14);

        //THEN
        assertThat(coursesByStudent, is(notNullValue()));
        assertThat(coursesByStudent, is(not(empty())));
        assertThat(coursesByStudent, hasItem(studentCourse1));
        assertThat(coursesByStudent, hasItem(studentCourse2));

    }

    @Test
    public void delete() {
        //GIVEN
        Person person = new Person(26, "John", "Travolta");
        personDao.save(person);
        Student student = new Student(17, person);
        studentDao.save(student);
        Course course = new Course(15, "Flying");
        courseDao.save(course);
        StudentCourse studentCourse = new StudentCourse(17, 15);
        studentsCoursesDao.save(studentCourse);

        //WHEN
        studentsCoursesDao.delete(17, 15);

        //THEN
        Collection<StudentCourse> coursesFromDb = studentsCoursesDao.findByCourseId(15);
        assertThat(coursesFromDb, is(notNullValue()));
        assertThat(coursesFromDb, is(empty()));
    }
}
