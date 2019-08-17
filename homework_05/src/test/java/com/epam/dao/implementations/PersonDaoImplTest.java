package com.epam.dao.implementations;

import com.epam.dao.entity.Person;
import com.epam.dao.interfaces.PersonDao;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PersonDaoImplTest {
    private static PersonDao personDao = new PersonDaoImpl();

    @AfterClass
    public static void clean() {
        for (int i = 1; i <= 8; i++) {
            personDao.delete(i);
        }
    }

    @Test
    public void save() {
        //GIVEN
        Person person1 = new Person(1, "masha", "tui");
        Person person2 = new Person(2, "masha2", "tui");

        //WHEN
        personDao.save(person1);
        personDao.save(person2);

        //THEN
        assertThat(personDao.findAll(), is(not(empty())));
        assertThat(personDao.findAll(), hasItem(person1));
        assertThat(personDao.findAll(), hasItem(person2));
    }

    @Test
    public void saveAll() {
        //GIVEN
        Person person1 = new Person(3, "Oleg", "tui");
        Person person2 = new Person(4, "Grigory", "tui");

        //WHEN
        personDao.saveAll(Arrays.asList(person1, person2));

        //THEN
        assertThat(personDao.findAll(), is(not(empty())));
        assertThat(personDao.findAll(), hasItem(person1));
        assertThat(personDao.findAll(), hasItem(person2));
    }

    @Test
    public void findById() {
        //GIVEN
        Person person = new Person(5, "Oleg", "tui");
        personDao.save(person);

        //WHEN
        Person byId = personDao.findById(5);

        //THEN
        assertThat(byId, equalTo(person));
    }

    @Test
    public void findAll() {
        //GIVEN
        Person person = new Person(6, "Igor", "Boldysh");
        personDao.save(person);

        //When
        Collection<Person> all = personDao.findAll();

        //Then
        assertThat(all, is(not(empty())));
        assertThat(all, hasItem(person));
    }

    @Test
    public void update() {
        //Given
        Person person = new Person(7, "Ivan", "Bezdomnyj");
        personDao.save(person);
        person.setLastName("Ivanov");

        //When
        personDao.update(person);

        //Then
        Person personFromDb = personDao.findById(7);
        assertThat(personFromDb, is(notNullValue()));
        assertThat(personFromDb.getFirstName(), equalTo("Ivan"));
        assertThat(personFromDb.getLastName(), equalTo("Ivanov"));
    }

    @Test
    public void delete() {
        //Given
        Person person = new Person(8, "Lena", "Geometry");
        personDao.save(person);

        //When
        personDao.delete(8);

        //Then
        Person personFromDb = personDao.findById(8);
        assertThat(personFromDb, is(nullValue()));
    }

}