package KeywordDrivenTestFramework.Entities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface KeywordAnnotation {
    String Keyword();

    //Use to restart - init the selenium instance
    boolean createNewBrowserInstance() default false;

    //Use to init the Appium Instance - currently wont restart
    boolean appiumTest() default false;

    //Use to init WinAppDriver
    boolean winAppTest() default false;

    //Use to mark a test as skippable
    //This will mean the test will log a skip if the previous test failed
    boolean blockable() default false;
}
