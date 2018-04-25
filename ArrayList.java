/*  Name        : Daniel O'Connell
 *  Class       : 1620-001
 *  Program #   : 8
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
 * organizations. Assignment 8 compiles all assignments into a GUI.
 */

package dataStructures;
import exceptions.InvalidSizeException;
import exceptions.MaximumCapacityException;

@SuppressWarnings("unchecked")
public class ArrayList<E extends Comparable<E>>{
    private final int defCap = 50;
    private int origCap;
    private int numElements = 0;
    private Object[] list;
    
    /*  Method Name   : <<ArrayList()>>
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : Constructor sets the original cap of the array to the max cap
     *                  and creates a new object array with that size. 
     */
    public ArrayList(){
        origCap = defCap;
        list = new Object[origCap];
    }
    
    /*  Method Name   : <<ArrayList()>>
     *  Parameters    : int for size of array.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : Constructor sets the size of the array to passed value. 
     */
    public ArrayList(int size) throws InvalidSizeException{
        if(size > defCap){
            throw new InvalidSizeException();
        }
        else{
            origCap = size;
            list = new Object[size];
        }
    }
    
    /*  Method Name   : addItem()
     *  Parameters    : Generic item.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : if there is space in the existing object array, 
     *                  adds to the empty space. otherwise enlarges and adds 
     */
    public void addItem(E item) throws MaximumCapacityException{
        boolean test = false;
        for(int i = 0; i < list.length; i++){
            if(list[i] == null){//there was an empty spot
                setItem(i,item);
                test = true;
                break;
            }
        }
        if (test == false){ //there was not an empty spot
            enlarge();
            setItem(numElements, item);
        }
        
    }
    
    /*  Method Name   : getItem()
     *  Parameters    : index of item in array
     *  Return Values : Generic object
     *  Partners      : N/A.
     *  Description   : returns the object in the array at the passed index. 
     */
    public E getItem(int index) throws IndexOutOfBoundsException{
        if(index > numElements){
            throw new IndexOutOfBoundsException("Index Out Of Range");
        }
        else{
            return (E)list[index];
        }
    }
    
    /*  Method Name   : setItem()
     *  Parameters    : int index to place object E in.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : Sets the value of the array to whatever is passed at the given index. 
     */
    public void setItem(int index, E item) throws IndexOutOfBoundsException{
        if(index > numElements){
            throw new IndexOutOfBoundsException("Index Out Of Range"); 
        }
        else{
            list[index] = item;
            numElements++;
        }    
    }
    
    /*  Method Name   : removeItem()
     *  Parameters    : index of item to remove.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : removes an item at the given index. 
     */
    public E removeItem(int index) throws IndexOutOfBoundsException{
        E toReturn = null;
        if(index > numElements){
            throw new IndexOutOfBoundsException("Index Out Of Range");
        }
        else{
            toReturn = ((E)list[index]);
            list[index] = null;
            numElements--;
        }
        return toReturn;
        
    }
    
    /*  Method Name   : findItem()
     *  Parameters    : Generic item E.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : searches through the array to find an equivalent 
     *                  item to the one passed using compareTo
     */
    public int findItem(E item){
        int index = -1;
        for (int i = 0; i < numElements - 1; i++){
            if (((Comparable<E>)list[i]).compareTo((E)item) == 0){
                index = i;
            }
        }
        return index;
    }
    
    /*  Method Name   : isEmpty()
     *  Parameters    : None.
     *  Return Values : boolean true or false.
     *  Partners      : N/A.
     *  Description   : checks if numElements is 0. 
     */
    public boolean isEmpty(){
        boolean test = false;
        if (numElements == 0){
            test = true;
        }
        return test;
    }
    
    /*  Method Name   : lengthIs()
     *  Parameters    : None.
     *  Return Values : length of array.
     *  Partners      : N/A.
     *  Description   : returns number of elements in the array. 
     */
    public int lengthIs(){
        return numElements;
    }
    
    /*  Method Name   : clear()
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : sets all elements to null; 
     */
    public void clear(){
        for (int i = 0; i < numElements; i++){
            list[i] = null;
        }
        numElements = origCap;
    }
    
    /*  Method Name   : toString()
     *  Parameters    : None.
     *  Return Values : String to be printed.
     *  Partners      : N/A.
     *  Description   : Calls upon the toString of each item in the array
     *                  returns formatted string of all. 
     */
    public String toString(){
        String toReturn = "";
        for (int i = 0; i < numElements; i++){
            if(list[i] != null){
                toReturn += list[i].toString() + "\n";    
            }
        }
        return toReturn;
    }
    
    /*  Method Name   : sort()
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : Sorts all items by whatever is defined for compareTo. 
     */
    public void sort(){
        Comparable<E> tempitem = null;
        for (int i = 1; i < numElements; i++){
            for (int j = i; j > 0; j--){
                if( ((Comparable<E>)list[j]).compareTo((E)list[j-1]) == -1) {
                    tempitem = ((Comparable<E>)list[j]);
                    list[j] = list[j-1];
                    list[j-1] = tempitem;
                }
            }
        }
    }
    
    /*  Method Name   : enlarge()
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : N/A.
     *  Description   : creates a new larger array of objects and copies items
     *                  from the existing array to the new one.
     */
    private void enlarge() throws MaximumCapacityException{
        int newsize = origCap + numElements;
        Object[] larger = null;
        
        if(numElements == defCap){
            throw new MaximumCapacityException();
        }
        else{      
            if (newsize > defCap){
                larger = new Object[defCap];
            }
            else{
                larger = new Object[newsize];
            }
            
            for (int i = 0; i < list.length; i++){
                larger[i]=list[i];
            }
            list = larger;
        }
    }
}