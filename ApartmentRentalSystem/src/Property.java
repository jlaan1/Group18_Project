
/**
 * Class that represents a property with the relevant attributes
 * 
 * @author Jialei
 */
public class Property {

	private String address;
	private String propertyType;
	private int zipCode;
	private int numBedrooms;
	private int currentStatus;
	private int monthlyRate;
	private int landlordID;
	private int clientID;

	public Property(String address, String type, int zipCode, int numBR, int status, int rate, int cID, int lID) {

		setAddress(address);
		setPropertyType(type);
		setZip(zipCode);
		setNumBR(numBR);
		setStatus(status);
		setRate(rate);
		setLandlordID(lID);
		setClientID(cID);

		// Add property to SQLite database
		PropertyDB_Messenger.insert(address, type, zipCode, numBR, status, rate, cID, lID);

	}

	/**
	 * Method to set address of property
	 * 
	 * @param addressFull full address of property
	 */
	public void setAddress(String addressFull) {
		address = addressFull;
	}

	/**
	 * Method to obtain the address of the property
	 * 
	 * @return full address of property
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method to set type of property (e.g. apartment or house)
	 * 
	 * @param type type of property
	 */
	public void setPropertyType(String type) {
		propertyType = type;
	}

	/**
	 * Method to obtain the type of property currently inspected
	 * 
	 * @return type of property
	 */
	public String getPropertyType() {
		return propertyType;
	}

	/**
	 * Method to set the Zip code of current property
	 * 
	 * @param zip Zip code
	 */
	public void setZip(int zip) {
		zipCode = zip;
	}

	/**
	 * Method to obtain the Zip code of current property
	 * 
	 * @return Zip code of property
	 */
	public int getZip() {
		return zipCode;
	}

	/**
	 * Method to set number of bedrooms that property has
	 * 
	 * @param numBR number of bedrooms
	 */
	public void setNumBR(int numBR) {
		numBedrooms = numBR;
	}

	/**
	 * Method to obtain number of bedrooms that property has
	 * 
	 * @return number of bedrooms
	 */
	public int getNumBR() {
		return numBedrooms;
	}

	/**
	 * Method to set current status of property
	 * 
	 * @param status current status
	 */
	public void setStatus(int status) {
		currentStatus = status;
	}

	/**
	 * Method to obtain current status of property
	 * 
	 * @return 0 if currently empty; 1 if occupied
	 */
	public int getStatus() {
		return currentStatus;
	}

	/**
	 * Method to set monthly rate of property
	 * 
	 * @param rate monthly rate of property
	 */
	public void setRate(int rate) {
		monthlyRate = rate;
	}

	/**
	 * Method to obtain the monthly rate of property
	 * 
	 * @return monthly rate of property
	 */
	public int getRate() {
		return monthlyRate;
	}

	/**
	 * Method to assign property to landlord
	 * 
	 * @param id landlord's unique id
	 */
	public void setLandlordID(int id) {
		landlordID = id;
	}

	/**
	 * Method to obtain ID of landlord owning the property
	 * 
	 * @return landlord's ID
	 */
	public int getLandlordID() {
		return landlordID;
	}

	/**
	 * Method to assign property to client
	 * 
	 * @param id client's unique id
	 */
	public void setClientID(int id) {
		clientID = id;
	}

	/**
	 * Method to obtain ID of client currently residing in property
	 * 
	 * @return client's ID
	 */
	public int getClientID() {
		return clientID;
	}
}
