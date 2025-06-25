package KeywordDrivenTestFramework.Utilities.IDGenerator;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class GenerateRandBetween {
    int min;
    int max;

    public GenerateRandBetween(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public GenerateRandBetween() {
    }

    public int getRandBetween(int min, int max){
        return (min + (int) Math.round(Math.random() * (max - min)));
    }
}
