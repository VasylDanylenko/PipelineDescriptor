package org.example;

import org.example.pipeline.PipelineDescriptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PipelineDataDescriptorParserTest {

    private final PipelineDescriptorParser pipelineDescriptorParser = new PipelineDescriptorParser();

    @Test
    void parsePipelineDescriptor_WhenValidJson_ParsesAndReturnsDescriptor() {
        Integer expectedCountOfSteps = 4;
        String json = PipelineData.getPipelineDescriptor();

        PipelineDescriptor descriptor = pipelineDescriptorParser.parsePipelineDescriptor(json);

        assertNotNull(descriptor);
        assertEquals(expectedCountOfSteps, descriptor.getSteps().size());
    }

    @Test
    void parsePipelineDescriptor_WhenInvalidJson_ThrowsRuntimeException() {
        String json = "Invalid JSON";

        PipelineDescriptorParser pipelineDescriptorParser = new PipelineDescriptorParser();

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                                                         () -> pipelineDescriptorParser.parsePipelineDescriptor(json));

        assertEquals(runtimeException.getMessage(),
                     "Failed to parse JSON. Reason: Unrecognized token 'Invalid': was expecting " +
                     "(JSON String, Number, Array, Object or token 'null', 'true' or 'false')\n" +
                     " at [Source: (String)\"Invalid JSON\"; line: 1, column: 8]");
    }
}