/*
 * JTest will use the "getInstance" method of this class to test
 * the other instance methods.
 */

package examples.singletons;


import java.util.HashSet;
import java.util.Set;


/**
 * Represents a dispensor and selector of raffle tickets.
 */
public class RaffleTicketPicker {
	
	/**
	 * Static field to track the single instance.
	 */
	private static RaffleTicketPicker _instance = null;
	
	/**
	 * Int to keep track of next ticket id number.
	 */
	private int _count = 1;
	
	/**
	 * Set to keep track of winning tickets.
	 */
	private Set _drawn_tickets = new HashSet();
	
	/**
	 * Use getInstance() so there will only be one instance of RaffleTicketPicker.
	 */
	private RaffleTicketPicker() {
	}
	
	/**
	 * Get the one and only istance of RaffleTicketPicker.
	 * @return A RaffleTicketPicker.
	 */
	public static RaffleTicketPicker getInstance() {
		if (_instance == null) {
			_instance = new RaffleTicketPicker();
		}
		return _instance;
	}
	
	/**
	 * Dispense a new raffle ticket.
	 * @return the numerical id of the ticket
	 */
	public int dispenseTicket() {
		int next_id = _count++;
		_drawn_tickets.add(new Integer(next_id));
		return next_id;
	}
	
	/**
	 * Select a winning ticket.
	 * @return the numerical id of the winning ticket
	 */
	public int selectWinner() {
		Integer winner = (Integer)_drawn_tickets.iterator().next();
		_drawn_tickets.remove(winner);
		return winner.intValue();
	}
}