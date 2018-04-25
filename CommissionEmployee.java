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
public class CommissionEmployee extends Employee 
{
    private double sales;
    private double rate;

    /*  Method Name   : <<CommissionEmployee>>
     *  Parameters    : Strings for first name and last name
     *                  Characters for gender and middle initial
     *                  Integer for employee number
     *                  Boolean for full time or part time
     *                  Double for pay rate
     *  Return Values : N/A.
     *  Partners      : None.
     *  Description   : Constructor method for CommissionEmployee.java, accepts all
     *                  that an Employee requires as well as a double for rate, 
     *                  sets sales to 0.0.
     */
    public CommissionEmployee(String fn, String ln, char m, char g, 
                              int empNum, boolean ft, double r)
                              throws InvalidEmployeeNumberException{
        super(fn, ln, m, g, empNum, ft);
        rate = r;
        sales = 0.0;
    }

    /*  Method Name   : increaseSales
     *  Parameters    : Double for amount to increase sales
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies an increase to the number of sales for a 
     *                  commission employee. Checks to verify sales are not 
     *                  being increased by a non-positive number.
     */
    public void increaseSales(double inSales){
        if (inSales < 0)
        {
            System.out.println("Error: Cannot increase sales by a negative amount.");
        }
        else
        {
            sales += inSales;
        }
    }

    /*  Method Name   : toString
     *  Parameters    : None.
     *  Return Values : Formatted String.
     *  Partners      : None.
     *  Description   : Overrides the Employee toString method to additionally list
     *                  rate percentage and number of sales. 
     */
    @Override
    public String toString(){
        return String.format("%sRate: %.2f \nSales: %.2f\n", super.toString(), rate, sales);
    }

    /*  Method Name   : calculateWeeklyPay
     *  Parameters    : None.
     *  Return Values : Double for amount of weekly pay
     *  Partners      : None.
     *  Description   : Calculates the amount of pay for a Commission Employee
     *                  based upon their sales and rate. Rounds rate down to two
     *                  decimal places.
     */
    public double calculateWeeklyPay(){
        rate *= 100;
        rate = Math.floor(rate)/100;
        return sales * (rate/100);
    }

    /*  Method Name   : annualRaise
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies a .2 percent annual raise to the rate of pay 
     *                  for a commission employee. Rounds rate down to two 
     *                  decimal places.
     */
    public void annualRaise(){
        rate += 0.2;
        rate *= 100;
        rate = Math.floor(rate)/100;
    }

    /*  Method Name   : holidayBonus
     *  Parameters    : None.
     *  Return Values : Double for amount of holiday bonus recieved.
     *  Partners      : None.
     *  Description   : Returns 0.0 since commission employees do not recieve a 
     *                  holiday bonus.
     */
    public double holidayBonus(){
        return 0.0;
    }

    /*  Method Name   : resetWeek
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Resets the sales of a commission employee
     *                  to zero.
     */
    public void resetWeek(){
        sales = 0.0;
    }
}
