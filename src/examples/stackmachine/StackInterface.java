/*
 * RunnableStackMachine.java uses this class as the model for the stack list. 
 * RunnableStackMachine should make sure that it calls the stack implementations
 * according to the precondition contracts defined in the interface.<br>
 */

package examples.stackmachine;

/**
 * The Design by Contract comments in this class provide the following 
 * advantages:<br>
 * <ul>
 * <li> <b>1) @pre</b> contracts check that the class is used correctly.
 * For example if 'push ()' is called with a non-integer argument a 
 * PreViolation will be generated. Note that the class assumes that
 * the values passes to push are valid arguments, the correct workings
 * of the class after that assume that push were passed valid integers.
 * To see this violation, push a non-integer value into the stack (i.e. "abc").
 * <li> <b>2) @post</b> contracts check that StackMachine works according to 
 * specificaion.
 */
public interface StackInterface {
    /** 
     * Add an element to the stack.<br>
     * <code><i>Pre comment:</i></code> the String pushed should 
     * represent an integer.
     * @param value to add to the stack.
     */
    
	/*
	 * Change this to a javadoc comment to use the dbc comments
	 * @pre isInteger (value) 
	 * @post $none
	 */ 
    
    public void push (String value);

    /**
     * Retrieve an element from the stack.<br> 
     * <code><i>Pre comment:</i></code> 'pop()' can only be called if 
     * the stack is not empty.
     */
    
	/*
	 * Change this to a javadoc comment to use the dbc comments
	 * @pre size () > 0 
	 * @post $none 
	 */
    
    public void pop ();
}