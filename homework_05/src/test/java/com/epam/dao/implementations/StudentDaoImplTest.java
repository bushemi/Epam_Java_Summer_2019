package com.epam.dao.implementations;

import com.epam.dao.entity.Person;
import com.epam.dao.entity.Student;
import com.epam.dao.interfaces.PersonDao;
import com.epam.dao.interfaces.StudentDao;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StudentDaoImplTest {
    private static PersonDao personDao = new PersonDaoImpl();
    private static StudentDao studentDao = new StudentDaoImpl();

    @AfterClass
    public static void clean() {
        deleteUsedStudents();
        deleteUsedPersons();
    }

    private static void deleteUsedStudents() {
        for (int i = 1; i <= 8; i++) {
            studentDao.delete(i);
        }
    }

    private static void deleteUsedPersons() {
        for (int i = 9; i <= 17; i++) {
            personDao.delete(i);
        }
    }

    @Test
    public void save() {
        //GIVEN
        Person person = new Person(9, "Ulan", "Badoev");
        personDao.save(person);
        Student student = new Student(1, person);

        //WHEN
        studentDao.save(student);

        //THEN
        Student studentFromDb = studentDao.findById(1);
        assertThat(studentFromDb, is(not(nullValue())));
        assertThat(studentFromDb.getPerson().getId(), equalTo(9));
    }

    @Test
    public void saveAll() {
        //GIVEN
        Person person1 = new Person(10, "Ushat", "Pomoev");
        personDao.save(person1);
        Person person2 = new Person(11, "Preston", "Garvy");
        personDao.save(person2);
        Student student1 = new Student(2, person1);
        Student student2 = new Student(3, person2);

        //WHEN
        studentDao.saveAll(Arrays.asList(student1, student2));

        //THEN
        Collection<Student> all = studentDao.findAll();
        assertThat(all, is(notNullValue()));
        assertThat(all, is(not(empty())));
        Student student1FromDb = all.stream()
                .filter(student -> student.getId().equals(student1.getId()))
                .findFirst().orElseThrow(RuntimeException::new);
        Student student2FromDb = all.stream()
                .filter(student -> student.getId().equals(student2.getId()))
                .findFirst().orElseThrow(RuntimeException::new);
        assertThat(student1FromDb.getPerson().getId(), is(equalTo(student1.getPerson().getId())));
        assertThat(student2FromDb.getPerson().getId(), is(equalTo(student2.getPerson().getId())));
    }

    @Test
    public void findById() {
        //GIVEN
        Person person = new Person(12, "Rulon", "Oboev");
        personDao.save(person);
        Student student = new Student(4, person);
        studentDao.save(student);

        //WHEN
        Student studentFromDb = studentDao.findById(4);

        //THEN
        assertThat(studentFromDb, is(notNullValue()));
        assertThat(studentFromDb.getId(), is(equalTo(student.getId())));
        assertThat(studentFromDb.getPerson().getId(), is(equalTo(student.getPerson().getId())));
    }

    @Test
    public void findAll() {
        //GIVEN
        Person person1 = new Person(13, "Nickolaj", "Fury");
        personDao.save(person1);
        Person person2 = new Person(14, "Natasha", "Romanoff");
        personDao.save(person2);
        Student student1 = new Student(5, person1);
        Student student2 = new Student(6, person2);
        studentDao.saveAll(Arrays.asList(student1, student2));

        //WHEN
        Collection<Student> students = studentDao.findAll();

        //THEN
        assertThat(students, is(not(empty())));
        Student student1FromDb = students.stream()
                .filter(student -> student.getId().equals(student1.getId()))
                .findFirst().orElseThrow(RuntimeException::new);
        Student student2FromDb = students.stream()
                .filter(student -> student.getId().equals(student2.getId()))
                .findFirst().orElseThrow(RuntimeException::new);
        assertThat(student1FromDb.getPerson().getId(), is(equalTo(student1.getPerson().getId())));
        assertThat(student2FromDb.getPerson().getId(), is(equalTo(student2.getPerson().getId())));
    }

    @Test
    public void update() {
        //GIVEN
        Person person1 = new Person(15, "Grigory", "Skovoroda");
        personDao.save(person1);
        Person person2 = new Person(16, "Igor", "Sikorski");
        personDao.save(person2);
        Student student = new Student(7, person1);
        studentDao.save(student);
        student.setPerson(person2);

        //WHEN
        studentDao.update(student);

        //THEN
        Student studentFromDb = studentDao.findById(7);
        assertThat(studentFromDb, is(notNullValue()));
        assertThat(studentFromDb.getPerson().getId(), is(equalTo(person2.getId())));
    }

    @Test
    public void delete() {
        //GIVEN
        Person person = new Person(17, "Taras", "Shevchenko");
        personDao.save(person);
        Student student = new Student(8, person);
        studentDao.save(student);

        //WHEN
        studentDao.delete(8);

        //THEN
        Student studentFromDb = studentDao.findById(8);
        assertThat(studentFromDb, is(nullValue()));
    }
}
