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

import java.util.Scanner;

import employeeType.employee.Employee;
import dataStructures.ArrayList;
import exceptions.InvalidCharacterException;
import exceptions.InvalidSizeException;
import exceptions.InvalidEmployeeNumberException;

import java.util.InputMismatchException;

public class EmployeeDriver
{
   static Scanner in = new Scanner(System.in);
   
   /*  Method Name   : menu()
    *  Parameters    : a list of strings representing the options to show.
    *  Return Values : N/A.
    *  Partners      : None.
    *  Description   : Displays the menus and prompts until a valid choice is made. 
    */
   public static int menu(String... options)
   {
      int choice = -1;
      for(int line = 0; line < options.length; line++)
            System.out.printf("%d. %s\n", line+1,options[line]);
      
      do{
         boolean valid = false;
         while(!valid){
             try{
                 System.out.print("Enter Choice: ");
                 choice = in.nextInt(); 
                 valid = true;
             }
             catch(InputMismatchException IME){
                 System.out.println("\nInvalid Choice, try again..."); 
                 in.nextLine();
             }
         }
         System.out.println("");
      }while(!(choice > 0 && choice <= options.length));

      return choice;
   }

   /*  Method Name   : main()
    *  Parameters    : String args[].
    *  Return Values : N/A.
    *  Partners      : None.
    *  Description   : Conducts operations based upon user input in the menu. 
    */
   public static void main(String args[])
   {
      int mainInput;   //Input for main menu
      int subInput1;   //Input for submenu
      int subInput2;   //Input for sub-submenu
      int en = -1;          //Inputting an employee number
      int index = -1;
      double amount = -1;
      EmployeeManager em = new EmployeeManager(); //The EmployeManager object
      boolean valid = false;

      if(em.loadEmployees("employees.ser", "requests.dat")){
          System.out.println("Employees loaded");
      }
      else{
          System.out.println("Employees not loaded");
      }
      
      //Main control loop, keep coming back to the
      //Main menu after each selection is finished
      do
      {
            //This is the main menu. Displays menu 
            //and asks for a choice, validates that
            //what is entered is a valid choice
            System.out.println("\n\nMain Menu\n");
            em.listAll();
            mainInput = menu("Employee Submenu", "Add Employee", "Remove Employee", "Calculate Weekly Payout", 
                             "Calculate Bonus", "Annual Raises", "Reset Week", "Find Employee", "Sort Employees",
                             "View Vacation Requests","Add Vacation Request" ,"Grant Vacation Request", "Employee Updates", "Quit");
            //Perform the correct action based upon Main menu input
            switch(mainInput)
            {
               //Employee Sub-menu
               case 1:
                  do
                  {

                     subInput1 = menu("Hourly Employees", "Salary Employee", "Comission Employees", "Back");

                     switch(subInput1)
                     {
                        case 1:
                           em.listHourly();
                           do
                           {

                              subInput2 = menu("Add Hours", "Back");

                              if( subInput2 == 1)
                              {
                                 valid = false;
                                 while(!valid){
                                     try{
                                         System.out.print("Employee Number: ");
                                         en = in.nextInt();
                                         index = em.getIndex(en);
                                         valid = true;
                                     }
                                     catch(InputMismatchException IME){
                                         System.out.println("\nInvalid Employee Number Entered, try again...");
                                         in.nextLine();
                                     }
                                 }
                                 
                                 if(index != -1)
                                 {
                                    System.out.print("Enter Hours: ");
                                    amount = in.nextDouble();
                                    em.increaseHours(index, amount);
                                    System.out.println("");
                                 }
                                 else
                                 {
                                    System.out.println("\nEmployee not found!");
                                 }

                              }
                           }while(subInput2 != 2);
                           break;

                        case 2:
                           em.listSalary();
                           subInput2 = menu("Back");

                           break;

                        case 3:
                           em.listCommission();
                           do
                           {
                              subInput2 = menu("Add Sales", "Back");

                              if( subInput2 == 1)
                              {
                                 valid = false;
                                 while(!valid){
                                     try{
                                         System.out.println("Employee Number: ");
                                         en = in.nextInt();
                                         index = em.getIndex(en);
                                         valid = true;
                                     }
                                     catch(InputMismatchException IME){
                                         System.out.println("\nInvalid Employee Number Entered, try again...");
                                         in.nextLine();
                                     }
                                 }
                                                 
                                 if(index != -1)
                                 {
                                    valid = false;
                                    while(!valid){
                                        try{
                                            System.out.print("Enter Sales: ");
                                            amount = in.nextDouble();
                                            em.increaseSales(index, amount);
                                            valid = true;
                                        }
                                        catch(InputMismatchException IME){
                                            System.out.println("\nInvalid amount entered, try again...");
                                            in.nextLine();
                                        }
                                    }
                                 }
                                 else
                                 {
                                    System.out.println("Employee not found!");
                                 }

                              }
                           }while(subInput2 != 2);
                           break;
                     }
                  }while(subInput1 != 4);
                  break;

                  //Add Employee
               case 2:
                  String fn, ln;
                  char mi = ' ';
                  char g  = ' ';
                  char f  = ' ';
                  boolean ft = true;

                  subInput1 = menu("Hourly", "Salary", "Commission");

                  System.out.print("Enter Last Name: ");
                  ln = in.next();
                  
                  System.out.print("Enter First Name: ");
                  fn = in.next();
                  
                  valid = false;
                  while(!valid){
                      try{
                          System.out.print("Enter Middle Initial: ");
                          mi = in.next().charAt(0);
                          valid = true;
                      }
                      catch(InputMismatchException IME){
                          System.out.println("\nInvalid initial entered, try again...");
                          in.nextLine();
                      }
                  }
                  valid = false;
                  
                  
                  while(!valid){
                      try{
                          System.out.print("Enter Gender: ");
                          g = in.next().charAt(0);
                          valid = true;
                      }
                      catch(InputMismatchException IME){
                          System.out.println("\nInvalid gender entered, try again...");
                          in.nextLine();
                      }
                  }
                  
                  
                  valid = false;
                  while(!valid){
                      try{
                          System.out.print("Enter Employee Number: ");
                          en = in.nextInt();
                          valid = true;
                      }
                      catch(InputMismatchException IME){
                          System.out.println("\nInvalid Employee Number Entered, try again...");
                          in.nextLine();
                      }
                  }

                  valid = false;
                  while(!valid){
                      try{
                          System.out.print("Full Time? (y/n): ");
                          f = in.next().charAt(0);
                          valid = true;
                      }
                      catch(InputMismatchException IME){
                          System.out.println("\nInvalid type entered, try again...");
                          in.nextLine();
                      }
                  }
                  
                  if(f == 'n' || f == 'N')
                  {
                     ft = false;
                  }

                  String rateType = "";
                  if(subInput1 == 1)
                  {
                     rateType = "Enter wage: ";
                  }
                  else if(subInput1 == 2)
                  {
                     rateType = "Enter salary: ";
                  }
                  else
                  {
                     rateType = "Enter rate: ";
                  }
                  
                  valid = false;
                  while(!valid){
                      try{
                          System.out.print(rateType);
                          amount = in.nextDouble();
                          valid = true;
                      }
                      catch(InputMismatchException IME){
                          System.out.println("\nInvalid amount entered, try again...");
                          in.nextLine();
                      }
                  }
                  valid = false;                  

                  while(!valid){
                      try{
                          em.addEmployee(subInput1, fn, ln , mi, g, en, ft, amount);
                          valid = true;
                      }
                      catch(InvalidEmployeeNumberException IENE){
                          System.out.println("\nInvalid employee number recieved, try again: ");
                          System.out.print("Enter Employee Number: ");
                          en = in.nextInt();
                          in.nextLine();
                      }
                  }
                  break;

                  //Remove Employee
               case 3:
                  valid = false;
                  while(!valid){
                      try{
                          System.out.print("Enter Employee Number to Remove: ");
                          en = in.nextInt();
                          valid = true;
                      }
                      catch(InputMismatchException IME){
                          System.out.println("Invalid Employee Number Entered, try again...");
                          in.nextLine();
                      }
                  }
                  valid = false;
                  
                  index = em.getIndex(en);
                  em.removeEmployee(index);
                  break;


                  //Calculate Weekly Payout
               case 4:
                  System.out.printf("Total weekly payout is %.2f\n", em.calculatePayout());
                  break;

                  //Calculate Bonus
               case 5:
                  amount = em.holidayBonuses();
                  System.out.printf("Total holiday bonus payout is %.2f\n", amount);
                  break;
                  //Apply Annual Raises
               case 6:
                  em.annualRaises();
                  System.out.println("Annual Raises applied.");
                  break;

                  //Reset the weeks values
               case 7:
                  em.resetWeek();
                  System.out.println("Weekly values reset.");
                  break;

                  //Find Employee
               case 8:
                  System.out.print("Enter substring of Employee name: ");
                  String substring = in.next();
                  ArrayList<Employee> ret = null;
                  try
                  {
                     ret = em.findAllBySubstring(substring);
                  }
                  catch(InvalidCharacterException ICE)
                  {
                     System.out.println("Invalid character found in search");
                     break;
                  }
                  catch(InvalidSizeException ISE){
                  }
                  System.out.println("Matches found:");
                  if(ret != null)
                     for(int i = 0; i < ret.lengthIs() && ret.getItem(i) != null; i++)
                        System.out.println(ret.getItem(i));
                  break;
                  
                  //Sort
               case 9: 
                   System.out.println("Sorting all employees by employee number.");
                   em.sort();
                   break;
                   
                   //View vacation requests
               case 10:
                   em.outputRequests();
                   break;
                   
                   //add vacation request
               case 11:                   
                   valid = false;
                   while(!valid){
                       try{
                           System.out.print("Enter Employee number for request: ");
                           int emp = in.nextInt();
                           em.addRequest(emp);
                           System.out.println("Employee " + emp + " added to vacation queue");
                           valid = true;
                       }
                       catch(InputMismatchException IME){
                           System.out.println("Invalid Employee Number Entered, try again...");
                           in.nextLine();
                       }
                   }
                   valid = false;
                   break;
             
                   //grant vacation request
               case 12: 
                   Employee requesting = em.grantNextRequest();
                   if(requesting != null){
                       System.out.println(requesting + "granted vacation request");
                   }
                   else{
                       System.out.println("No vacation requests");
                   }
                   break;
                   
                   //Execute Employee Updates
               case 13:
                   System.out.println("Enter name of update file: ");
                   String filename = in.next();
                   if(em.processUpdates(filename)){
                       System.out.println("Updates processed successfully");
                   }
                   else{
                       System.out.println("Updates not processed");
                   }
                   break;
                   
                  //Exit
               case 14:
                  System.out.println("\nThank you for using the Employee Manager!\n");
                  if(em.saveEmployees("employees.ser", "requests.dat")){
                      System.out.println("Employees stored");
                  }
                  else{
                      System.out.println("Employees not stored");
                  }
               }
      }while(mainInput != 14);
   }
}
