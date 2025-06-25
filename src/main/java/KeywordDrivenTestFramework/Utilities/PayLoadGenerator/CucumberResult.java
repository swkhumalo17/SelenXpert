package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class CucumberResult {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("line")
    @Expose
    private Integer line;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("elements")
    @Expose
    private List<Element> elements = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CucumberResult withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CucumberResult withId(String id) {
        this.id = id;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public CucumberResult withKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CucumberResult withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public CucumberResult withLine(Integer line) {
        this.line = line;
        return this;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public CucumberResult withTags(List<Object> tags) {
        this.tags = tags;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public CucumberResult withUri(String uri) {
        this.uri = uri;
        return this;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public CucumberResult withElements(List<Element> elements) {
        this.elements = elements;
        return this;
    }
}
