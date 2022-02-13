import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /** enter path to file, set filePath equal to it **/
    String filePath;
    /** method to convert file into ArrayList **/
    public void CSVToArrayList(String file) {

    }

    /** method to convert file back into csv **/
    public void ArrayListToCSV(ArrayList arr) {

    }

    /** search helper method. searches array list, returns index of array w/ matching product ID**/
    public int search(int id) {

    }

    /** MAIN LOOP **/
    public static void main (String[] args) {

        boolean run = true;
        while (run) {

            int selection;

            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Choose 1 to Create");
                System.out.println("Choose 2 to Read");
                System.out.println("Choose 3 to Update");
                System.out.println("Choose 4 to Delete");
                System.out.println("Choose 5 to EXIT");
                selection = sc.nextInt();
            }

            switch (selection) {
                case 1:

                    /** prompt user for product id, index 0**/
                    Scanner inputProdID = new Scanner(System.in);
                    System.out.println("You have chosen to create a new product.");
                    System.out.println("Enter new product ID:");
                    int prodID = inputProdID.nextInt();

                    /** prompt user for quantity, index 1 **/
                    Scanner inputQuant = new Scanner(System.in);
                    System.out.println("Enter product quantity:");
                    int quant = inputQuant.nextInt();

                    /** prompt user for wholesale_cost, index 2 **/
                    Scanner inputWholesaleCost = new Scanner(System.in);
                    System.out.println("Enter the wholesale cost:");
                    float wholesaleCost = inputWholesaleCost.nextFloat();

                    /** prompt user for sale_price, index 3 **/
                    Scanner inputSalePrice = new Scanner(System.in);
                    System.out.println("Enter the sale price:");
                    float salePrice = inputSalePrice.nextFloat();

                    /** prompt user for supplier_ID, index 4 **/
                    Scanner inputSupplierID = new Scanner(System.in);
                    System.out.println("Enter the supplier ID:");
                    int supplierID = inputSupplierID.nextInt();

                    /** METHOD CALL   ADJUST AS NEEDED **/
                    int[] newProduct = create(prodID, quant, wholesaleCost, salePrice, supplierID);

                    break;

                case 2:
                    System.out.println("You have chosen to read the file.");
                    Scanner inputReadChoice = new Scanner(System.in);
                    System.out.println("Read entire file(1) or read single line(2)?");
                    int readChoice = inputReadChoice.nextInt();

                    if (readChoice == 1) {
                        /** CALL TO READALL METHOD **/
                    } else {
                        Scanner inputReadSingle = new Scanner(System.in);
                        System.out.println("Enter product ID of item:");
                        int readProdID = inputReadSingle.nextInt();
                        /** if product ID exists, call readOne method. if not, throw exception. **/
                    }

                    break;

                case 3:
                    /** ADJUST AS NEEDED TO WORK WITH UPDATE METHOD **/
                    System.out.println("You have chosen to update an item.");

                    /** input product id of product to update**/
                    Scanner inputUpdateID = new Scanner(System.in);
                    System.out.println("Enter product ID to update:");
                    int updateID = inputUpdateID.nextInt();
                    System.out.println("Enter new values. If a value must stay the same, enter nothing.");

                    Scanner inputUpdateQuant = new Scanner(System.in);
                    System.out.println("Enter new quantity:");
                    int updateQuant = inputUpdateID.nextInt();

                    Scanner inputUpdateWholesale = new Scanner(System.in);
                    System.out.println("Enter new wholesale cost:");
                    float updateWholesale = inputUpdateID.nextFloat();

                    Scanner inputUpdatePrice = new Scanner(System.in);
                    System.out.println("Enter new sale price:");
                    float updatePrice = inputUpdateID.nextFloat();

                    Scanner inputUpdateSupplierID = new Scanner(System.in);
                    System.out.println("Enter new supplier ID:");
                    int updateSupplierID = inputUpdateID.nextInt();

                    /** UPDATE METHOD CALL **/
                    update(updateID, updateQuant, updateWholesale, updatePrice, updateSupplierID);

                    break;

                case 4:

                    System.out.println("You have chosen to delete an item.");
                    Scanner inputDeleteID = new Scanner(System.in);
                    System.out.println("Enter product ID of item to delete:");
                    int deleteID = inputDeleteID.nextInt();

                    /** METHOD CALL **/
                    delete(deleteID);

                    break;

                case 5:
                    System.out.println("Bye!");
                    run = false;
                    break;
            }
        }
    }

    /**
     * input '1'
     * CREATE METHOD called from case 1 of switch case
     * takes in 5 parameters, turns into array, appends array to end of arraylist (csv file)
     * **/
    public static int[] create(int productID, int quantity, float wholesaleCost, float salePrice, int supplierID) {
        /** create new list**/

        /** append list to ArrayList**/

    }

    /** input '2' **/
    /** READ METHOD **/
    /** read all or one product? **/
    /** if all, loop through ArrayList and print all to console **/

    public static int[][] readAll() {
        /** basically return entire csv file**/
    }

    /** if one, prompt for product id and print product array to console **/
    public static int[] readOne(int productID) {
        /** use search() method if needed**/
    }

    /** input '3' **/
    /** UPDATE METHOD **/
    /** note: if nothing is entered for a prompt, the current value will stay the same. **/
    public static void update(int prodID, int newQuant, float newWholesale, float newPrice, int newSupplierID) {

    }


    /** input '4' **/
    /** DELETE METHOD **/
    public static void delete(int prodID) {

    }

}