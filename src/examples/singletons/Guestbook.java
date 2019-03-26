/*
 * JTest will use the "getInstance" method of this class to test
 * the other instance methods.
 */

package examples.singletons;


import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


/**
 * Represents a list of names of visiting guests.
 */
public class Guestbook {
	
	/**
	 * Static field to track the single instance.
	 */
	private static Guestbook _instance = null;
	
	/**
	 * Set to keep track of guest names.
	 */
	private Set _guests = new TreeSet();
	
	/**
	 * Use getInstance() so there will only be one instance of Guestbook.
	 */
	private Guestbook() {
	}
	
	/**
	 * Get the one and only istance of Guestbook.
	 * @return A Guestbook.
	 */
	public static Guestbook getInstance() {
		if (_instance == null) {
			_instance = new Guestbook();
		}
		return _instance;
	}
	
	/**
	 * Register a new guest by name.
	 * @param name The name of the guest.
	 */
	public void registerGuest(String name) {
		_guests.add(name);
	}
	
	/**
	 * Get the number of guests who have registed.
	 * @return The number of registered guests.
	 */
	public int countGuests() {
		return _guests.size();
	}
	
	/**
	 * Get the names of all registered guests as a String array.
	 * @return An array of guest names.
	 */
	public String [] getGuestNames() {
		return (String [])_guests.toArray(new String [_guests.size()]);
	}
	
	/**
	 * Print a list of registered guests.
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		Iterator iterator = _guests.iterator();
		while (iterator.hasNext()) {
			result.append(iterator.next ());
			result.append(' ');
		}
		return result.toString();
	}
}