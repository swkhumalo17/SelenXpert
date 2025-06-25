package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Element {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("line")
    @Expose
    private Integer line;
    @SerializedName("steps")
    @Expose
    private List<Step> steps = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("keyword")
    @Expose
    private String keyword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Element withLine(Integer line) {
        this.line = line;
        return this;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Element withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Element withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Element withId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Element withType(String type) {
        this.type = type;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Element withKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
