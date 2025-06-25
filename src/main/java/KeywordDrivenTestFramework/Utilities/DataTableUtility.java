package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Entities.DataColumn;
import KeywordDrivenTestFramework.Entities.DataRow;

import static java.lang.System.err;

import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class DataTableUtility {
    public DataRow getSpecificRowByColumnValue(LinkedList<DataRow> dataTable, String columnName, String columnValue) {
        try {
            Predicate<DataRow> predicate = c -> c.getColumnValue(columnName).equals(columnValue);

            DataRow obj = dataTable.stream().filter(predicate).findFirst().get();
            return obj;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find row containing - " + columnName + " - in table rows");
            return null;
        }

    }
}
