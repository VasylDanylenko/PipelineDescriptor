package org.example.processor;

import java.util.Map;

public class AddFieldProcessor implements Processor {
    private String fieldName;
    private String fieldValue;

    @Override
    public void initialize(Map<String, String> configuration) {
        fieldName = configuration.get("fieldName");
        fieldValue = configuration.get("fieldValue");
    }

    @Override
    public void process(Map<String, Object> jsonDocument) {
        jsonDocument.put(fieldName, fieldValue);
    }
}
