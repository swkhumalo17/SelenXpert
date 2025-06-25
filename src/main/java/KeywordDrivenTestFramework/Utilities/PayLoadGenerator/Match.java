package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Match {
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("arguments")
    @Expose
    private List<Argument> arguments = null;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Match withLocation(String location) {
        this.location = location;
        return this;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public Match withArguments(List<Argument> arguments) {
        this.arguments = arguments;
        return this;
    }
}
