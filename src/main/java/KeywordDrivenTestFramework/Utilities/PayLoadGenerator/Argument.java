package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Argument {
    @SerializedName("val")
    @Expose
    private String val;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Argument withVal(String val) {
        this.val = val;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Argument withOffset(Integer offset) {
        this.offset = offset;
        return this;
    }
}
