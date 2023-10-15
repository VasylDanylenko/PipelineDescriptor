package org.example.pipeline;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Configuration {
    private String fieldName;
    private String fieldValue;
    private String targetFieldName;
}
