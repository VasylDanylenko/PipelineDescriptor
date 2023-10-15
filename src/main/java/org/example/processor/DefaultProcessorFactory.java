package org.example.processor;

public class DefaultProcessorFactory implements ProcessorFactory {
    @Override
    public Processor create(String processorName) {
        if ("AddField".equals(processorName)) {
            return new AddFieldProcessor();
        } else if ("RemoveField".equals(processorName)) {
            return new RemoveFieldProcessor();
        } else if ("CountNumOfFields".equals(processorName)) {
            return new CountNumOfFieldsProcessor();
        } else {
            throw new IllegalArgumentException("Unknown processor: " + processorName);
        }
    }
}
