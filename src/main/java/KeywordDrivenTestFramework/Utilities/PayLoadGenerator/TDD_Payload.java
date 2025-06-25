package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class TDD_Payload {
    private File CucumberJsonFIle;
    // private BufferedReader Breader;
    //  private FileReader Freader;
    private FileWriter Fwriter;
    private Gson gson;
    private CucumberResult CucumberRunResults[];
    private List<Step> steps;
    private List<Element> elements;

    public TDD_Payload(File CucumberJsonFile) {
        this.CucumberJsonFIle = CucumberJsonFile;
        steps = new ArrayList();
        elements = new ArrayList();
        CucumberRunResults = new CucumberResult[1];
        gson = new GsonBuilder().setPrettyPrinting().create();
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

    public void addSteps(String name, Match match, Result result, String keyword) {
        Step tc_steps = new Step();
        tc_steps.setName(name);
        tc_steps.setMatch(match);
        tc_steps.setResult(result);
        tc_steps.setKeyword(keyword);
        steps.add(tc_steps);
    }

    public void addEements(String desc, String id, String keyword, int line, String name, String type) {
        Element tc_elements = new Element();
        tc_elements.setDescription(desc);
        tc_elements.setId(id);
        tc_elements.setKeyword(keyword);
        tc_elements.setLine(1);
        tc_elements.setName(name);
        tc_elements.setSteps(steps);
        tc_elements.setType(type);
        elements.add(tc_elements);
    }

    public void generatePayload() {
        CucumberRunResults[0] = new CucumberResult();
        CucumberRunResults[0].setElements(elements);
        boolean a = WriteToCucumberJson();
        if (a) {
            System.out.println("yaay");
        } else {
            System.out.println("Meeh");
        }
    }
}
