package com.epam.dao.implementations;

import com.epam.dao.entity.Course;
import com.epam.dao.interfaces.CourseDao;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CourseDaoImplTest {
    private static CourseDao courseDao = new CourseDaoImpl();

    @AfterClass
    public static void clean() {
        for (int i = 1; i <= 8; i++) {
            courseDao.delete(i);
        }
    }

    @Test
    public void save() {
        //GIVEN
        Course course = new Course(1, "Math");

        //WHEN
        courseDao.save(course);

        //THEN
        Course courseFromDb = courseDao.findById(1);
        assertThat(courseFromDb, is(not(nullValue())));
        assertThat(courseFromDb, equalTo(course));

    }

    @Test
    public void saveAll() {
        //GIVEN
        Course course1 = new Course(2, "History");
        Course course2 = new Course(3, "Programming");

        //WHEN
        courseDao.saveAll(Arrays.asList(course1, course2));

        //THEN
        Collection<Course> all = courseDao.findAll();
        assertThat(all, is(notNullValue()));
        assertThat(all, is(not(empty())));
        assertThat(all, hasItem(course1));
        assertThat(all, hasItem(course2));
    }

    @Test
    public void findById() {
        //GIVEN
        Course course = new Course(4, "PT");
        courseDao.save(course);

        //WHEN
        Course courseFromDb = courseDao.findById(4);

        //THEN
        assertThat(courseFromDb, is(notNullValue()));
        assertThat(courseFromDb, is(equalTo(course)));
    }

    @Test
    public void findAll() {
        //GIVEN
        Course course1 = new Course(5, "Writing");
        Course course2 = new Course(6, "Logic");
        courseDao.saveAll(Arrays.asList(course1, course2));

        //WHEN
        Collection<Course> all = courseDao.findAll();

        //THEN
        assertThat(all, is(notNullValue()));
        assertThat(all, is(not(empty())));
        assertThat(all, hasItem(course1));
        assertThat(all, hasItem(course2));
    }

    @Test
    public void update() {
        //GIVEN
        Course course = new Course(7, "English");
        courseDao.save(course);
        course.setCourseName("Swimming");

        //WHEN
        courseDao.update(course);

        //THEN
        Course courseFromDb = courseDao.findById(7);
        assertThat(courseFromDb, is(notNullValue()));
        assertThat(courseFromDb, is(equalTo(course)));
    }

    @Test
    public void delete() {
        //GIVEN
        Course course = new Course(8, "Math");
        courseDao.save(course);

        //WHEN
        courseDao.delete(8);

        //THEN
        Course courseFromDb = courseDao.findById(8);
        assertThat(courseFromDb, is(nullValue()));
    }
}
