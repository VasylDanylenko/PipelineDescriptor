package org.example;

import lombok.AllArgsConstructor;
import org.example.pipeline.Configuration;
import org.example.pipeline.PipelineDescriptor;
import org.example.pipeline.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@AllArgsConstructor
class PipelineExecutor {

    private final Map<String, BiConsumer<Map<String, Object>, Configuration>> strategies = new HashMap<>();

    {
        strategies.put("AddField", this::addField);
        strategies.put("RemoveField", this::removeField);
    }

    public void transform(PipelineDescriptor pipelineDescriptor, Map<String, Object> jsonDocument) {
        List<Step> steps = pipelineDescriptor.getSteps();

        steps.forEach(step -> {
            String processor = step.getProcessor();
            Configuration configuration = step.getConfiguration();

            strategies.getOrDefault(processor, this::unknownProcessor).accept(jsonDocument, configuration);
        });
    }

    private void addField(Map<String, Object> jsonDocument, Configuration configuration) {
        String fieldName = configuration.getFieldName();
        String fieldValue = configuration.getFieldValue();
        jsonDocument.put(fieldName, fieldValue);
    }

    private void removeField(Map<String, Object> jsonDocument, Configuration configuration) {
        String fieldName = configuration.getFieldName();
        jsonDocument.remove(fieldName);
    }

    private void unknownProcessor(Map<String, Object> jsonDocument, Configuration configuration) {
        System.out.println("Unknown processor type: " + configuration.getFieldName());
    }
}
