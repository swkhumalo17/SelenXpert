package KeywordDrivenTestFramework.Entities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

// This class contains the definition for a collection of data retrieved from the browser
// during a test run - if a test needs to store a reference number for example.

public class RetrievedTestValues {
    public Map<String, String> RetrievedValuesList;

    public RetrievedTestValues() {

    }

    public void addParameter(String retrievedValueName, String retrievedValue) {
        if (RetrievedValuesList == null) {
            this.RetrievedValuesList = new HashMap<>();
        }
        this.RetrievedValuesList.put(retrievedValueName, retrievedValue);
    }
}
