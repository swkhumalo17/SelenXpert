package KeywordDrivenTestFramework.Entities;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class DataColumn {
    public String columnHeader;
    public String columnValue;
    public Enums.ResultStatus resultStatus;

    public DataColumn(String header, String value, Enums.ResultStatus result) {
        this.columnHeader = header;
        this.columnValue = value;
        this.resultStatus = result;

    }
}
