package KeywordDrivenTestFramework.Entities;

import static java.lang.System.err;

import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class DataRow {
    public LinkedList<DataColumn> DataColumns;

    public DataRow() {
        DataColumns = new LinkedList<>();
    }

    public String getColumnValue(String columnHeader) {
        try {
            Predicate<DataColumn> predicate = c -> c.columnHeader.equals(columnHeader);
            DataColumn obj = DataColumns.stream().filter(predicate).findFirst().get();
            return obj.columnValue;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnHeader + " - in table row");
            return "";
        }
    }

    public DataColumn getColumn(String columnHeader) {
        try {
            Predicate<DataColumn> predicate = c -> c.columnHeader.equals(columnHeader);
            DataColumn obj = DataColumns.stream().filter(predicate).findFirst().get();
            return obj;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnHeader + " - in table row");
            return null;
        }
    }
}
