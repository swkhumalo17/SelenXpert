package KeywordDrivenTestFramework.Utilities.PayLoadGenerator;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class GenerateNewPayload {
    private DateFormat df;
    private File QASFolder;
    private File payloadFile;

    public GenerateNewPayload() {
        df = new SimpleDateFormat("yyyyMMddHHmmss");
        QASFolder = new File("QAS");
        payloadFile = new File(QASFolder, String.format("results-%s", df.format(new Date())));
        if (!QASFolder.exists()) {
            try {
                QASFolder.mkdirs();
                payloadFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(TDD_Payload.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                payloadFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(TDD_Payload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public File generateNewPayloadFile() {
        return payloadFile;
    }

}
