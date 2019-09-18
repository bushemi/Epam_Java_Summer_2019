package com.epam.converters;

import com.epam.model.TestEntity;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJsonConverterTest {
    private static final String JSON_REPRESENTATION =
            "{\"id\":71,\"subject\":\"Algebra\",\"testName\":\"Calculation\",\"difficulty\":10,\"secondsToComplete\":180}";
    private JsonConverter<TestEntity> converter = new TestJsonConverterImpl();
    private static TestEntity entityToJson = new TestEntity();

    @BeforeClass
    public static void init() {
        entityToJson.setId(71L);
        entityToJson.setSubject("Algebra");
        entityToJson.setTestName("Calculation");
        entityToJson.setDifficulty(10);
        entityToJson.setSecondsToComplete(180);
    }

    @Test
    public void toJson() {
        //WHEN
        String actualJson = converter.toJson(entityToJson);

        //THEN
        assertEquals(JSON_REPRESENTATION, actualJson);
    }

    @Test
    public void fromJson() {
        //WHEN
        TestEntity entityFromJson = converter.fromJson(JSON_REPRESENTATION);

        //THEN
        assertEquals(entityToJson, entityFromJson);
    }
}