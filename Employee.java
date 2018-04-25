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

package employeeType.employee;
import exceptions.InvalidEmployeeNumberException;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Employee implements Comparable<Employee>, Serializable{
    private String firstName;
    private String lastName;
    private char middleInitial;
    private boolean fullTime;
    private char gender;
    private int employeeNum;

    /*  Method Name   : <<Employee>>
     *  Parameters    : Strings for first name and last name
     *                  Characters for gender and middle initial
     *                  Integer for employee number
     *                  Boolean for full time or part time
     *  Return Values : N/A.
     *  Partners      : None.
     *  Description   : Constructor method for Employee.java, accepts information 
     *                  that will be used by all employee subtypes.
     */
    public Employee(String fn, String ln, char m, char g, int empNum, boolean ft) throws InvalidEmployeeNumberException{
        setFirstName(fn);
        setLastName(ln);
        setMiddleI(m);
        setGender(g);
        setEmployeeNumber(empNum);
        fullTime = ft;
    }

    /*  Method Name   : getEmployeeNumber
     *  Parameters    : None.
     *  Return Values : Int for employee number.
     *  Partners      : None.
     *  Description   : Returns an employee number for the given employee. 
     */
    public int getEmployeeNumber(){
        return employeeNum;
    }

    /*  Method Name   : setEmployeeNumber
     *  Parameters    : Int for employee number.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Validates and sets an employee number for a new employee. 
     */
    public void setEmployeeNumber(int empNum) throws InvalidEmployeeNumberException{
        if (empNum > 10000 && empNum < 99999){
            employeeNum = empNum;
        }
        else{
            throw new InvalidEmployeeNumberException(empNum);
        }
    }

    /*  Method Name   : getFirstName
     *  Parameters    : None.
     *  Return Values : String for employee's first name.
     *  Partners      : None.
     *  Description   : Returns an employee's first name. 
     */
    public String getFirstName(){
        return firstName;
    }

    /*  Method Name   : getLastName
     *  Parameters    : None.
     *  Return Values : String for employee's last name.
     *  Partners      : None.
     *  Description   : Returns an employee's last name. 
     */
    public String getLastName(){
        return lastName;
    }

    /*  Method Name   : getMiddleInitial
     *  Parameters    : None.
     *  Return Values : Char for employee's middle initial.
     *  Partners      : None.
     *  Description   : Returns an employee's middle initial. 
     */
    public char getMiddleInitial(){
        return middleInitial;
    }

    /*  Method Name   : getGender
     *  Parameters    : None.
     *  Return Values : Char for employee's gender.
     *  Partners      : None.
     *  Description   : Returns an employee's gender. 
     */
    public char getGender(){
        return gender;
    }

    /*  Method Name   : setFirstName
     *  Parameters    : String for first name.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Sets the first name of the current employee. 
     */
    public void setFirstName(String fn){
        firstName = fn;
    }

    /*  Method Name   : setLastName
     *  Parameters    : String for last name.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Sets the last name of the current employee. 
     */
    public void setLastName(String ln){
        lastName = ln;
    }

    /*  Method Name   : setMiddleI
     *  Parameters    : Char for middle initial.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Sets the middle initial of the current employee. 
     */
    public void setMiddleI(char m){
        middleInitial = m;
    }

    /*  Method Name   : setGender
     *  Parameters    : Char for gender.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Validates and sets the gender of the current employee. 
     *                  If any value other than "M" or "F" is recieved, the 
     *                  gender is set to "F" by default. 
     */
    public void setGender(char g){
        if(g=='m' || g=='M'){
            gender = 'M';
        }
        else{
            gender = 'F';
        }

    }

    /*  Method Name   : equals
     *  Parameters    : Object for other employee.
     *  Return Values : Boolean to show if other employee exists.
     *  Partners      : None.
     *  Description   : Compares the employee numbers of two different employee
     *                  to see if they are the same. 
     */
    @Override
    public boolean equals(Object e2){
        if (this.employeeNum == ((Employee)e2).employeeNum){
            return true;
        }
        else{
            return false;
        }

    }

    /*  Method Name   : toString
     *  Parameters    : None.
     *  Return Values : Formatted string.
     *  Partners      : None.
     *  Description   : Overrides the default toString method to set a template 
     *                  for subtypes of employees. 
     */
    @Override
    public String toString(){
        String time;
        if (fullTime)
        {
            time = "Full Time";
        }
        else
        {
            time = "Part Time";
        }

        String s = String.format(employeeNum + 
                "\n" + 
                lastName + 
                ", " + 
                firstName + " " +
                middleInitial + 
                ".\n" + 
                "Gender: " + 
                gender + 
                "\n" + 
                "Status: " + 
                time + "\n");
        return s;
    }
    
    /*  Method Name   : compareTo
     *  Parameters    : Another employee number to be compared
     *  Return Values : int representing whethere the passed employee's number is:
     *                  less than (1)
     *                  greater than (-1)
     *                  equal to(0)
     *  Partners      : N/A.
     *  Description   : compares the calling employee with another employee and 
     *                  returns an int value representing the relationship
     */
    public int compareTo(Employee e){
        int toReturn = 0;
        if(this.getEmployeeNumber() > e.getEmployeeNumber()){
            toReturn = 1;
        }
        if(this.getEmployeeNumber() == e.getEmployeeNumber()){
            toReturn = 0;
        }
        if(this.getEmployeeNumber() < e.getEmployeeNumber()){
            toReturn = -1;
        }
        
        return toReturn;      
    }
    
    /*  Method Name   : ABSTRACT calculateWeeklyPay
     *  Parameters    : N/A.
     *  Return Values : N/A.
     *  Partners      : N/A.
     *  Description   : Abstract class, not implemented. 
     */
    public abstract double calculateWeeklyPay();
    
    /*  Method Name   : ABSTRACT annualRaise
     *  Parameters    : N/A.
     *  Return Values : N/A.
     *  Partners      : N/A.
     *  Description   : Abstract class, not implemented. 
     */
    public abstract void annualRaise();
    
    /*  Method Name   : ABSTRACT holidayBonus
     *  Parameters    : N/A.
     *  Return Values : N/A.
     *  Partners      : N/A.
     *  Description   : Abstract class, not implemented. 
     */
    public abstract double holidayBonus();
    
    /*  Method Name   : ABSTRACT resetWeek
     *  Parameters    : N/A.
     *  Return Values : N/A.
     *  Partners      : N/A.
     *  Description   : Abstract class, not implemented. 
     */
    public abstract void resetWeek();
}
