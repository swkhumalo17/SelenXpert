package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class CucumberResultsEditor {
    private File CucumberJsonFIle;
    private BufferedReader Breader;
    private FileReader Freader;
    private FileWriter Fwriter;
    private Gson gson;
    private CucumberResult[] CucumberRunResults;

    public CucumberResultsEditor(File CucumberJsonFile) {
        try {
            this.CucumberJsonFIle = CucumberJsonFile;
            Freader = new FileReader(CucumberJsonFIle);
            Breader = new BufferedReader(Freader);
            gson = new GsonBuilder().setPrettyPrinting().create();
            CucumberRunResults = gson.fromJson(Breader, CucumberResult[].class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CucumberResultsEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addDeviceToScenarioName(String deviceName) {
        String ScenarioName = CucumberRunResults[0].getElements().get(0).getName();
        System.out.println("Before -> " + ScenarioName);
        if (!ScenarioName.contains(deviceName)) {
            CucumberRunResults[0].getElements().get(0).setName(ScenarioName + " - " + deviceName);
        }
        ScenarioName = CucumberRunResults[0].getElements().get(0).getName();
        System.out.println("After -> " + ScenarioName);
    }

    public void addLocationForIOS() {
//       Element iosResults =  CucumberRunResults[0].getElements().get(0).getSteps().get(0).getMatch().setLocation(location)

        List<Element> IOSElementResults = CucumberRunResults[0].getElements();
        int counter = 1;
        for (Element e : IOSElementResults) {
            for (Step s : e.getSteps()) {
                Match match = new Match();
                match.setLocation("Step" + counter);
                s.setMatch(match);
                counter++;
            }
        }
    }

    public boolean WriteToCucumberJson() {
        boolean correct = false;
        try {
            Fwriter = new FileWriter(CucumberJsonFIle);
            gson.toJson(CucumberRunResults, Fwriter);
            correct = true;
        } catch (IOException ex) {
            Logger.getLogger(CucumberResultsEditor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (Fwriter != null) {
                try {
                    Fwriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(CucumberResultsEditor.class.getName()).log(Level.SEVERE, null, ex);
                    correct = false;
                }
            }
        }
        return correct;
    }

    public boolean writeIOSResults(String deviceName) {
        try {
            addDeviceToScenarioName(deviceName);
            addLocationForIOS();
            WriteToCucumberJson();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
