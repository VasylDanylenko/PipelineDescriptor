package org.example;

import org.example.pipeline.PipelineDescriptor;
import org.example.processor.DefaultProcessorFactory;
import org.example.processor.ProcessorFactory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PipelineDataDescriptorTest {

    @Test
    void testProcessDocumentByDescriptor_GetModifiedDocument() {
        Map<String, Object> initialDocument = new HashMap<>();
        Map<String, Object> expectedDocument =
            Map.of("newFirstName", "John", "newLastName", "Doe", "numOfFields", 4, "newAge", 30, "newEmail",
                   "john.doe@example.com");
        initialDocument.put("newLastName", "Doe");
        initialDocument.put("newAge", 30);
        initialDocument.put("newEmail", "john.doe@example.com");

        String descriptorJSON = """
            {
                "steps": [
                    {
                        "processor": "AddField",
                        "configuration": {
                            "fieldName": "newFirstName",
                            "fieldValue": "John"
                        }
                    },
                    {
                        "processor": "CountNumOfFields",
                        "configuration": {
                            "targetFieldName": "numOfFields"
                        }
                    }
                ]
            }""";

        PipelineDescriptor pipelineDescriptor = new PipelineDescriptorParser().parsePipelineDescriptor(descriptorJSON);
        PipelineExecutor pipelineExecutor = new PipelineExecutor(new DefaultProcessorFactory());

        pipelineExecutor.transform(pipelineDescriptor, initialDocument);

        assertEquals(expectedDocument, initialDocument);
    }
}
