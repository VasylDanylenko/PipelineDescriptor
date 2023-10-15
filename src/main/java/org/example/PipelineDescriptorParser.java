package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pipeline.PipelineDescriptor;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.String.format;

public class PipelineDescriptorParser {
    public PipelineDescriptor parsePipelineDescriptor(String jsonResourcePath) {
        try (InputStream inputStream = PipelineDescriptorParser.class.getResourceAsStream(jsonResourcePath)) {
            return new ObjectMapper().readValue(inputStream, PipelineDescriptor.class);
        } catch (IOException exception) {
            String errorMessage = format("Failed from JSON: %s. Reason: %s", jsonResourcePath, exception.getMessage());
            throw new RuntimeException(errorMessage, exception);
        }
    }
}
