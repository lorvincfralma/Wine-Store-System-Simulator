package assignement2;

import java.util.List;

/**
 * The class {@code Wine} is used to represents wine entity.
 */

public class Wine {
	
	/**
	 * Class fields.
	 * name - It is the wine's name
	 * producer - It is wine's producer
	 * year - It is wine's year
	 * technicalNotes - They are wine's technicalNotes
	 * vines - It is a list that contains wine's vines
	 * numBottles - It is the number of bottles
	 */
	
	private String name;
	private String producer;
	private String year;
	private String technicalNotes;
	private List<String> vines;
	private int numBottles;
	
	/**
	 * Class constructor.
	 * @param name It is the wines's name.
	 * @param producer It is the wines's producer.
	 * @param year It is the wines's year.
	 * @param technicalNotes They are the wines's technical notes.
	 * @param vines It is a list that contains wines's vines.
	 * @param numBottles It is the number of bottles available.
	 */
	
	public Wine(final String name, final String producer, final String year, final String technicalNotes, final List<String> vines, final int numBottles) {
		this.setName(name);
		this.setProducer(producer);
		this.setYear(year);
		this.setTechnicalNotes(technicalNotes);
		this.setVines(vines);
		this.setNumBottles(numBottles);
	}
	
	/**
	 * This method is used to get the wines's name.
	 * @return It returns the wines's name.
	 */

	public String getName() { return name; }
	
	/**
	 * This method is used to get the wines's producer.
	 * @return It returns the wines's producer.
	 */

	public String getProducer() { return producer; }
	
	/**
	 * This method is used to get the wines's year.
	 * @return It returns the wines's year.
	 */

	public String getYear() { return year; }
	
	/**
	 * This method is used to get the wines's technical notes.
	 * @return It returns the wines's technical notes.
	 */

	public String getTechnicalNotes() { return technicalNotes; }
	
	/**
	 * This method is used to get the list that contains wines's vines.
	 * @return It returns the list that contains wines's vines.
	 */

	public List<String> getVines() { return vines; }
	
	/**
	 * This method is used to get the number of wines's bottles.
	 * @return It returns the number of wines's bottles.
	 */

	public int getNumBottles() { return numBottles;	}
	
	/**
	 * This method is used to set the wines's name.
	 * @param name is the wines's name.
	 */

	public void setName(String name) { this.name = name; }
	
	/**
	 * This method is used to set the wines's producer.
	 * @param producer is the wines's producer.
	 */

	public void setProducer(String producer) { this.producer = producer; }
	
	/**
	 * This method is used to set the wines's year.
	 * @param year is the wines's year.
	 */

	public void setYear(String year) { this.year = year; }
	
	/**
	 * This method is used to set the wines's technical notes.
	 * @param technicalNotes is the wines's technical notes.
	 */

	public void setTechnicalNotes(String technicalNotes) { this.technicalNotes = technicalNotes; }
	
	/**
	 * This method is used to set the wines's vines list.
	 * @param vines is the wines's vines list.
	 */

	public void setVines(List<String> vines) { this.vines = vines; }
	
	/**
	 * This method is used to set the number of wines's bottles.
	 * @param numBottles is the number of wines's bottles.
	 */

	public void setNumBottles(int numBottles) { this.numBottles = numBottles; }
}
