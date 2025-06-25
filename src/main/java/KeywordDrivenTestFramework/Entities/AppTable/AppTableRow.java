package KeywordDrivenTestFramework.Entities.AppTable;

import static java.lang.System.err;
import java.util.LinkedList;
import java.util.function.Predicate;
import org.openqa.selenium.WebElement;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class AppTableRow {
    public LinkedList<AppTableColumn> DataColumns;

    public AppTableRow() {
        DataColumns = new LinkedList<>();
    }

    public String getColumnValue(String columnHeader) {
        try {
            Predicate<AppTableColumn> predicate = c -> c.columnName.equals(columnHeader);
            AppTableColumn obj = DataColumns.stream().filter(predicate).findFirst().get();
            return obj.cellText;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnHeader + " - in table row");
            return "";
        }

    }

    public WebElement getElement(String columnHeader) {
        try {
            Predicate<AppTableColumn> predicate = c -> c.columnName.equals(columnHeader);
            AppTableColumn obj = DataColumns.stream().filter(predicate).findFirst().get();
            return obj.element;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnHeader + " - in table row");
            return null;
        }

    }

    public WebElement getElementByText(String columnText) {
        try {
            Predicate<AppTableColumn> predicate = c -> c.cellText.equals(columnText);
            AppTableColumn obj = DataColumns.stream().filter(predicate).findFirst().get();
            return obj.element;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnText + " - in table row");
            return null;
        }

    }

    public AppTableColumn getColumn(String columnHeader) {
        try {
            Predicate<AppTableColumn> predicate = c -> c.columnName.equals(columnHeader);
            AppTableColumn obj = DataColumns.stream().filter(predicate).findFirst().get();
            return obj;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnHeader + " - in table row");
            return null;
        }
    }
}
