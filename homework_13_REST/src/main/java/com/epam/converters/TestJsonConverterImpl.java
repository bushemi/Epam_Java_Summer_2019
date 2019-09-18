package com.epam.converters;

import com.epam.model.TestEntity;

public class TestJsonConverterImpl implements JsonConverter<TestEntity> {
    @Override
    public String toJson(TestEntity entity) {
        StringBuilder sb = new StringBuilder()
                .append("{")
                .append("\"id\":").append(entity.getId()).append(",")
                .append("\"subject\":\"").append(entity.getSubject()).append("\",")
                .append("\"testName\":\"").append(entity.getTestName()).append("\",")
                .append("\"difficulty\":").append(entity.getDifficulty()).append(",")
                .append("\"secondsToComplete\":").append(entity.getSecondsToComplete())
                .append("}");

        return sb.toString();
    }

    @Override
    public TestEntity fromJson(String json) {
        TestEntity testEntity = new TestEntity();
        json = json.replace("{", "").replace("}", "");
        String[] pairs = json.split(",");
        for (String pair : pairs) {
            String[] strings = pair.split(":");
            String value = strings[1].trim();
            if (pair.contains("\"id\"")) {
                includeIdFromValueToTestEntity(testEntity, value);
                continue;
            }
            if (pair.contains("\"subject\"")) {
                testEntity.setSubject(value.substring(1, value.length()-1));
                continue;
            }
            if (pair.contains("\"testName\"")) {
                testEntity.setTestName(value.substring(1, value.length()-1));
                continue;
            }
            if (pair.contains("\"difficulty\"")) {
                includeDifficultyFromValueToTestEntity(testEntity, value);
                continue;
            }
            if (pair.contains("\"secondsToComplete\"")) {
                includeSecondsToCompleteFromValueToTestEntity(testEntity, value);
            }
        }
        return testEntity;
    }

    private void includeDifficultyFromValueToTestEntity(TestEntity testEntity, String value) {
        if (isValueNull(value) || isValueString(value) || isValueNegative(value)) {
            testEntity.setDifficulty(null);
        } else {
            testEntity.setDifficulty(Integer.parseInt(value));
        }
    }

    private void includeSecondsToCompleteFromValueToTestEntity(TestEntity testEntity, String value) {
        if (isValueNull(value) || isValueString(value) || isValueNegative(value)) {
            testEntity.setSecondsToComplete(null);
        } else {
            testEntity.setSecondsToComplete(Integer.parseInt(value));
        }
    }


    private void includeIdFromValueToTestEntity(TestEntity testEntity, String value) {
        if (isValueNull(value) || isValueString(value) || isValueNegative(value)) {
            testEntity.setId(null);
        } else {
            testEntity.setId(Long.parseLong(value));
        }
    }

    private boolean isValueNull(String value) {
        return value.toLowerCase().contains("null");
    }

    private boolean isValueString(String value) {
        return value.contains("\"");
    }

    private boolean isValueNegative(String value) {
        return value.contains("-");
    }

}
