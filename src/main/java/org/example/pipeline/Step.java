package org.example.pipeline;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Step {
    private String processor;
    private Configuration configuration;
}
