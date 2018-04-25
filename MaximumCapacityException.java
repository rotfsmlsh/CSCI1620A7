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

package exceptions;
@SuppressWarnings("serial")
public class MaximumCapacityException extends Exception
{
   public MaximumCapacityException()
   {
      super();
   }

   public MaximumCapacityException(String message)
   {
      super(message);
   }
}