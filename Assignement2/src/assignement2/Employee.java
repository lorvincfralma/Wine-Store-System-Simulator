package assignement2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
 
/**
 * The class {@code Employee} is used to represents employee entity.
 * This class inherit from People class.  
 */

public class Employee extends People {
		
	/**
	 * Class constructor.
	 * @param name It is the employee's name.
	 * @param surname It is the employee's surname.
	 * @param email It is the employee's email.
	 * @param password It is the employee's password.
	 */
	
	public Employee(final String name, final String surname, final String email, final String password) {
		super(name, surname, email, password);
	}
	
	/**
	 * This method is used by employee to delivers orders to users.
	 * @param list is the list that contains orders
	 */
	
	public void deliveryWine(final List<Order<User,Wine>> list) { // an employee sends the bottle of wine to the customer
		if(!list.isEmpty()) { // if the order list is not empty, then send orders
			LocalDateTime dateTime = LocalDateTime.now(); // gets the current date and time
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        
	    	for(int i = 0; i < list.size();) {	    		
	    		if(!list.get(i).getDelivered()) { // if the order has not yet been sent, it must be sent
	    			
	    			Wine w = list.get(i).getWine(); // ordered wine
	    			User u = list.get(i).getPerson(); // user who did the order
	    			
		    		
	    			System.out.println("\n(SYSTEM - USER) -> [" + u.getName() + ", " + u.getEmail() + "], " + this.getName() 
	    				+ " has shipped your order: [" + w.getName() + ", " + w.getYear() + ", " + w.getNumBottles() 
	    					+ "]. to the address " + u.getAddress() + " " + dateTime.format(formatter));
					
		    		list.get(i).setDelivered(true); // order has been shipped (order list contains both shipped and unshipped order)
		    	}
			}
		}
		
		else System.out.println("\n(SYSTEM - EMPLOYEE) -> " + this.getName() + " there aren't order in queue.\n");
	}
	
	/**
	 * This method is used by employee to reload bottles of wine.
	 * @param w is the wine that must be reloaded
	 * @param n is the number of wine's bottles that must be reloaded
	 * @param ws is the WineStoreSystem instances
	 */
	
	private static void reloadBottles(final Wine w, final int n, final WineStoreSystem ws) { 
		ws.updateSystemData(w, n); // employee update system data	
	}
	
	/**
	 * This method is used by employee to satisfy the registered user's requirements.
	 * @param ws It is the instance of Wine Store
	 * 
	 * {@link WineStoreSystem#reportWineAvailable() }
	 */
	
	public static void buyNewBottles(final WineStoreSystem ws) {
		int nNewBottles = 0;
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		
		for(int i = 0; i < ws.notificationList.size(); i++) { // go through the whole list of people who have subscribed a notification to eventually inform them that the wine is available
			
			do{
				System.out.println("\n(SYSTEM - EMPLOYEE) -> The user: [" + ws.notificationList.get(i).getPerson().getName() 
					+ "] has subscribed a notification for: " + ws.notificationList.get(i).getWine().getNumBottles() 
						+ " bottles of " + ws.notificationList.get(i).getWine().getName() + "\n");
				
				System.out.print("\n(SYSTEM - EMPLOYEE) -> How many bottles of [" + ws.notificationList.get(i).getWine().getName() 
					+ ", " + ws.notificationList.get(i).getWine().getYear() + "] do you want to buy from producer: " 
						+ ws.notificationList.get(i).getWine().getProducer() + "?\n:");
				
				nNewBottles = keyboard.nextInt();
			  
				if(nNewBottles <= 0) System.out.println("\n(SYSTEM - EMPLOYEE) -> You can't buy from producer/supplier zero ore negative number of bottles.");
				
			} while (nNewBottles <= 0);
			
			Employee.reloadBottles(ws.notificationList.get(i).getWine(), nNewBottles, ws);
		}
		
		ws.reportWineAvailable(); // ws is used to call the method used to inform customers that the wine is available
	}
	
	// N.B: Per la simulazione è richiesto che non ci debbano essere bottiglie disponibili del vino UY.
				// pertanto è stata disabilitata la funzionalità che non permette di acquisire un numero di bottiglie
					// non valido (zero o minore di zero).
	
	/**
	 * This method is used by employee to buy new bottles when there are no more available.
	 * @param ws It is the instance of Wine Store
	 * @param w It is the finished wine to buy
	 */
		
	public static void buyFinischedBottles(final WineStoreSystem ws, final Wine w) {
		int nNewBottles = 0;
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
			
		//do{
			System.out.print("\n(SYSTEM - EMPLOYEE) -> How many bottles of [" + w.getName() + ", " + w.getYear() 
				+ "] do you want to buy from producer (enter 0 if you don't want to buy it now): " + w.getProducer() + "?\n:");
			nNewBottles = keyboard.nextInt();
		  
			//if(nNewBottles == 0) System.out.println("\n(SYSTEM - EMPLOYEE) -> You can't buy zero bottles.");
			
		//} while (nNewBottles <= 0);
			
		if(nNewBottles != 0) Employee.reloadBottles(w, nNewBottles, ws);
	}
}
