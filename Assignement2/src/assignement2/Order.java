package assignement2;

/**
 * The class {@code Order} is used to represents order entity.
 * It is composed of |User, Wine| pair
 */

public class Order<P, W> {
	
	/**
	 * Class fields.
	 * P - It is the person who did the order.
	 * W - It is the wine ordered by that person.
	 */
	
	private P person;
	private W wine; 
	private boolean delivered; // true = delivered, false = not delivered
	
	/**
	 * Class constructor.
	 * @param p It is the person who did the order.
	 * @param w It is the wine to order.
	 */
	
	public Order(final P p, final W w) {
		setPerson(p);
		setWine(w);
		this.delivered = false; // the value of delivered is changed when the employee delivers the order
	}
	
	/**
	 * Method used to get a generic instance. In this case to get the instance of People.
	 * @return It returns a generic instance. In this case it returns an instance of People.
	 */
	
	public P getPerson() { return this.person; }
	
	/**
	 * Method used to get a generic instance. In this case to get the instance of Wine.
	 * @return It returns a generic instance. In this case it returns an instance of Wine.
	 */
	
	public W getWine() { return this.wine; }
	
	/**
	 * Method used to get delivered flag.
	 * @return It returns the value of the delivered flag. (False : not delivered - True: delivered)
	 */
	
	public boolean getDelivered() { return this.delivered; }
	
	/**
	 * Method used to set person.
	 * @param p It is a generic instance. In this case it will be an instance of People.
	 */
		
	public void setPerson(final P p){ this.person = p; }
	
	/**
	 * Method used to set wine.
	 * @param w It is a generic instance. In this case it is an instance of Wine.
	 */
	
	public void setWine(final W w){ this.wine = w; }	
	
	/**
	 * Method used to set the value of the delivered flag.
	 * @param h It is the value of the flag.
	 */
	
	public void setDelivered(final boolean h) {	this.delivered = h; }
}
