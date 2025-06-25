package KeywordDrivenTestFramework.Entities.AppTable;

import static java.lang.System.err;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.openqa.selenium.WebElement;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class AppTable {
    public List<AppTableRow> Rows;

    public AppTable(List<AppTableRow> rows) {
        this.Rows = rows;
    }


    public List<String> getColumnValues(String columnHeader) {
        List<String> column = new ArrayList<>();
        try {
            for (AppTableRow row : Rows) {
                Predicate<AppTableColumn> predicate = c -> c.columnName.equals(columnHeader);
                AppTableColumn obj = row.DataColumns.stream().filter(predicate).findFirst().get();
                column.add(obj.cellText);
            }
            return column;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnHeader + " - in table");
            return null;
        }

    }

    public List<String> getRowValues(int rowIndex) {
        List<String> column = new ArrayList<>();
        try {
            AppTableRow row = Rows.get(rowIndex);

            for (AppTableColumn columns : row.DataColumns) {
                column.add(columns.cellText);
            }

            return column;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find row - " + rowIndex + " - in table");
            return null;
        }

    }

    public WebElement getElementByText(String text) {
        try {
            //Iterates through rows
            for (AppTableRow row : Rows) {
                //If object is not found it will throw
                try {
                    Predicate<AppTableColumn> predicate = c -> c.cellText.equals(text);
                    AppTableColumn obj = row.DataColumns.stream().filter(predicate).findFirst().get();
                    return obj.element;
                } catch (Exception e) {

                }

            }
            //Nothing found which matches the perdicate
            return null;

        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + text + " - in table row");
            return null;
        }
    }
}
