package assignement2;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The class {@code WineStoreSystem} in used to contain the main method to 
 * execute the simulation and it contains system data and methods 
 * to manage system data.
 * @author Vincenzo Fraello (299647) - Lorenzo Di Palma (299636).
 * @version 1.0
 */

public class WineStoreSystem {
	
	/**
	 * Class fields.
	 * name - It is the name of the Wine Store
	 * wineList - It is a list that contains all wine store's wines.
	 * orderList - It is a list that contains all wine store's orders (delivered and not delivered).
	 * notificationList - It is a list that contains all users that subscribed a notification for a particular wine.
	 */
	
	private String name;	
	protected List<Wine> wineList = new LinkedList<>();	// list that contains all wine store's wines
	protected List<Order<User, Wine>> orderList = new LinkedList<>(); // list that contains delivered / undelivered wines
	protected List<Order<User, Wine>> notificationList = new ArrayList<>(); // list of users that await a notification
	
	/**
	 * Class constructor.
	 * @param name It is the wine store's name.
	 */
	
	public WineStoreSystem(final String name) {	this.setName(name);	}
	
	/**
	 * This method is used by the system to add an order |User, Wine| into the wine store system.
	 * The list contains both delivered/undelivered order.
	 * @param ord It is the order to store.
	 */
		
	public void storeOrder(final Order<User, Wine> ord) { this.orderList.add(ord); }
	
	/**
	 * This method is used by the system to update the number of bottles of a specific wine 
	 * after a user's purchase.
	 * @param w It is the wine 
	 * @param nb It is the number of bottles bought by user
	 */
	
	public void decreaseNumBottles(final Wine w, final int nb) {
		w.setNumBottles(w.getNumBottles() - nb); // update system-data
		if (w.getNumBottles() == 0) this.reportZeroBottles(w); // it checks if user has taken all available bottles of a specific wine
	}
	
	/**
	 * This method is used to update system data about the quantities of a specific Wine
	 * after employee purchase from supplier
	 * @param w It is the wine to update
	 * @param nb It is the number of bottles bought by employee
	 * 
	 * {@link Employee#buyFinischedBottles(WineStoreSystem, Wine) }
	 * {@link Employee#buyNewBottles(WineStoreSystem) }
	 */
	

	public void updateSystemData(final Wine w, final int nb) { 
		
		for(var v : this.wineList) {
			if(v.getName().equalsIgnoreCase(w.getName()) && v.getYear().equals(w.getYear()) 
					&&  v.getProducer().equalsIgnoreCase(w.getProducer()) && nb > 0) { // it check that the wine is in the list and that the number of bottles is valid

				v.setNumBottles(nb + v.getNumBottles()); 
			}
		}
	} 
	
	/**
	 * This method is used by the system to report to employee that the bottles of a specific wine 
	 * have run out.
	 * @param w It is the wine that is finished
	 * 
	 * {@link Employee#buyFinischedBottles(WineStoreSystem, Wine) }
	 */
	
	public void reportZeroBottles(final Wine w) {
		System.out.println("\n(SYSTEM - EMPLOYEE) -> Attention: [" + w.getName() + ", " 
				+ w.getYear() + "] out of stock.");	//the system notifies the employee	
		Employee.buyFinischedBottles(this, w); // employee have to buy an adequate number of bottles
	}	
	
	/**
	 * This method is used by the system to notify the availability of a wine to the user.
	 * It is called by automatically when an employee buy new bottles of wine to satisfy 
	 * the user's requirements.
	 */

	public void reportWineAvailable() { 
		boolean wineFound = false; // this flag is used to verify that the user has been deleted from the list
		
		if(!this.notificationList.isEmpty()) { // if the list of users who have subscribed to a notification is not empty, then try to satisfy their requests		
			for(int i = 0; i < this.notificationList.size(); ) {
				
				for(Wine v: this.wineList) {
					
					Wine w = this.notificationList.get(i).getWine(); // subscribed wine
					User u = this.notificationList.get(i).getPerson(); // user who subscribe notification
									
					if(w.getName().equalsIgnoreCase(v.getName()) && w.getYear().equals(v.getYear()) 
									&& w.getProducer().equalsIgnoreCase(v.getProducer()) 
										&& w.getNumBottles() <= (v.getNumBottles())) { // It checks that the wine the user wants is available and that the number of bottles the user wants is available
						
						wineFound = true;
						
						String note = "\n(SYSTEM - USER) -> Dear "+ u.getName() + ": the wine: [" 
								+ w.getName() + ", " + w.getYear() + ", " + w.getProducer() + "] is now availaible.\n";
						
						System.out.println(note);
						
						u.notifyList.add(note); // each user has his own list of notifications
						
						this.notificationList.remove(i); // when notification has been sent then the user is removed from the list of user that await a notification
						break; // user has been found so it is unnecessary to do others checks
					}
					
					else wineFound = false; // This instruction is necessary because if the first user respects the controls then the flag is set to true. When the next user does not respect the controls, the variable would still be true and therefore the index would not be increased and the next user would not be passed
					// Quetsa istruzione è necessaria poichè se il primo user rispetta i controlli allora il flag viene settato a true. Quando l'utente successivo non rispetta i controlli la variabile risulterebbe comunque a true e quindi non verrebbe incrementato l'indice e non si passerebbe all'eventuale user successivo 
				}
				
				if(!wineFound) { // these instruction are only executed the previous user is not removed from the list
					i++; // increment is necessary to go to the next user
					wineFound = false;
				}
			}
		}
	}
		
	/**
	 * This method is used to get the wine store's name.
	 * @return The name of the wine store.
	 */

	public String getName() { return name; }
	
	/**
	 * This method is used to set the name of the wine store. 
	 * @param name It is the name of the wine store.
	 */

	public void setName(String name) { this.name = name; }
	
	/*
	 * This method is used to clear file after a simulation
	 */
	
	protected void clearFile() {
		FileWriter fwOb = null;
		try {
			fwOb = new FileWriter("login.txt", false);
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try {
			fwOb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to execute the simulation.
	 * @param args None
	 * @throws IOException None
	 */

	public static void main(String[] args) throws IOException {

		System.out.println("----------------------------------------------------------------");
		System.out.println("|                   	    SIMULATION                         |");
		System.out.println("----------------------------------------------------------------\n");
		
		System.out.println("Authors: Vincenzo Fraello (299647) - Lorenzo Di Palma (299636)\n");
		
		WineStoreSystem ws = new WineStoreSystem("LorVincFraLma");
		
		System.out.println("\t\tWine Store name: " + ws.getName());
		
		// N.B: USARE COME DATI DELLA SIMULAZIONE QUELLI CHE VENGONO STAMPATI SOPRA IL LOGIN
		
			// TEST 1
		
		System.out.println("\n1) il sistema viene inizializzato con alcuni utenti un impiegato e dei vini\n");
		
		List<String> lVines = new LinkedList<>(); lVines.add("Mascalese"); lVines.add("Trebbiano"); lVines.add("Sangiovese");
		
		//Employee emp = new Employee("Paolo", "Bianchi", "paolo.bianchi@outlook.com", "56789");
		
		Wine w1 = new Wine("Tavernello", "Famiglia Bianchi", "2020", "Table wine produced by the Caviro company from Romagna, headquartered in Faenza.", lVines, 8); // UX
		Wine w2 = new Wine("Merlot", "Famiglia Verdi", "1970", "Very early wine, a characteristic that allows it to adapt easily to the climates of the whole wine world.", lVines, 2); // UY		
		Wine w3 = new Wine("Montepulciano", "Famiglia Gialli", "2018", "DOC wine whose production is allowed in the provinces of Chieti, L'Aquila, Pescara and Teramo.", lVines, 18);
		
		ws.wineList.add(w1);
		ws.wineList.add(w2);
		ws.wineList.add(w3);
				
		User usr1 = new User(); // UX
		User usr2 = new User(); // UY
		User usr3 = new User();	// UZ
		
		System.out.println("\tEmployee (\"Paolo\", \"Bianchi\", \"paolo.bianchi@outlook.com\", \"56789\")\n");
		
		System.out.println("\tUser UX (\"Vincenzo\", \"Fraello\", \"vincenzo.fraello@gmail.com\", \"299647\")\n");
		System.out.println("\tUser UY (\"Lorenzo\", \"Di Palma\", \"lorenzo.dipalma@gmail.com\", \"299636\")UY\n");
		System.out.println("\tUser UZ (\"Mario\", \"Rossi\", \"mario.rossi@gmail.com\", \"12345\")\n");
		
		System.out.println("\tWine UX (\"Tavernello\", \"Famiglia Bianchi\", \"2020\", \"Table wine produced by the Caviro company from Romagna, headquartered in Faenza.\", [ Mascalese Trebbiano Sangiovese ], 8) -> UX\n");
		System.out.println("\tWine UY (\"Merlot\", \"Famiglia Verdi\", \"1970\", \"Very early wine, a characteristic that allows it to adapt easily to the climates of the whole wine world.\", [ Mascalese Trebbiano Sangiovese ], 2) -> UY\n");
		
		System.out.println("\n2) un utente UX si registra e fa l’acquisto di alcune bottiglie di un certo vino UX\n");
		
			// TEST 2
		
		usr1.signUp("Vincenzo", "Fraello", "vincenzo.fraello@gmail.com", "299647", "Via P.P 5"); // UX
		
		usr1.buyWine(ws);
		
			// TEST 2
		
		System.out.println("\n3) un utente UY si registra e fa l’acquisto di tutte le bottiglie di un certo vino UY\n");
		
		usr2.signUp("Lorenzo", "Di Palma", "lorenzo.dipalma@gmail.com", "299636", "Piazzale S.C 6"); // UY
		
		usr2.buyWine(ws);
		
			// TEST 3
		
		System.out.println("\n4) un utente UZ si registra e vuole fare l’acquisto di alcune bottiglie del vino UY "
				+ "non più disponibile e chiede di ricevere una notifica quando il vino UY sarà di nuovo disponibile\n");
		
		
		usr3.signUp("Mario", "Rossi", "mario.rossi@gmail.com", "12345", "Via P.G.C 28"); // UZ
		
		usr3.buyWine(ws);
		
		
			// TEST 4
		
		System.out.println("\n5) l’impiegato aggiunge un certo numero di bottiglie del vino UY e il sistema notifica "
				+ "l’utente UZ della nuova disponibilità del vino\n");
		
		
		//emp.buyNewBottles(ws);
		
		Employee.buyNewBottles(ws);
		
		// N.B: svuotiamo il file contenente gli utenti registrati per evitare di scrivere gli stessi utenti ogni 
			// volta che la simulazione termina
		
		for(var h : ws.wineList) {
			System.out.println();
			System.out.println(h.getName() + ", " + h.getNumBottles());
		}
		
		ws.clearFile();		
	}
}
