/*
 * Created on Sep 10, 2003
 */

package examples.stackmachine;

import javax.swing.DefaultListModel;

/** Implementes basic stack functionality extending
 * <code>DefaultListModel</code>.
 */

public abstract class AbstractStackMachine extends DefaultListModel 
                                    implements StackInterface {
    /**
     * Retrive two elements from the stack and put back the add of them.<br>
     * @return the result of the operation<br>
     * <code><i>Pre comment:</i></code> 'add()' can only be called if 
     * there are enough elements.<br>
     * <code><i>Post comment:</i></code> top value in the stack should be 
     * the sum of the top two.
     */
    
    /* 
     * change this to javadoc comment to use dbc 
     * @pre size () >= 2
     * @post at (0) == $pre (int, at (0)) + $pre (int, at (1)) 
     */
    public int add () {
        int op1 = popInt ();
        int op2 = popInt ();
        int result = op1 + op2;

        pushInt (result);
        return result;
    }

    /**
     * Retrive two elements from the stack and put back the add of them.<br>
     * @return the result of the operation<br>
     * <code><i>Pre comment:</i></code> 'subtract()' can only be called 
     * if there are enough elements.<br>
     * <code><i>Post comment:</i></code> top value in the stack should be 
     * the subtraction of the top two.
     */
    /*
     * change this to javadoc comment to use dbc
     * @pre size () >= 2
     * @post at (0) == $pre (int, at (0)) - $pre (int, at (1)) 
     */
    public int subtract () {
        int op1 = popInt ();
        int op2 = popInt ();
        int result = op1 + op2;  // BUG: should be '-'

        pushInt (result);
        return result;
    }

    /**
     * Retrive two elements from the stack and put back the add of them.<br>
     * @return the result of the operation<br>
     * <code><i>Pre comment:</i></code> 'multiply()' can only be called     
     * if there are enough elements.<br>
     * <code><i>Post comment:</i></code> top value in the stack should be 
     * the multiplication of the top two.
     */
    /*
     * change this to javadoc comment to use dbc
     * @pre size () >= 2
     * @post at (0) == $pre (int, at (0)) * $pre (int, at (1)) 
     * @post $pre (int, at (0)) * $pre (int, at (1)) > 0
     */
    public int multiply () {
        int op1 = popInt ();
        int op2 = popInt ();
        int result = op1 * op2;

        // bogus assert: uncomment to try:
        //** @assert (result == op1 + op2) */
        pushInt (result);
        return result;
    }

    /**
     * Retrive two elements from the stack and put back the add of them.<br>
     * @return the result of the operation<br>
     * <code><i>Pre comment:</i></code> 'divide()' can only be called 
     * if there are enough elements.<br>
     * <code><i>Post comment:</i></code> top value in the stack should be 
     * the multiplication of the top two.
      
     */
    /*
     * @pre size () >= 2  
     * change this to javadoc comment to use dbc
     * @post at (0) == $pre (int, at (0)) / $pre (int, at (1)) 
     * @post $pre (int, at (0)) / $pre (int, at (1)) != 0 
     */
    public int divide () {
        int op1 = popInt ();
        int op2 = popInt ();
        int result = op1 / op2;

        pushInt (result);
        return result;
    }

    /** 
     * Retrive an element from the stack by index.
     * @param index of element to retrieve.
     * @return an element of the stack.
     */
    protected int at (int index) {
        return Integer.parseInt ((String) elementAt (index));
    }
    
    /** 
     * Check the input string.
     * @param string input string.
     * @return true is a string is a value, false - otherwise. 
     */
    protected boolean isInteger (String string) {
        try {
            Integer.parseInt (string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /** 
     * Retriev an element from the stack.<br> 
     * Derived class implement the schema of retrieving (lifo or fifo). 
     */
    abstract protected int popInt ();
 
    /** 
     * Add an element to the end of the stack.
     * @param value of the element to push in. 
     */
    abstract protected void pushInt (int value);
}