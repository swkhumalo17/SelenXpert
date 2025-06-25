package KeywordDrivenTestFramework.Utilities.IDGenerator;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

public class DataGenUtils {
    private String alpha = "abcdefghijklmnopqrstuvwxyz";
    private String alphaCaps = alpha.toUpperCase();
    private String alphaSmallCaps = alpha + alphaCaps;
    private String num = "0123456789";
    private String alphaNum = alphaSmallCaps + num;

    static String[] arrayOfSurnames = {"Morley", "Scott", "Kruger", "Lain", "Kennedy",
            "Gawron", "Han", "Hall", "Aydogdu", "Grace", "Spiers", "Perera", "Smith",
            "Connoly", "Sokolowski", "Chaow", "James", "June", "Khumalo", "Masondo"};
    static String[] arrayOfNamesMale = {"Sibusiso", "Sifiso", "Sipho", "Siyabonga",
            "Sizwe", "Tau", "Teboho", "Thabani", "Thabo", "Themba"};
    static String[] arrayOfNamesFemale = {"Amahle", "Annika", "Bakang", "Bonolo",
            "Busisiwe", "harlize", "Elna", "Gugulethu", "Hlengiwe", "Imka"};
    static String[] arrayOfInitials = {"A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    static String[] domainNamesArray = {"Oldmutual.com", "Outlook.com", "gmail.com", "inbox.com", "mail.com"};
    static String[] Gender =  {"Male", "Female"};
    static String[] southAfricaAddressArray = {"1947 Main Rd,Harding,KwaZulu-Natal,4683,South_Africa", "1556 Doreen St,Rustenburg,North West,366,South_Africa",
            "1194 Voortrekker St,Isipingo Beach,KwaZulu-Natal,4115,South_Africa", "1204 Bezuidenhout St,Ermelo,Mpumalanga,2357,South_Africa",
            "25 Barlow Street,Mokopane,Limpopo,654,South_Africa", "1374 Sandown Rd,Clanwilliam,Western Cape,8138,South_Africa",
            "1961 Amos St,Hammanskraal,Gauteng,413,South_Africa", "2318 Loop St,Vredenburg,Western Cape,7390,South_Africa",
            "2135 St. John Street,Strand,Western Cape,7140,South_Africa", "1317 Plane St,Butterworth,Eastern Cape,4968,South_Africa",
            "474 Sandown Rd,Graafwater,Western Cape,8121,South_Africa", "267 Dickens St,Kempton Park,Gauteng,1621,South_Africa"};

    static String Name;
    static String Surname;

    public static void main(String[] args) {
        DataGenUtils data = new DataGenUtils();
        //Generate SA ID
		/*for(int i=0; i<50; i++){
			System.out.println(data.saidGen("1984-10-22", "M"));
		}
		System.out.println(data.randomAlphaNum(8));
		*/
        String count = "12";
        System.out.println(data.optionTechGuid());
        System.out.println(data.randomAlphaSmallCaps(Integer.parseInt(count, 10)));
        System.out.println(data.randomNum(Integer.parseInt(count, 10)));
        System.out.println(data.randomAlphaNum(Integer.parseInt(count, 10)));


    }

    //Email Generator
    public String emailGen() {
        String Initials, UniqueUserName, email;
        int RandomNumber = new Random().nextInt(domainNamesArray.length);

        Initials = getName().substring(0, 1);
        UniqueUserName = Initials + getSurname();

        email = UniqueUserName + "@" + domainNamesArray[RandomNumber];

        return email;
    }

    //SA ID Generator
    /**
     * Generate Valid SA ID given date of birth
     *
     * @param dob 850101: �Year of birth, Month of birth and Day of birth, 1 January 1985�
     *            6: �0-4, Female, 5-9, Male�
     *            184: No explanation given
     *            0: �0, SA Citizen, 1, Non SA Citizen�
     */
    public String saidGen(String dob, String gender) {
        String[] dobArray = dob.split(","); //YYYY-MM-DD
        String dobIdStr = null;
        Random random = new Random();
        Object genderId = 0;
        if (dobArray.length == 3) {
            String yearId = dobArray[0].substring(2, 4);
            String monthId = dobArray[1];
            String dayId = dobArray[2];
            dobIdStr = yearId + monthId + dayId;
        }
        //random.nextInt(max - min + 1) + min
        //Female ID
        if (gender.toUpperCase().trim().equals("FEMALE") || gender.toUpperCase().trim().equals("F")) {
            //System.out.println("FEMALE");
            genderId = random.nextInt(5);
        }

        //Male ID
        if (gender.toUpperCase().trim().equals("MALE") || gender.toUpperCase().trim().equals("M")) {
            //System.out.println("MALE");
            genderId = random.nextInt(5) + 5;

        }
        String genderIdStr = genderId.toString();

        //Next three digits (Sequence Number)
        Object sqnum = random.nextInt(900) + 100;
        String sqnumStr = sqnum.toString();

        //Citizen (0 - SA, 1 - Non SA)
        String citizen = "0";

        // X, 8 or 9 (no meaning)
        Object xDigit = random.nextInt(2) + 8;
        String xDigitStr = xDigit.toString();

        //Idnumber
        String idNumber = dobIdStr + genderIdStr + sqnumStr + citizen + xDigitStr;

        //Check digit

        String chkDigit = calculateCheckDigit(idNumber);

        return idNumber + chkDigit;

    }

    /**
     * Calculates the last digits for a number received as parameter
     *
     * @param card {@link String} number
     * @return {@link String} the check digit
     */
    public static String calculateCheckDigit(String card) {
        if (card == null)
            return null;
        String digit;
        /* convert to array of int for simplicity */
        int[] digits = new int[card.length()];
        for (int i = 0; i < card.length(); i++) {
            digits[i] = Character.getNumericValue(card.charAt(i));
        }

        /* double every other starting from right - jumping from 2 in 2 */
        for (int i = digits.length - 1; i >= 0; i -= 2) {
            digits[i] += digits[i];

            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
        }
        /* multiply by 9 step */
        sum = sum * 9;

        /* convert to string to be easier to take the last digit */
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }

    //Names Generator
    public static String generateFullName(String Gender) {
        String FullName;

        int RandomNumber = new Random().nextInt(arrayOfNamesMale.length);

        if (Gender == "Male") {
            Name = arrayOfNamesMale[RandomNumber];
        } else {
            Name = arrayOfNamesFemale[RandomNumber];
        }
        Surname = arrayOfSurnames[RandomNumber];

        FullName = Name + " " + Surname;

        return FullName;
    }

    public String getSurname() {
        return Surname;
    }

    public String getName() {
        return Name;
    }

    //SA Cellpone Generator
    public static String RandomPhoneNum() {

        Random generator = new Random();
        String CellNumber;
        String strippedNum;
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;

        num1 = generator.nextInt(9);
        num2 = generator.nextInt(641) + 100;//number has to be less than 742//can't go below 100.
        num3 = generator.nextInt(8999) + 1000; // make numbers 0 through 9 for each digit.//can't go below 1000.

        String string1 = Integer.toString(num1);
        strippedNum = Integer.toOctalString(num1);

        return CellNumber = ("08" + strippedNum + " " + num2 + " " + num3);
    }

    public static String getGender(){
        int RandomNumber = new Random().nextInt(Gender.length);

        return Gender[RandomNumber];
    }

    //Password Generator
    public String generatePassayPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }

    //SAP Address generator
    public static String saAddressGenerator() {
        String Address;
        int RandomNumber = new Random().nextInt(southAfricaAddressArray.length);

        Address = southAfricaAddressArray[RandomNumber];

        return Address;
    }

    //Date Generator

    public String dateGen(String pattern, int c) {
        /*
          Change m (month) to M, please note m represents minutes in date format
          Change D (day) to d
         */
        pattern = pattern.replace("m", "M");
        pattern = pattern.replace("D", "d");

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, c);  // number of days to add
        String newDate = (String) (format.format(cal.getTime()));

        return newDate;
    }

    //File name time stamp
    public String fileNameTimeStamp(String pattern, int c) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, c);  // number of days to add
        String newDate = (String) (format.format(cal.getTime()));

        return newDate;
    }

    /* UUID or GUID Generator
     *
     */
    public String optionTechGuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * @param str - input string (source of characters for random generator)
     * @param le  - length of string to be generated
     * @return output - generated string
     */
    public String randomMaster(String str, int le) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < le; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();

        return output;
    }

    public String randomAlphaSmallCaps(int le) {
        return this.randomMaster(this.alphaSmallCaps, le);
    }

    public String randomAlphaNum(int le) {
        return this.randomMaster(this.alphaNum, le);
    }

    public String randomNum(int le) {
        return this.randomMaster(this.num, le);
    }

}
