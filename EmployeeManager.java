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

import employeeType.employee.Employee;
import employeeType.subTypes.CommissionEmployee;
import employeeType.subTypes.HourlyEmployee;
import employeeType.subTypes.SalaryEmployee;
import exceptions.EmptyListException;
import exceptions.InvalidCharacterException;
import exceptions.InvalidEmployeeNumberException;
import exceptions.InvalidSizeException;
import exceptions.MaximumCapacityException;
import dataStructures.ArrayList;
import dataStructures.LinkedList;
import dataStructures.Queue;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

class EmployeeManager {
    private ArrayList<Employee> employees;
    private final int employeeMax = 10;
    private LinkedList<Employee> hourlyList;
    private LinkedList<Employee> salaryList;
    private LinkedList<Employee> commissionList;
    private Queue<Employee> vacationRequests;
    
    /*  Method Name   : <<EmployeeManager>>
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Constructor method for the EmployeeManager class.
     *                  Creates a new array List of employees using 3 as the initial size. 
     *                  Creates a new Linked List for each type of employee.
     */
    public EmployeeManager(){
        try{
            employees = new ArrayList<Employee>(3);
            hourlyList = new LinkedList<Employee>("Hourly List");
            salaryList = new LinkedList<Employee>("Salary List");
            commissionList = new LinkedList<Employee>("Commission List");
            vacationRequests = new Queue<Employee>("Vacation Requests"); 
        }
        catch(InvalidSizeException ISE){
            employees = new ArrayList<Employee>();
        }
    }
    
    /*  Method Name   : addEmployee
     *  Parameters    : Integer for type of employee to be created
     *                  Strings for first name and last name
     *                  Characters for gender and middle initial
     *                  Integer for employee number
     *                  Boolean for full time or part time
     *                  Double for rate
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Creates a new employee object of the given type in the array list
     *                  as well as in the appropriate linked list.
     */
    public void addEmployee(int type, String fn, String ln, char m, char g, int en, boolean ft, double amount)
                            throws InvalidEmployeeNumberException{
        boolean exists;
        Employee tempEmployee = null;
        try{
            switch(type){
                case 1:  //This is an hourly employee
                    exists = false;
                    tempEmployee = new HourlyEmployee(fn, ln, m, g, en, ft, amount);
                    if(employees.findItem(tempEmployee) != -1){
                        exists = true;
                    }
                    
                    if(exists == true){
                        System.out.println("\nDuplicate Not Added\n");
                    }
                    else{
                        employees.addItem(tempEmployee);
                        hourlyList.insertAtFront(tempEmployee);
                    }
                    break; //end case 1
                    
                case 2:  //This is a salary employee
                    exists = false;
                    tempEmployee = new SalaryEmployee(fn, ln, m, g, en, ft, amount);
                    if(employees.findItem(tempEmployee) != -1){
                        exists = true;
                    }
                    
                    if(exists == true){
                        System.out.println("\nDuplicate Not Added\n");
                    }
                    else{
                        employees.addItem(tempEmployee);
                        salaryList.insertAtFront(tempEmployee);
                    }
                    break; //end case 2
                    
                case 3:  //This is a commission employee
                    exists = false;
                    tempEmployee = new CommissionEmployee(fn, ln, m, g, en, ft, amount);
                    if(employees.findItem(tempEmployee) != -1){
                        exists = true;
                    }
                    
                    if(exists == true){
                        System.out.println("\nDuplicate Not Added\n");
                    }
                    else{
                        employees.addItem(tempEmployee);
                        commissionList.insertAtFront(tempEmployee);
                    }
                    break; //end case 3
                    
                default: //This will happen if the employee type is invalid
                    System.out.println("\nInvalid Employee Type, None Added!\n");
                    break; //end default case
            }//end switch
        }
        catch (MaximumCapacityException e){
            System.out.println("No more employees can be added!");
        }
    }//end method addEmployee
    
    /*  Method Name   : removeEmployee
     *  Parameters    : integer for index of employee to remove
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Calls upon ArrayList's removeItem and the appropriate linked list 
     *                  findandremove if the index is valid.
     */
    public void removeEmployee(int index){
        Employee temp;
        if(index != -1){
            temp = employees.getItem(index);
            employees.removeItem(index);
            
            if(temp instanceof HourlyEmployee){
                hourlyList.findAndRemove(temp);
            }
            
            if(temp instanceof SalaryEmployee){
                salaryList.findAndRemove(temp);
            }
            
            if(temp instanceof CommissionEmployee){
                commissionList.findAndRemove(temp);
            }
        }
        else{
            System.out.println("Employee not found!");
        }
    }
    
    /*  Method Name   : listAll
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Prints a formatted string for all employees.
     */
    public void listAll(){
        System.out.println(employees.toString());
    }
    
    /*  Method Name   : listHourly
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Prints a formatted string for each 
     *                  hourly employee.
     */
    public void listHourly(){
        System.out.print(hourlyList.toString());
    }
    
    /*  Method Name   : listSalary
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Prints a formatted string for each 
     *                  salary employee.
     */
    public void listSalary(){
        System.out.print(salaryList.toString());
    }
    
    /*  Method Name   : listCommission
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Prints a formatted string for each 
     *                  commission employee.
     */
    public void listCommission(){
        System.out.print(commissionList.toString());
    }
    
    /*  Method Name   : resetWeek
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Resets the week for all employees. 
     */
    public void resetWeek(){
        for(int i = 0; i < employees.lengthIs(); i++){
            if(employees.getItem(i) != null){
                employees.getItem(i).resetWeek();
            }
        }
    }
    
    /*  Method Name   : calculatePayout
     *  Parameters    : None.
     *  Return Values : double for total payout
     *  Partners      : None.
     *  Description   : Calculates total weekly pay 
     *                  of all employees 
     */
    public double calculatePayout(){
        double payout = 0;
        for(int i = 0; i < employees.lengthIs(); i++){
            if(employees.getItem(i) != null){
                payout += employees.getItem(i).calculateWeeklyPay();
            }
        }
        return payout;
    }
    
    /*  Method Name   : getIndex
     *  Parameters    : integer for employee number
     *  Return Values : integer for matching array index
     *  Partners      : None.
     *  Description   : Finds and returns the index of an 
     *                  employee with the given employee number
     */
    public int getIndex(int empNum){
        int match = -1;
        for(int i = 0; i < employees.lengthIs(); i++){
            if(employees.getItem(i) != null){
                if(employees.getItem(i).getEmployeeNumber() == empNum){
                    match = i;   
                }
            }
        }
        return match;
    }
    
    /*  Method Name   : annualRaises
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies an annual raise to each
     *                  employee. 
     */
    public void annualRaises(){
        for(int i = 0; i < employees.lengthIs(); i++){
            employees.getItem(i).annualRaise();
        }
    }
    
    /*  Method Name   : holidayBonuses
     *  Parameters    : None
     *  Return Values : Double for total holiday bonuses.
     *  Partners      : None.
     *  Description   : calculates holiday bonus for all employees
     */
    public double holidayBonuses(){
        double totalBonus = 0;
        for(int i = 0; i < employees.lengthIs(); i++){
            if(employees.getItem(i) != null){
                totalBonus += employees.getItem(i).holidayBonus();
            }
        }
        return totalBonus;
    }
    
    /*  Method Name   : increaseHours
     *  Parameters    : Double for amount to increase hours, 
     *                  index of employee
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies an increase to the number of hours for an 
     *                  employee at given index. 
     */
    public void increaseHours(int index, double amount){
        if(employees.getItem(index) instanceof HourlyEmployee){
            ((HourlyEmployee)employees.getItem(index)).increaseHours(amount);
        }
    }
    
    /*  Method Name   : increaseSales
     *  Parameters    : Double for amount to increase sales, 
     *                  index of employee
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Applies an increase to the number of sales for an 
     *                  employee at given index. 
     */
    public void increaseSales(int index, double amount){
        if(employees.getItem(index) instanceof CommissionEmployee){
            ((CommissionEmployee)employees.getItem(index)).increaseSales(amount);
        }
    }
    
    /*  Method Name   : findAllBySubstring
     *  Parameters    : String of the substring to be found
     *  Return Values : ArrayList of employees whose names contain
     *                  the passed substring.
     *  Partners      : None.
     *  Description   : Checks all existing employees to see if thier 
     *                  name contains a passed substring by comparing
     *                  the hash values of the substring to each possible 
     *                  substrings hash value in the employees name.
     */
    public ArrayList<Employee> findAllBySubstring(String find) throws InvalidSizeException{ 
        ArrayList<Employee> matchedEmployees = new ArrayList<Employee>(employees.lengthIs()); 
        int counter = 0; 
        
        for(int i = 0; i < employees.lengthIs(); i++){ 
            String thisString = employees.getItem(i).getFirstName().trim() + employees.getItem(i).getLastName().trim();
            
            if(RabinKarp(thisString, find) != -1){
                matchedEmployees.setItem(counter, employees.getItem(i)); 
                counter++; 
            }
        }

        return matchedEmployees;
    }
    
    /*  Method Name   : RabinKarp
     *  Parameters    : String of the full name of the employee
     *                  String of the substring to be found
     *  Return Values : index of found employee via linearSearchRecursive
     *  Partners      : None.
     *  Description   : Processes the hash to be found and the hashes of each possible substring in the given 
     *                  string. Determines if passed substring exists in full string.
     */
    private int RabinKarp(String name, String find){
        int findHash = stringHash(find);
        int[] hashArray = null;
        int arrayLength = 0;
        String newFind = find;
        
        if (find.length() > name.length()){
            newFind = find.substring(0,name.length());
            System.out.println("\nInvalid substring length, shortening to: " + newFind + "\n");
        }
        
        arrayLength = (name.length() + 1) - newFind.length(); 
        hashArray = new int[arrayLength];

        
        RabinKarpHashes(name, hashArray, name.length() - newFind.length(), newFind.length()); 
        
        return linearSearchRecursive(hashArray, findHash, 0);
    }
      
    /*  Method Name   : RabinKarpHashes
     *  Parameters    : String of the full name of the employee
     *                  int[] of found hashes
     *  Return Values : int hash value of all substrings of size length
     *  Partners      : None.
     *  Description   : Finds the hash values of all substrings of size length 
     *                  in the String s, starting at index pos and down. 
     */
    private int RabinKarpHashes(String s, int[] hashes, int pos, int length){    
        if (pos == 0){
            hashes[pos] = stringHash(s.substring(0,length));
        }
        else{
            hashes[pos] = 26 * (RabinKarpHashes(s,hashes,pos-1,length) - (charNumericValue(s.charAt(pos-1)) * (int)Math.pow(26, length - 1))) 
                                + charNumericValue(s.charAt(pos + length -1));
        }
        return hashes[pos];
    }
    
    /*  Method Name   : linearSearchRecursive
     *  Parameters    : int[] of found hashes
     *                  int of the hash to find
     *                  int of the position being tested
     *  Return Values : int of the index of the found hash if found
     *                  -1 if hash is not found
     *  Partners      : None.
     *  Description   : Returns the position of key in the data array, or -1 if it is not present.  
     */    
    private int linearSearchRecursive(int[] data, int key, int pos){
        if (key == data[pos]){
            return pos;
        }
        if (pos + 1 < data.length){
            return linearSearchRecursive(data, key, pos + 1);
        }
        return -1;
    }
    
    /*  Method Name   : stringHash
     *  Parameters    : String representing the the whole string to be converted
     *  Return Values : Hash value of passed string
     *  Partners      : None.
     *  Description   : Returns the hash value of the passed string.  
     */  
    private int stringHash(String s){
        int hashValue = 0;
        for(int i = 0; i < s.length(); i++){
            hashValue += charNumericValue(s.charAt(i)) * Math.pow(26, s.length() - 1 - i);
        }
        return hashValue;
    }
    
    /*  Method Name   : charNumericValue
     *  Parameters    : char to find value of
     *  Return Values : int of the numeric value of the passed char
     *  Partners      : None.
     *  Description   : Returns a numerical value for a given character where
     *                  a = 0 and z = 25. Ignores case.  
     */  
    private int charNumericValue(char c){
        switch(c){
            case 'a': case 'A':
                return 0;
            case 'b': case 'B':
                return 1;
            case 'c': case 'C':
                return 2;
            case 'd': case 'D':
                return 3;
            case 'e': case 'E':
                return 4;
            case 'f': case 'F':
                return 5;
            case 'g': case 'G':
                return 6;
            case 'h': case 'H':
                return 7;
            case 'i': case 'I':
                return 8;
            case 'j': case 'J':
                return 9;
            case 'k': case 'K':
                return 10;
            case 'l': case 'L':
                return 11;
            case 'm': case 'M':
                return 12;
            case 'n': case 'N':
                return 13;
            case 'o': case 'O':
                return 14;
            case 'p': case 'P':
                return 15;
            case 'q': case 'Q':
                return 16;
            case 'r': case 'R':
                return 17;
            case 's': case 'S':
                return 18;
            case 't': case 'T':
                return 19;
            case 'u': case 'U':
                return 20;
            case 'v': case 'V':
                return 21;
            case 'w': case 'W':
                return 22;
            case 'x': case 'X':
                return 23;
            case 'y': case 'Y':
                return 24;
            case 'z': case 'Z':
                return 25;
            default:
                throw new InvalidCharacterException(c);
        }
    }
    
    /*  Method Name   : sort()
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Calls upon ArrayList's sort method as well as the sort 
     *                  methods for each non-empty linked list. 
     */
    public void sort(){
        employees.sort();
        try{
            hourlyList.sort();
        }
        catch(EmptyListException ELE){
            System.out.println("Hourly list is empty!");
        }
        try{
            salaryList.sort();
        }
        catch(EmptyListException ELE){
            System.out.println("Salary list is empty!");
        }
        try{
            commissionList.sort();
        }
        catch(EmptyListException ELE){
            System.out.println("Commission list is empty!");
        }
    }
    
    /*  Method Name   : addRequest()
     *  Parameters    : Employee number to add.
     *  Return Values : True or false if employee's request was added.
     *  Partners      : None.
     *  Description   : Checks to see if an employee with the given employee 
     *                  number exists then adds that employee to the vacation 
     *                  request queue.
     */
    public boolean addRequest(int empNum){
        if(getIndex(empNum) != -1){
            vacationRequests.enqueue(employees.getItem(getIndex(empNum)));
            return true;
        }
        else{
            return false;
        }
        
    }
    
    /*  Method Name   : viewNextRequest()
     *  Parameters    : None.
     *  Return Values : Employee object.
     *  Partners      : None.
     *  Description   : Returns the employee at the top of the queue. 
     */
    public Employee viewNextRequest(){
        return (vacationRequests.peek());
    }
    
    /*  Method Name   : grantNextRequest()
     *  Parameters    : None.
     *  Return Values : Employee object.
     *  Partners      : None.
     *  Description   : Ensures the queue is not empty then removes and returns 
     *                  the first employee from the queue.  
     */
    public Employee grantNextRequest(){
        if(!vacationRequests.isEmpty()){
            return (vacationRequests.dequeue());
        }
        else{
            return null;
        }
    }
    
    /*  Method Name   : outputRequests()
     *  Parameters    : None.
     *  Return Values : None.
     *  Partners      : None.
     *  Description   : Prints all employees with vacation requests. Prints the 
     *                  next employee to have their request granted after the 
     *                  entire list followed by "will receive next request.
     */
    public void outputRequests(){
        if(!vacationRequests.isEmpty()){
            System.out.println(vacationRequests.toString() + "\n");
            System.out.print(vacationRequests.peek() + "will receive next request\n");
        }
        else{
            System.out.println("No vacation requests");
        }
    }
    
    /*  Method Name   : loadEmployees()
     *  Parameters    : String employeeFile, String requestFile.
     *  Return Values : boolean.
     *  Partners      : None.
     *  Description   : Attempts to load the employee file as well as the request
     *                  file. returns true if successful.
     */
    public boolean loadEmployees(String employeeFile, String requestFile){
        Employee tempEmployee = null;
        boolean employeeFlag;
        
        hourlyList.clear();
        salaryList.clear();
        commissionList.clear();
        vacationRequests.clear();
        
        //populate employee lists
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("employees.ser"))){
            while(true){
                tempEmployee = (Employee)input.readObject();
                employees.addItem(tempEmployee);
                if (tempEmployee instanceof CommissionEmployee){
                    commissionList.insertAtFront(tempEmployee);
                }
                if(tempEmployee instanceof HourlyEmployee){
                    hourlyList.insertAtFront(tempEmployee);
                }
                if(tempEmployee instanceof SalaryEmployee){
                    salaryList.insertAtFront(tempEmployee);
                }
                employeeFlag = true;
            }
        }
        catch(MaximumCapacityException MCE){
            System.out.println("Maximum Capacity Reached");
            employeeFlag = true;
        }
        catch(EOFException EOFE){
            employeeFlag = true;
        }
        catch(FileNotFoundException FNFE){
            System.err.println("Error opening file.");
            return false;
        } 
        catch (ClassNotFoundException CNFE) {
            System.err.println("Error opening file.");
            return false;
        } 
        catch (IOException IOE) {
            System.err.println("Error opening file.");
            return false;
        }
        
        //populate vacation requests
        try(Scanner in = new Scanner(new File(requestFile))){
            int id = in.nextInt();
            vacationRequests.enqueue(employees.getItem(getIndex(id)));
        }
        catch(FileNotFoundException FNFE){
            System.err.println("Error opening file.");
        }
        catch(NoSuchElementException NSEE){
            System.out.println("No vacation requests");
        }
        
        if(employeeFlag){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    /*  Method Name   : saveEmployees()
     *  Parameters    : String employeeFile, String requestFile.
     *  Return Values : boolean.
     *  Partners      : None.
     *  Description   : Attempts to save the employee file as well as the request
     *                  file. returns true if successful.
     */
    public boolean saveEmployees(String employeeFile, String requestFile){
        boolean objectFlag;
        boolean formatterFlag;
        boolean returnFlag;
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(employeeFile))){
            for(int i = 0; i < employees.lengthIs(); i++){
                output.writeObject(employees.getItem(i));
            }
            objectFlag = true;
        }
        catch(IOException IOE){
            System.err.println("Error Opening File");
            System.exit(1);
            objectFlag = false;
        }
        
        try(Formatter out = new Formatter(requestFile)){
            for(int j = 0; j < vacationRequests.lengthIs(); j++){
                out.format("%s \n", vacationRequests.dequeue().getEmployeeNumber());
            }
            formatterFlag = true;
        }
        catch(FileNotFoundException FNFE){
            formatterFlag = false;
        }
        
        if(objectFlag && formatterFlag){
            returnFlag = true;
        }
        else{
            returnFlag = false;
        }
        
        return returnFlag;
    }
    
    /*  Method Name   : processUpdates()
     *  Parameters    : String fileName.
     *  Return Values : boolean.
     *  Partners      : None.
     *  Description   : Attempts to save the current employees to file 
     *                  as well as the request file. returns true if successful.
     */
    public boolean processUpdates(String filename){
        boolean returnFlag = false;
        String emptyCheck = "";
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            emptyCheck = br.readLine();
        }
        catch(IOException IOE){
            System.out.println("Update file is empty");
        }
        
        if(!(emptyCheck == null)){
            try(Scanner in = new Scanner(new File(filename))){
                while(true){
                    int id = in.nextInt();
                    double amount = in.nextDouble();
                    
                    //apply increase
                    if(getIndex(id) != -1 && !(employees.getItem(getIndex(id)) instanceof SalaryEmployee)){
                        
                        if(employees.getItem(getIndex(id)) instanceof HourlyEmployee && amount > 0.0){
                            increaseHours(getIndex(id), amount);
                        }
                        if(employees.getItem(getIndex(id)) instanceof CommissionEmployee && amount > 0.0){
                            increaseSales(getIndex(id), amount);
                        }
                        returnFlag = true;
                    }
                    else{
                        returnFlag = false;
                    }
                }
            }
            catch(FileNotFoundException FNFE){
                System.err.println("Error opening file updates.");
                returnFlag = false;
            }
            catch(NoSuchElementException NSEE){
                returnFlag = true;
            }
        }
        else{
            
            return false;
        }
        return returnFlag;
    }
}
