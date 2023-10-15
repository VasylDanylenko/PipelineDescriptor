package org.example;

import org.example.pipeline.Configuration;
import org.example.pipeline.PipelineDescriptor;
import org.example.pipeline.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PipelineExecutorTest {

    private final PipelineExecutor pipelineExecutor = new PipelineExecutor();
    private final Map<String, Object> jsonDocument = new HashMap<>();

    @Test
    void transform() {
        PipelineDescriptor pipelineDescriptor = createAddFieldPipeline();
        jsonDocument.put("existingField", "value");

        pipelineExecutor.transform(pipelineDescriptor, jsonDocument);

        assertEquals("value", jsonDocument.get("existingField"));
        assertEquals("NewValue", jsonDocument.get("newField"));
    }

    private PipelineDescriptor createAddFieldPipeline() {
        List<Step> steps = new ArrayList<>();

        Step step = new Step("AddField", getConfiguration());

        steps.add(step);

        return new PipelineDescriptor(steps);
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setFieldName("newField");
        configuration.setFieldValue("NewValue");
        return configuration;
    }
}