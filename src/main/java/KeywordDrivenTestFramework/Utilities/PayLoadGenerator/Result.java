package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Result {
    @SerializedName("duration")
    @Expose
    private long duration;
    @SerializedName("status")
    @Expose
    private String status;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Result withDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result withStatus(String status) {
        this.status = status;
        return this;
    }
}
