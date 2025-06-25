package KeywordDrivenTestFramework.Testing.PageObjects;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.Enums;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

public class GCS_PageObject extends BaseClass {
    //All xPaths are created this side
    public static String GCS_URL(String Site){
        return "https://" + Site;
    }

    public static String PartyID() {
        return "//span[@id='lblPartyId']";
    }
}
