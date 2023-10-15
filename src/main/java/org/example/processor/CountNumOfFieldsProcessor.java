package org.example.processor;

import java.util.Map;

public class CountNumOfFieldsProcessor implements Processor {
    private String targetFieldName;

    @Override
    public void initialize(Map<String, String> configuration) {
        targetFieldName = configuration.get("targetFieldName");
    }

    @Override
    public void process(Map<String, Object> jsonDocument) {
        int numOfFields = jsonDocument.size();
        jsonDocument.put(targetFieldName, numOfFields);
    }
}
