package KeywordDrivenTestFramework.Testing.TestClasses.DAE_Sanity.OM_SecureServices;

import java.util.Scanner;

/**
 * @author SKHUMALO on 2023/04/01.
 * @project DAE-Automation-Framework
 */
public class ProfitDetermining {
    public static void main(String[] args) {
        //Declaration and initialization
        Scanner scanner = new Scanner(System.in);
        double totalProfit = 0;
        double salePrice = 1;

        //Sentinel >> This will control your program if the user enters 0 it will stop
        while (salePrice != 0) {
            System.out.print("Enter the sale price of the car (enter 0 to stop): ");
            salePrice = scanner.nextDouble();

            if (salePrice != 0) {
                System.out.print("Enter the cost price of the car: ");
                double costPrice = scanner.nextDouble();
                double profit = salePrice - costPrice;
                totalProfit += profit;
                System.out.println("The profit on this sale is R" + profit);
                System.out.println("The total profit so far is R" + totalProfit);
            }
        }
        //Your end output
        System.out.println("You have entered 0 as the sale price. End of job.");
    }
}
