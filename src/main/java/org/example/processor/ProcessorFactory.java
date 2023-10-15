package org.example.processor;

public interface ProcessorFactory {
    Processor create(String processorName);
}
