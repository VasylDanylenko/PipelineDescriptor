package org.example;

import org.example.pipeline.PipelineDescriptor;
import org.junit.jupiter.api.Test;

class PipelineDescriptorParserTest {

    @Test
    void parsePipelineDescriptor() {
        PipelineDescriptorParser pipelineDescriptorParser = new PipelineDescriptorParser();

        PipelineDescriptor descriptor = pipelineDescriptorParser.parsePipelineDescriptor("/PipelineDescriptor");

        System.out.println(descriptor);
    }
}