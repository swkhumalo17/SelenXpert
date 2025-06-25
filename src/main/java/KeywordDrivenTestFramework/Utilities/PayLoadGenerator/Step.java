package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Step {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("line")
    @Expose
    private Integer line;
    @SerializedName("match")
    @Expose
    private Match match;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("keyword")
    @Expose
    private String keyword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Step withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Step withLine(Integer line) {
        this.line = line;
        return this;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Step withMatch(Match match) {
        this.match = match;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Step withResult(Result result) {
        this.result = result;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Step withKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
