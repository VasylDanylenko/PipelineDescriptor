package org.example;

import org.example.pipeline.Configuration;
import org.example.pipeline.PipelineDescriptor;
import org.example.pipeline.Step;
import org.example.processor.DefaultProcessorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PipelineDataExecutorTest {

    private final PipelineExecutor pipelineExecutor = new PipelineExecutor(new DefaultProcessorFactory());
    private final Map<String, Object> jsonDocument = new HashMap<>();

    @BeforeEach
    void setUp() {
        jsonDocument.put("existingField", "value");
    }

    @Test
    void transform_AddField_ReturnsModifiedDocument() {
        PipelineDescriptor pipelineDescriptor = createAddFieldPipeline();

        pipelineExecutor.transform(pipelineDescriptor, jsonDocument);

        assertEquals("value", jsonDocument.get("existingField"));
        assertEquals("NewValue", jsonDocument.get("newField"));
    }

    @Test
    void transform_RemoveField_ReturnsModifiedDocument() {
        PipelineDescriptor pipelineDescriptor = createRemoveFieldPipeline();

        pipelineExecutor.transform(pipelineDescriptor, jsonDocument);

        assertFalse(jsonDocument.containsKey("userName"));
    }

    @Test
    void transform_CountNumOfFields_ReturnsModifiedDocument() {
        PipelineDescriptor pipelineDescriptor = createCountNumOfFieldsPipeline();

        pipelineExecutor.transform(pipelineDescriptor, jsonDocument);

        assertTrue(jsonDocument.containsKey("numOfFields"));
        assertEquals(1, jsonDocument.get("numOfFields"));
    }

    private PipelineDescriptor createAddFieldPipeline() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step("AddField", getAddFieldConfiguration());
        steps.add(step);
        return new PipelineDescriptor(steps);
    }

    private PipelineDescriptor createRemoveFieldPipeline() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step("RemoveField", getRemoveFieldConfiguration());
        steps.add(step);
        return new PipelineDescriptor(steps);
    }

    private PipelineDescriptor createCountNumOfFieldsPipeline() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step("CountNumOfFields", getCountNumOfFieldsConfiguration());
        steps.add(step);
        return new PipelineDescriptor(steps);
    }

    private Configuration getAddFieldConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setFieldName("newField");
        configuration.setFieldValue("NewValue");
        return configuration;
    }

    private Configuration getRemoveFieldConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setFieldName("userName");
        return configuration;
    }

    private Configuration getCountNumOfFieldsConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setTargetFieldName("numOfFields");
        return configuration;
    }
}