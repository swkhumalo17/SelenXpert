package KeywordDrivenTestFramework.Testing.TestClasses.DAE_Sanity.OM_SecureServices;

/**
 * @author SKHUMALO on 2022/07/08.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Client {
    private String firstName;
    private String lastName;
    private String email;
    private String idNo;

    public Client(String firstName, String lastName, String email, String idNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.idNo = idNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String email) {
        this.idNo = idNo;
    }
}
