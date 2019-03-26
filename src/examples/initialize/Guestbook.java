/*
 * To get more coverage, place the following code in the setUp() method of
 * PackageTestCase in the same package as the generated test class, then
 * run the "Generate and Run Unit Tests" config.
 * 
       Guestbook book = new Guestbook();
       book.dataInitialize();
       jtest.Repository.putTemporary("book1", book);
 */


package examples.initialize;


import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


/**
 * Represents a list of names of visiting guests.
 */
public class Guestbook {
	
	/**
	 * Set to keep track of guest names.
	 */
	private Set _guests;
	
	/**
	 * Need to initialize a newly created Guestbook by calling dataInitialize().
	 */
	public Guestbook() {
	}
	
	/**
	 * Initialize a guestbook.
	 */
	public void dataInitialize() {
		_guests = new TreeSet();
	}
	
	/**
	 * Register a new guest by name.
	 * @param name the name of the guest
	 */
	public void registerGuest(String name) {
		if (_guests != null) {
			_guests.add(name);
		}
	}
	
	/**
	 * Get the number of guests who have registed.
	 * @return the number of registered guests
	 */
	public int countGuests() {
		if (_guests != null) {
			int result = _guests.size();
			return result;
		}
		return -1;
	}
	
	/**
	 * Get the names of all registered guests as a String array.
	 * @return an array of guest names
	 */
	public String [] getGuestNames() {
		if (_guests != null) {
			String [] result = new String[_guests.size()];
			result = (String [])_guests.toArray(result);
			return result;
		}
		return null;
	}
	
	/**
	 * Print a list of registered guests.
     * @return a text string list of registered guests
	 */
	public String display() {
		if (_guests != null) {
			StringBuffer result = new StringBuffer();
			Iterator iterator = _guests.iterator();
			while (iterator.hasNext()) {
				result.append(iterator.next());
				result.append(' ');
			}
			return result.toString();
		}
		return null;
	}
}