package org.example.pipeline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {
    private String fieldName;
    private String fieldValue;
    private String targetFieldName;

    public Map<String, String> convertConfigurationToMap(Configuration configuration) {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("fieldName", configuration.getFieldName());
        configMap.put("fieldValue", configuration.getFieldValue());
        configMap.put("targetFieldName", configuration.getTargetFieldName());
        return configMap;
    }
}
