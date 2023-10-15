package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pipeline.PipelineDescriptor;

import java.io.IOException;

import static java.lang.String.format;

public class PipelineDescriptorParser {
    public PipelineDescriptor parsePipelineDescriptor(String json) {
        try {
            return new ObjectMapper().readValue(json, PipelineDescriptor.class);
        } catch (IOException exception) {
            String errorMessage = format("Failed to parse JSON. Reason: %s", exception.getMessage());
            throw new RuntimeException(errorMessage, exception);
        }
    }
}
