package KeywordDrivenTestFramework.Entities.AppTable;

import org.openqa.selenium.WebElement;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class AppTableColumn {
    public String columnName;
    public String cellText;
    public WebElement element;


    public AppTableColumn(String column, String text, WebElement webElement) {
        this.columnName = column;
        this.cellText = text;
        this.element = webElement;
    }
}
