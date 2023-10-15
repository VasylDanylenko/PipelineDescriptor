package org.example;

import lombok.AllArgsConstructor;
import org.example.pipeline.Configuration;
import org.example.pipeline.PipelineDescriptor;
import org.example.pipeline.Step;
import org.example.processor.Processor;
import org.example.processor.ProcessorFactory;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class PipelineExecutor {
    private final ProcessorFactory processorFactory;

    public void transform(PipelineDescriptor pipelineDescriptor, Map<String, Object> jsonDocument) {
        List<Step> steps = pipelineDescriptor.getSteps();

        steps.forEach(step -> {
            Configuration configuration = step.getConfiguration();
            Map<String, String> configurationMap = configuration.convertConfigurationToMap(configuration);

            Processor processor = processorFactory.create(step.getProcessor());
            processor.initialize(configurationMap);
            processor.process(jsonDocument);
        });
    }
}