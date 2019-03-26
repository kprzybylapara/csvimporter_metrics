/*
 * To get more coverage, place the following code in the setUp() method of
 * PackageTestCase in the same package as the generated test class, then
 * run the "Generate and Run Unit Tests" config.
 * 
       RaffleTicketPicker rtp = new RaffleTicketPicker();
       rtp.dataInitialize();
       jtest.Repository.putTemporary("rtp1", rtp);
 */

package examples.initialize;


import java.util.HashSet;
import java.util.Set;


/**
 * Represents a dispensor and selector of raffle tickets.
 */
public class RaffleTicketPicker {
	
	/**
	 * Int to keep track of next ticket id number.
	 */
	private int _count = 1;
	
	/**
	 * Set to keep track of winning tickets.
	 */
	private Set _drawn_tickets;
	
	/**
	 * Need to initialize a newly created instance by calling dataInitialize().
	 */
	public RaffleTicketPicker() {
	}
	
	/**
	 * Initialize a raffle ticket picker.
	 */
	public void dataInitialize() {
		_drawn_tickets = new HashSet();
	}
	
	/**
	 * Dispense a new raffle ticket.
	 * @return the numerical id of the ticket
	 */
	public int dispenseTicket() {
		if (_drawn_tickets != null) {
			int next_id = _count++;
			_drawn_tickets.add(new Integer(next_id));
			return next_id;
		}
		return -1;
	}
	
	/**
	 * Select a winning ticket.
	 * @return the numerical id of the winning ticket
	 */
	public int selectWinner () {
		if (_drawn_tickets != null) {
			Integer winner = (Integer)_drawn_tickets.iterator().next();
			_drawn_tickets.remove(winner);
			return winner.intValue();
		}
		return -1;
	}
}