import java.util.Scanner;
public class Menu{
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
