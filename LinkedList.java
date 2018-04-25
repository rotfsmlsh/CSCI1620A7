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
import exceptions.EmptyListException;

public class LinkedList<E extends Comparable<E>>{
    private ListNode<E> firstNode;
    private ListNode<E> lastNode;
    private int numElements;
    private String name;
    
    /*  Method Name   : <<LinkedList>>
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : creates an empty linked list with no name. 
     */
    public LinkedList(){
        firstNode = null;
        lastNode = null;
        numElements = 0;
    }
    
    /*  Method Name   : <<LinkedList>>
     *  Parameters    : String name.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : creates an empty linked list with a name. 
     */
    public LinkedList(String n){
        firstNode = null;
        lastNode = null;
        numElements = 0;
        name = n;
    }
    
    /*  Method Name   : insertAtFront
     *  Parameters    : a generic item.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : adds a new node to the beginning of the linked list. 
     */
    public void insertAtFront(E item){
        ListNode<E> newNode = new ListNode<E>(item);
        
        if (isEmpty()){           
            firstNode = newNode;
            lastNode = newNode;
            numElements++;
        }
        else{
            newNode.setNext(firstNode);
            firstNode = newNode;
            numElements++;
        }
    }
    
    /*  Method Name   : insertAtBack
     *  Parameters    : a generic item.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : adds a new node to the end of the linked list. 
     */
    public void insertAtBack(E item){
        ListNode<E> newNode = new ListNode<E>(item);
        
        if (isEmpty()){
            firstNode = newNode;
            lastNode = newNode;
            numElements++;
        }
        else{
            lastNode.setNext(newNode);
            lastNode = newNode;
            numElements++;
        }
    }
    
    /*  Method Name   : removeFromFront
     *  Parameters    : None.
     *  Return Values : the item removed.
     *  Partners      : None.
     *  Description   : removes a node from the beginning of the list. 
     */
    public E removeFromFront() throws EmptyListException{
        ListNode<E> temp = null;
        if (isEmpty()){
            throw new EmptyListException(name);
        }
        if (firstNode == lastNode){
            temp = firstNode;
            firstNode = null;
            lastNode = null;
            numElements--;
            return temp.getData();
        }
        else{
            temp = firstNode;
            firstNode = firstNode.getNext();
            numElements--;
            return temp.getData();
        }
    }
    
    /*  Method Name   : removeFromBack
     *  Parameters    : None.
     *  Return Values : the item removed.
     *  Partners      : None.
     *  Description   : removes a node from the end of the list. 
     */
    public E removeFromBack() throws EmptyListException{
        ListNode<E> temp = firstNode;
        ListNode<E> toReturn = null;
        if (isEmpty()){
            throw new EmptyListException(name);
        }
        if (firstNode == lastNode){
            toReturn = firstNode;
            firstNode = null;
            lastNode = null;
            --numElements;
        }
        else{
            while(temp.getNext().getNext() != null){
                temp = temp.getNext();
            }
            
            temp.setNext(null);
            lastNode = temp;
            toReturn = temp;
            --numElements;
        }
        return toReturn.getData();
    }
    
    /*  Method Name   : removeItem
     *  Parameters    : index of the item in the list
     *  Return Values : the item removed.
     *  Partners      : None.
     *  Description   : removes a node from the given index of the list. 
     */
    public E removeItem(int index) throws IndexOutOfBoundsException{
        ListNode<E> temp = firstNode;
        ListNode<E> toReturn = null;
        
        if (numElements == 0){
            throw new EmptyListException(name);
        }
        
        if (index < 0 || index > lengthIs() - 1){
            throw new IndexOutOfBoundsException(name + " Index out of range");
        }
        
        if (index == 0){
            return removeFromFront();
        }
        
        if (index == numElements - 1){
            return removeFromBack();
        }
        
        else{
            
            for (int i = 0; i < index - 1; i++){
                temp = temp.getNext();
            }
            toReturn = temp.getNext();
            temp.setNext(temp.getNext().getNext());
            numElements--;
        }
        return toReturn.getData();
    }
    
    /*  Method Name   : getItem
     *  Parameters    : index of item in list.
     *  Return Values : the item requested.
     *  Partners      : None.
     *  Description   : returns the item at the given index in the list. 
     */
    public E getItem(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index > numElements - 1){       
            throw new IndexOutOfBoundsException(name + " Index out of range");
        }
        
        if (isEmpty()){
            throw new EmptyListException(name);
        }
        
        ListNode<E> temp = firstNode;
        int testindex = 0;
            
        while (temp != null && testindex < index){
            temp = temp.getNext();
            testindex++;
        }
        
        return temp.getData();
    }
    
    /*  Method Name   : setItem
     *  Parameters    : index of the item in the list and the item to put there.
     *  Return Values : None.
     *  Partners      : Tutors.
     *  Description   : changes the value of an item at the given index in the list. 
     */
    public void setItem(int index, E item) throws IndexOutOfBoundsException{
        ListNode<E> temp = firstNode;
        if(index > numElements - 1|| index < 0){
            throw new IndexOutOfBoundsException(name + " Index out of Range");
        }
        else{
            for(int i = 0; i < index; i++){
                temp = temp.getNext();
            }                
            temp.setData(item);
        }
    }
    
    /*  Method Name   : findAndRemove
     *  Parameters    : item to be removed.
     *  Return Values : boolean if the item was removed.
     *  Partners      : Tutors.
     *  Description   : tries to remove an item at the given place in the list. 
     */
    public boolean findAndRemove(E item){
        if (isEmpty()){
            throw new EmptyListException(name);
        }
        if (findItem(item) == -1){
            return false;
        }
        else{
            removeItem(findItem(item));
            return true;
        }
    }
    
    /*  Method Name   : findItem
     *  Parameters    : a generic item to be found.
     *  Return Values : the index of the item found.
     *  Partners      : None.
     *  Description   : searches the list to find the passed item. 
     */
    public int findItem(E item){
        boolean flag = false;
        int toReturn = -1;

        if (firstNode.getData() == item){
            flag = true;
            toReturn = 0;
        }
        if (lastNode.getData() == item){
            flag = true;
            toReturn = numElements - 1;
        }
        if(!flag){
            for (int j =0; j < numElements - 1; j++){
                if (getItem(j) == item){
                    toReturn = j;
                    break;
                }
            }
        }
        return toReturn;
    }
    
    /*  Method Name   : lengthIs
     *  Parameters    : None.
     *  Return Values : number of elements in list.
     *  Partners      : None.
     *  Description   : returns the length of the list. 
     */
    public int lengthIs(){
        return numElements;
    }
    
    /*  Method Name   : clear
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Clears the entire list. 
     */
    public void clear(){
        numElements = 0;
        firstNode = null;
        lastNode = null;
    }
    
    /*  Method Name   : toString
     *  Parameters    : None.
     *  Return Values : formatted string.
     *  Partners      : None.
     *  Description   : Returns a formatted string of all elements in the list. 
     */
    public String toString(){
        String toReturn = "";

        if(!isEmpty()){
            ListNode<E> item = firstNode;
            toReturn += item.getData();
            
            while(item.getNext() != null){
                item = item.getNext();
                toReturn += "\n\n" + item.getData();
            }
        }
        return toReturn;
    }
    
    /*  Method Name   : isEmpty
     *  Parameters    : None.
     *  Return Values : boolean if list is or isnt empty.
     *  Partners      : None.
     *  Description   : checks to see if list is empty, returns true or false. 
     */
    public boolean isEmpty(){
        boolean flag;
        if(firstNode == null && lastNode == null){
            flag = true;
        }
        else{
            flag = false;
        }
        return flag;
    }
    
    /*  Method Name   : sort
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies a selection sort to the entire list. 
     */
    public void sort() throws EmptyListException{
        if(isEmpty()){
            throw new EmptyListException(name);
        }
        else{
            int lowest;
            ListNode<E> temp = firstNode;
            ListNode<E> temp2 = temp.getNext();
            E smallest;
            
            for (int i = 0; i < numElements - 1; i++){
                lowest = i;
                smallest = temp.getData();
                for(int j = i+1; j < numElements; j++){
                    if(temp2.getData().compareTo(smallest) < 0){
                        lowest = j;
                        smallest = temp2.getData();
                    }
                    temp2 = temp2.getNext();
                }
                E toSwap = temp.getData();
                setItem(i,smallest);
                setItem(lowest,toSwap);
                temp = temp.getNext();
                temp2 = temp.getNext();
            }
        }
    }
}
