package org.example;

public class PipelineData {
    public static String getPipelineDescriptor() {
        return """
            {
              "steps": [
                {
                  "processor": "AddField",
                  "configuration": {
                    "fieldName": "accountName",
                    "fieldValue": "Facebook"
                  }
                },
                {
                  "processor": "RemoveField",
                  "configuration": {
                    "fieldName": "userName"
                  }
                },
                {
                  "processor": "AddField",
                  "configuration": {
                    "fieldName": "userName",
                    "fieldValue": "David"
                  }
                },
                {
                  "processor": "CountNumOfFields",
                  "configuration": {
                    "targetFieldName": "numOfFields"
                  }
                }
              ]
            }
            """;
    }
}
