package org.example.processor;

public class DefaultProcessorFactory implements ProcessorFactory {
    @Override
    public Processor create(String processorName) {
        if(processorName.equals("AddField")){
            return new AddFieldProcessor();
        }
        if(processorName.equals("RemoveField")){
            return new RemoveFieldProcessor();
        }
        else
            throw new IllegalArgumentException("Unknown processor: " + processorName);
    }
}
