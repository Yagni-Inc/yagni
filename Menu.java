import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Menu{
  
  public static void main(String[]args){
    Menu newMenu = new Menu();
    Scanner scInput = new Scanner(System.in);
    boolean done = false;
  
    while(!done){

      int selection;
      int selection3;
      int selection4;
      int selection5;
    
      System.out.println("Choose 1 to Create");
      System.out.println("Choose 2 to Read");
      System.out.println("Choose 3 to Update");
      System.out.println("Choose 4 to Delete");
      System.out.println("Choose 5 to EXIT");
      selection = scInput.nextInt();

      switch(selection){
        
        // Switch case for the add/create method
        case 1:
          newMenu.AddRecord();
          break;
      
        case 2:
          System.out.println("Read here");
          System.out.println("Enter product ID");
          Scanner obj3=new Scanner(System.in);
          selection3=obj3.nextInt();
          System.out.println(selection3);
          break;
        
        case 3:
          System.out.println("Update here");
          System.out.println("Enter product ID to search");
          Scanner obj4=new Scanner(System.in);
          selection4=obj4.nextInt();
          System.out.println(selection4);
          break;
          
        case 4:
          System.out.println("Delete here");
          System.out.println("Enter product ID to delete");
          Scanner obj5=new Scanner(System.in);
          selection5=obj5.nextInt();
          System.out.println(selection5);
          break;

        case 5:
        System.out.println("Bye!");
        done = true;
        scInput.close();
        System.exit(0);
      }        
    }   
  }

  //Method that adds records to the inventory file
  //Implemented by Kyle Zimmerman 2.12.22
  public void addRecord(){

    String fileName = "inventory_team4.csv"; // variable for hardcoded file name
    BufferedWriter buffWrite = null; //Setting up the buffered writer variable and setting initially to null
    String product_id, quantity, wholesale_cost, sale_price, supplier_id; //Setting up all the sting variables that I will be sett to user inputs
    Scanner strInput2 = new Scanner(System.in); //Initializing a scanner object

    // User input for the fields of the inventory and setting them to the appropriate variables
    //Product ID input
    System.out.print("Enter the product ID: ");
    product_id = strInput2.nextLine();

    //Quantity input
    System.out.print("Enter the Quantity: ");
    quantity = strInput2.nextLine();

    //Wholesale cost input
    System.out.print("Enter the wholesale cost: ");
    wholesale_cost = strInput2.nextLine();

    //Sale price input
    System.out.print("Enter the sale cost: ");
    sale_price = strInput2.nextLine();

    //Supplier ID input
    System.out.print("Enter the supplier ID: ");
    supplier_id = strInput2.nextLine();

    // Writing to the inventory file
    // Try catches IOExecption
    try { 
      buffWrite = new BufferedWriter(new FileWriter(fileName,true)); //Setting our buffered writer to the correct file
      buffWrite.write(product_id+","+quantity+","+wholesale_cost+","+sale_price+","+supplier_id); //Writing to the file
      buffWrite.flush();
      buffWrite.newLine();
      buffWrite.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
	public static void main(String[]args){
	
	boolean done = false;
		while(!done){
	
			int selection;
			int selection2;
			int selection3;
			int selection4;
			int selection5;
	
			try (Scanner obj = new Scanner(System.in)) {
				System.out.println("Choose 1 to Create");
				System.out.println("Choose 2 to Read");
				System.out.println("Choose 3 to Update");
				System.out.println("Choose 4 to Delete");
				System.out.println("Choose 5 to EXIT");
				selection=obj.nextInt();
			}

			switch(selection){
				case 1:
				Scanner obj2 = new Scanner(System.in);
				System.out.println("Create here");
				System.out.println("Enter product ID");
				selection2=obj2.nextInt();
				System.out.println(selection2);
				break;
			
				case 2:
				System.out.println("Read here");
				System.out.println("Enter product ID");
				Scanner obj3=new Scanner(System.in);
				selection3=obj3.nextInt();
				System.out.println(selection3);
				break;
				
				case 3:
				System.out.println("Update here");
				System.out.println("Enter product ID to search");
				Scanner obj4=new Scanner(System.in);
				selection4=obj4.nextInt();
				System.out.println(selection4);
				break;
				
				case 4:
				System.out.println("Delete here");
				System.out.println("Enter product ID to delete");
				Scanner obj5=new Scanner(System.in);
				selection5=obj5.nextInt();
				System.out.println(selection5);
				break;

				case 5:
				System.out.println("Bye!");
				done = true;
				break;				
			}
		}
	}
}
