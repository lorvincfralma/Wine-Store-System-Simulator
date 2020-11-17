package assignement2;

import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The class {@code User} is used to represents user entity. 
 * This class inherit from People class.
 */

public class User extends People {

	/**
	 * Class fields.
	 * notifyList - It is a list that contain
	 * flagRegistered - It is a flag that indicates if a user is registered. address
	 * order - It is the user's order
	 * address - It is the user's address. It is used to send the order.
	 */
	
	protected List<String> notifyList = new LinkedList<>(); // this list contains all the availability notifications sent by the system to the user
	private Boolean flagRegistered; /* true -> registered || false -> not registered */
	private Order<User, Wine> order = null;
	private String address;
	
	/**
	 * Class constructor.
	 * It is used by unregistered user
	 */

	public User() { this.setFlagRegistered(false); }
	
	/**
	 * This method is used by the user to login into the wine store account
	 * @return (True - log in successfully completed, False - wrong log in)
	 * @throws IOException signals that an I/O exception has occurred.
	 */ 

	public boolean signIn() throws IOException {
		try {
			String file = "login.txt"; // file that contains registered users

			String line = " ";
			String name = " "; // email instead name
			String pass = " ";
			boolean isLoginSuccess = false; // this is a flag used to verify that login ha been successfully completed
			boolean continue_ = true; // this flag is used to allow user to retry login or discard login

			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);

			System.out.println("\t----------");
			System.out.println("\t| LOGIN: |");
			System.out.println("\t----------");

			while (continue_) {		

				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);

				System.out.print("\n(USER) -> Insert user name: "); String inpUser = keyboard.nextLine();

				System.out.print("\n(USER) -> Insert password: "); String inpPass = keyboard.nextLine();

				while ((line = br.readLine()) != null) { // it checks if user is registered. Registered user informations are stored into a file (like a DataBase)
					name = line.split(",")[0]; // N.B: Use EMAIL instead NAME to guarantee unique access. --> email = line.split(",")[2];
					pass = line.split(",")[3];
										
					if(!(this.getName().equals(inpUser) && this.getPassword().equals(inpPass))) { // it checks that credentials entered by user who want to logging (user who used the method) are the same that has been used during registration --> an instance of user can't access with credential of another instance of user. 
						System.out.println("\nYou aren't " + this.getName()); // use email instead name
						break;
					}

					if (inpUser.equalsIgnoreCase(name) && inpPass.equals(pass)) { // email instead name --> inpUser.equals(email)
						isLoginSuccess = true;
						System.out.println("\n(SYSTEM - USER) -> Login has been successfully completed!");
						break;
					}
				}

				if (isLoginSuccess) { // login has been successfully completed
					br.close();
					fr.close();
					return true;
				}

				else {
					System.out.print("\n(SYSTEM - USER) -> Wrong credential or user not registered!"
							+ "\nDo you want to continue? (insert true to continue)\n: ");
					continue_ = Boolean.parseBoolean(keyboard.nextLine());
				}

				br.close();
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return false; // wrong login
	}

	/**
	 * This method is used by user to signUp into the wine store.
	 * @param name is the user'name that want to be registered into the system.
	 * @param surname is the user's name that want to be registered into the system.
	 * @param email is the user's email that want to be registered into the system.
	 * @param passwd is the user's password that want to be registered into the system.
	 * @param address is the user's address that want into be registered 
	 */
	
	public void signUp(final String name, final String surname, final String email, final String passwd, final String address) {
		
		if (!this.getFlagRegistered()) { // it checks that the user who want to register with a series of credential isn't already registered
			
			try {
				File file = new File("login.txt"); // file that will contains registered user credentials
				FileWriter fr;
				fr = new FileWriter(file, true);
				fr.write(name + "," + surname + "," + email + "," + passwd + "," + address + "\n"); // it writes user's credential/informations into a file (like a DataBase)
				fr.close();
			} 
			
			catch (IOException e) {
				e.printStackTrace();
			}			
			
			this.setName(name);
			this.setSurname(surname);
			this.setEmail(email);
			this.setPassword(passwd);
			this.setAddress(address);
			this.setFlagRegistered(true);
			
			System.out.println("\n(USER) -> [" + this.getName() + ", " + this.getEmail() +  ", " + this.getPassword() 
				+ "] registration has been successfully completed!\n");			
		}

		else System.out.println("\n(USER) -> You [" + this.getName() + ", " + this.getEmail() + "] are already registered!");
	}
	
	/**
	 * This method is used to print the vines' list
	 * @param list is the vines list
	 */
	
	private void printVines(final List<String> list) {
		System.out.print("Vines: [ ");

		for (String v : list) {
			System.out.print(v + " ");
		}
		System.out.println("]");
	}
	
	/**
	 * This method is used to search the wine into the system
	 * @param list is the list of wines
	 * @return (Wine - search successfully , null - search went wrong)
	 */
	
	public Wine searchWine(final List<Wine> list) {
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		Boolean continue_ = true;
		Wine tmpWine = null;

		while (continue_) {
			System.out.print("\n(USER) -> Insert wine name: "); String name = keyboard.nextLine();
			//System.out.print("\n(USER) -> Insert wine producer: "); String producer = keyboard.nextLine(); --> you can search wine by name - producer - year
			System.out.print("\n(USER) -> Insert wine year: ");	String year = keyboard.nextLine();

			for (Wine w : list) { // go through wine store's wine list to check that the desired wine is available
				if (name.equalsIgnoreCase(w.getName()) && year.equalsIgnoreCase(w.getYear())) {
					System.out.print("\n(SYSTEM) -> I found: [" + w.getName() + ", " + w.getProducer() + ", " + w.getYear()
							+ "] and there are avalaible: " + w.getNumBottles() + " bottles. ");
					printVines(w.getVines());
					tmpWine = w;
				}
			}

			System.out.print("\nDo you want to continue to sarch? (insert true to continue)\n: "); // It allows user to do another search
			continue_ = Boolean.parseBoolean(keyboard.nextLine());
		}
		return tmpWine;
	}

	/**
	 * This method is used from the user to buy new bottles of a specific wine.
	 * @param ws It is the instance of Wine Store
	 * @throws IOException None
	 */
	
	public void buyWine(final WineStoreSystem ws) throws IOException { 
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in); 
		Boolean notify = true;

		if (this.getFlagRegistered()) { // if you are registered you can authenticated log in before buying a wine

			if (this.signIn()) { // you are logged in so you can buy a wine

				Wine tmpWine = this.searchWine(ws.wineList); // it is the wine searched

				if (tmpWine != null) { // if the wine is available (exist in wine store's list)

					System.out.print("\n(USER) -> Insert number of bottles: "); int nbottles = Integer.parseInt(keyboard.nextLine());

					if (nbottles > 0 && nbottles <= tmpWine.getNumBottles()) { // it checks that the desired number of bottles is valid and there are enough bottles into the wine store
						
						// creation of another instance of Wine to store the desired wine and quantities of that wine by the user
						
						Wine ordWine = new Wine(tmpWine.getName(), tmpWine.getProducer(), tmpWine.getYear(), 
								tmpWine.getTechnicalNotes(), tmpWine.getVines(), nbottles);

						order = new Order<User, Wine>(this, ordWine); // the system keeps track of purchases

						ws.storeOrder(order); // the system keeps track of purchases

						ws.decreaseNumBottles(tmpWine, nbottles); // the system manages number of bottles (keep tracks of the number of bottles available)
						
						System.out.println("\n(SYSTEM - USER) -> [" + this.getName() + ", " + this.getEmail() 
							+ "]  your order [" + order.getWine().getName() + ", " + order.getWine().getProducer() 
								+ ", " + order.getWine().getNumBottles() + "] has been succesfully completed.\n");
					}
					
					else if (nbottles > 0 && nbottles > tmpWine.getNumBottles()) { // it checks that the desired number of bottles is valid and there aren't enough bottles into the wine store --> the user can subscribe a notification
						System.out.print("\n(SYSTEM - USER) -> Wine not avalaible; do you want to subscribe a notification? (insert true to subscribe)\n: ");
						
						notify = Boolean.parseBoolean(keyboard.nextLine());

						if (notify) {
							
							// creation of another instance of Wine to store the desired wine and quantities of that wine by the user
							
							Wine ordWine = new Wine(tmpWine.getName(), tmpWine.getProducer(), tmpWine.getYear(), 
									tmpWine.getTechnicalNotes(), tmpWine.getVines(), nbottles);
							
							order = new Order<User, Wine>(this, ordWine);
							
							this.requestNotifyAvailable(ws, order); // it calls the method to notify that there is a new notification
						}
					}
					
					else if (nbottles == 0 || nbottles < 0) System.out.println("\n(SYSTEM - USER) -> You can't order zero bottles or negative values.\n");
				}

				else System.out.println("\n(SYSTEM - USER) -> Wine not available.\n"); // if searchWine return null
			}

			else System.out.println("\n(SYSTEM - USER) -> You didn't sign in.\n"); // if signIn return false
		}

		else System.out.print("\n(SYSTEM - USER) -> You are not registered."); // if this.flagRegistered == false
	}

	/**
	 * This method is used to store into the system the user request for notification
	 * @param ws is the instance of WineStoreSystem
	 * @param ord is the user's order
	 */
	
	private void requestNotifyAvailable(final WineStoreSystem ws, final Order<User, Wine> ord) {		
		ws.notificationList.add(ord);
		
		System.out.println("\n(SYSTEM - USER) -> " + ord.getPerson().getName()
				+ " your request for notification has been succesfully completed.\n");
	}
	
	/**
	 * This method is used to print user's notification list that the system sent him.
	 */
	
	public void printNotifyList() {
		for(String l: this.notifyList) {
			System.out.println(l);
		}
	}

	/**
	 * This method is used to get the value of the flag that indicates if a user is registered
	 * @return It returns the flag's value.
	 */

	public Boolean getFlagRegistered() { return this.flagRegistered; }
	
	/**
	 * This method is used to get user's address.
	 * @return It returns the user's address.
	 */

	public String getAddress() { return this.address; }

	/**
	 * This method is used to set the value of the flag.
	 * @param flagRegistered It is the value of the flag.
	 */

	public void setFlagRegistered(Boolean flagRegistered) { this.flagRegistered = flagRegistered; }

	/**
	 * This method is used to set the user's address.
	 * @param address It is the address.
	 */

	public void setAddress(String address) { this.address = address; }
}
