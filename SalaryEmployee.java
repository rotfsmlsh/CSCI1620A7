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

package employeeType.subTypes;
import employeeType.employee.Employee;
import exceptions.InvalidEmployeeNumberException;

@SuppressWarnings("serial")
public class SalaryEmployee extends Employee 
{
    private double salary;

    /*  Method Name   : <<SalaryEmployee>>
     *  Parameters    : Strings for first name and last name
     *                  Characters for gender and middle initial
     *                  Integer for employee number
     *                  Boolean for full time or part time
     *                  Double for annual salary
     *  Return Values : N/A.
     *  Partners      : None.
     *  Description   : Constructor method for SalaryEmployee.java, accepts all
     *                  that an Employee requires as well as a double for salary.
     */
    public SalaryEmployee(String fn, String ln, char m, char g, 
                          int empNum, boolean ft, double s) 
                          throws InvalidEmployeeNumberException{
        super(fn, ln, m, g, empNum, ft);
        salary = s;
    }

    /*  Method Name   : toString
     *  Parameters    : None.
     *  Return Values : Formatted String.
     *  Partners      : None.
     *  Description   : Overrides the Employee toString method to additionally list
     *                  annual salary. 
     */
    @Override
    public String toString(){
        return String.format("%sSalary: %.2f\n", super.toString(), salary);
    }

    /*  Method Name   : calculateWeeklyPay
     *  Parameters    : None.
     *  Return Values : Double for amount of weekly pay.
     *  Partners      : None.
     *  Description   : Returns the weekly pay of a salary employee. 
     */
    public double calculateWeeklyPay(){
        return salary/52;
    }

    /*  Method Name   : annualRaise
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies an annual raise of six percent to the 
     *                  salary of a salary employee. Rounds down to the 
     *                  nearest hundredth. 
     */
    public void annualRaise(){
        salary *= 106;
        salary = Math.floor(salary)/100;
    }

    /*  Method Name   : holidayBonus
     *  Parameters    : None.
     *  Return Values : Double for amount of holiday bonus to be paid.
     *  Partners      : None.
     *  Description   : Calculates and returns the amount of money to be 
     *                  paid to a salary employee for thier holiday bonus. 
     */
    public double holidayBonus(){
        return salary * .03;
    }

    /*  Method Name   : resetWeek
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : No function. 
     */
    public void resetWeek(){}
}
