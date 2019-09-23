package com.epam.service;

import com.epam.dao.interfaces.TestEntityDao;
import com.epam.model.TestEntity;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TestEntityServiceImplTest {
    private TestEntityDao testEntityDao = mock(TestEntityDao.class);
    private TestEntityService service = new TestEntityServiceImpl(testEntityDao);
    private static final long ID = 1L;

    @Test
    public void findById() {
        //GIVEN
        when(testEntityDao.findById(ID)).thenReturn(new TestEntity());

        //WHEN
        TestEntity entityFoundById = service.findById(ID);

        //THEN
        assertNotNull(entityFoundById);
        verify(testEntityDao).findById(ID);
    }

    @Test
    public void save() {
        //GIVEN
        TestEntity entity = new TestEntity();
        when(testEntityDao.save(entity)).thenReturn(1L);

        //WHEN
        long save = service.save(entity);

        //THEN
        assertEquals(1L, save);
        verify(testEntityDao).save(entity);
    }

    @Test
    public void saveAll() {
        //GIVEN
        List<TestEntity> testEntities = Arrays.asList(new TestEntity(), new TestEntity(), new TestEntity());

        //WHEN
        service.saveAll(testEntities);
        //THEN

        verify(testEntityDao).saveAll(testEntities);
    }

    @Test
    public void findAll() {
        //GIVEN
        List<TestEntity> testEntities = Arrays.asList(new TestEntity(), new TestEntity(), new TestEntity());
        when(testEntityDao.findAll()).thenReturn(testEntities);

        //WHEN
        List<TestEntity> all = service.findAll();

        //THEN
        verify(testEntityDao).findAll();
    }

    @Test
    public void update() {
        //GIVEN
        TestEntity entity = new TestEntity();

        //WHEN
        service.update(entity);

        //THEN
        verify(testEntityDao).update(entity);
    }

    @Test
    public void updateAll() {
        //GIVEN
        TestEntity testEntity = new TestEntity();
        List<TestEntity> testEntities = Arrays.asList(testEntity, testEntity, testEntity);

        //WHEN
        service.updateAll(testEntities);

        //THEN
        verify(testEntityDao, times(3)).update(testEntity);
    }

    @Test
    public void delete() {
        //WHEN
        service.delete(ID);

        //THEN
        verify(testEntityDao).delete(ID);
    }

    @Test
    public void isTestExist() {
        //GIVEN
        when(testEntityDao.isTestExist(ID)).thenReturn(true);

        //WHEN
        boolean testExist = service.isTestExist(ID);

        //THEN
        verify(testEntityDao).isTestExist(ID);
        assertTrue(testExist);
    }
}