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
public class HourlyEmployee extends Employee 
{
    private double wage;
    private double hoursWorked;

    /*  Method Name   : <<HourlyEmployee>>
     *  Parameters    : Strings for first name and last name
     *                  Characters for gender and middle initial
     *                  Integer for employee number
     *                  Boolean for full time or part time
     *                  Double for hourly wage
     *  Return Values : N/A.
     *  Partners      : None.
     *  Description   : Constructor method for HourlyEmployee.java, accepts all
     *                  that an Employee requires as well as a double for wage.
     *                  Sets hours worked to 0.0.
     */
    public HourlyEmployee(String fn, String ln, char m, char g, 
                          int empNum, boolean ft, double w)
                          throws InvalidEmployeeNumberException{
        super(fn, ln, m, g, empNum, ft);
        wage = w;
        hoursWorked = 0.0;
    }

    /*  Method Name   : increaseHours
     *  Parameters    : Double for hours to increase.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies an increase to the number of hours worked by 
     *                  an hourly employee. Verifies that hours are not lowered. 
     */
    public void increaseHours(double hours){
        if(hours < 0)
        {
            System.out.println("Error, cannot increase hours by a negative value.");
        }
        else 
        { 
            hoursWorked += hours; 
        }
    }

    /*  Method Name   : toString
     *  Parameters    : None.
     *  Return Values : Formatted String.
     *  Partners      : None.
     *  Description   : Overrides the Employee toString method to additionally list
     *                  wage and hours worked. 
     */
    @Override
    public String toString(){
        return String.format("%sWage: %.2f \nHours Worked: %.2f\n", super.toString(), wage, hoursWorked);
    }

    /*  Method Name   : calculateWeeklyPay
     *  Parameters    : None.
     *  Return Values : Double for amount earned in a week.
     *  Partners      : None.
     *  Description   : Calculates and returns the amount of money earned
     *                  by an hourly employee in one week. Hours worked beyond
     *                  40 are paid at twice the normal rate. 
     */
    @Override
    public double calculateWeeklyPay(){
        double pay;
        double overtimeHours;
        double overtimeRate = wage * 2;
        double overtimePay;
        double regularPay;

        if (hoursWorked <= 40){
            pay = hoursWorked * wage;   
        }
        else{
            overtimeHours = hoursWorked - 40;
            overtimePay = overtimeHours * overtimeRate;
            regularPay = 40 * wage;
            pay = regularPay + overtimePay;
        }
        return pay;
    }

    /*  Method Name   : annualRaise
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies an annual raise of 5% to an 
     *                  hourly employee's wage. 
     */
    public void annualRaise(){
        wage *= 105;
        wage = Math.floor(wage)/100;
    } 

    /*  Method Name   : holidayBonus
     *  Parameters    : None.
     *  Return Values : Double for total holiday bonus.
     *  Partners      : None.
     *  Description   : Calculates and returns a holiday bonus equivalent to 
     *                  a weeks pay. 
     */
    @Override
    public double holidayBonus(){
        return wage * 40;
    }

    /*  Method Name   : resetWeek
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Sets an hourly employee's hours to zero. 
     */
    @Override
    public void resetWeek(){
        hoursWorked = 0.0;
    }   
}
