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

public class ListNode<E extends Comparable<E>>{
    private E data;
    private ListNode<E> nextNode;
    
    /*  Method Name   : <<ListNode>>
     *  Parameters    : generic data d.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : constructor that creates a new node. 
     */
    public ListNode(E d){
        data = d;
        nextNode = null;
    }
    
    /*  Method Name   : <<ListNode>>
     *  Parameters    : generic data d and a link to the next node.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : constructor that creates a new node and sets a next node. 
     */
    public ListNode(E d, ListNode<E> node){
        data = d;
        nextNode = node;
    }
    
    /*  Method Name   : setData
     *  Parameters    : generic data d.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : assigns data to a node. 
     */
    public void setData(E d){
        data = d;
    }
    
    /*  Method Name   : getData
     *  Parameters    : None.
     *  Return Values : Generic Data.
     *  Partners      : None.
     *  Description   : returns the value of the node. 
     */
    public E getData(){
        return data;
    }
    
    /*  Method Name   : setNext
     *  Parameters    : node to set as next.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : gives an existing node a next node. 
     */
    public void setNext(ListNode<E> next){
        nextNode = next;
    }
    
    /*  Method Name   : getNext
     *  Parameters    : None.
     *  Return Values : the next node.
     *  Partners      : None.
     *  Description   : returns the next node in the list. 
     */
    public ListNode<E> getNext(){
        return nextNode;
    }
}
