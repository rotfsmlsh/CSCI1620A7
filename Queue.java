/*  Name        : Daniel O'Connell
 *  Class       : 1620-001
 *  Program #   : 7
 *  Due Date    : November 19, 2015
 *  
 *  Honor Pledge:  On my honor as a student of the University
 *                of Nebraska at Omaha, I have neither given nor received
 *                unauthorized help on this homework assignment.
 *
 * NAME: Daniel O'Connell
 * NUID: 12992765
 * EMAIL: danwoc@gmail.com  
 * Partners:  none
 *
 * Description:
 * This program creates a simple class hierarchy using abstract methods and inheritance
 * building upon the Employee program from assignments 1 and 2. This program simulates an 
 * employee management system that might be used by restaurants or similarly structured 
 * organizations. Assignment 7 adds the ability to save and load employees and vacation 
 * requests from a file as well as process update files. 
 */

package dataStructures;

import dataStructures.LinkedList;

public class Queue<E extends Comparable<E>>{
    private LinkedList<E> list;
    
    /*  Method Name   : <<Queue>>  
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Creates a new Linked list using the default constructor.
     */
    public Queue(){
        list = new LinkedList<E>();
    }
    
    /*  Method Name   : <<Queue>> 
     *  Parameters    : Name of the list.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Creates a new Linked List using the passed name.
     */
    public Queue(String name){
        list = new LinkedList<E>(name);
    }
    
    /*  Method Name   : enqueue() 
     *  Parameters    : Item to be added to queue.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Inserts an object to the back of the queue,
     *                  this signifies the newest object added to queue.                  
     */
    public void enqueue(E item){
        list.insertAtBack(item);
    }
    
    /*  Method Name   : dequeue() 
     *  Parameters    : None.
     *  Return Values : Item that was removed from queue.
     *  Partners      : None.
     *  Description   : Removes and returns the oldest object from the queue.
     */
    public E dequeue(){
        return (list.removeFromFront());
    }
    
    /*  Method Name   : lengthIs() 
     *  Parameters    : None.
     *  Return Values : integer length.
     *  Partners      : None.
     *  Description   : returns length of the current queue
     */
    public int lengthIs(){
        return list.lengthIs();
    }
    
    /*  Method Name   : peek() 
     *  Parameters    : None.
     *  Return Values : Generic object at top of queue.
     *  Partners      : None.
     *  Description   : Returns the oldest object in the queue
     */
    public E peek(){
        if(!isEmpty()){
            return list.getItem(0);
        }
        else{
            return null;
        }
    }
    
    /*  Method Name   : toString() 
     *  Parameters    : None.
     *  Return Values : Formatted string.
     *  Partners      : None.
     *  Description   : Returns a string of all objects in the list.
     */
    public String toString(){
        return list.toString();
    }
    
    /*  Method Name   : isEmpty() 
     *  Parameters    : None.
     *  Return Values : boolean.
     *  Partners      : None.
     *  Description   : returns whether or not the list is empty.
     */
    public boolean isEmpty(){
        return (list.isEmpty());
    }
    
    /*  Method Name   : clear() 
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Removes all objects in the list.
     */
    public void clear(){
        list.clear();
    }
}