package name.lourie.kevin;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * List of employees
 *
 */
public class EmployeeList { 
	/** List of employees and their information */
	Employee[] employees = new Employee[60];

	/** Number of employees that are currently in the list */
	int size = 0;

	/**
	 * Read in file and store each employee in its own element in the array
	 * 
	 * @param location file to read
	 * @throws IOException
	 */
	EmployeeList(File location) throws IOException {
		String line;
		Boolean isNotFirstLine = false;
		
		// Make a FileReader
		FileReader fr = new FileReader(location);
		
		// Make a BufferedReader
		BufferedReader br = new BufferedReader(fr);
		
		// Make an array list
		ArrayList<Employee> al = new ArrayList<>();
		
		// Check if end
		while((line = br.readLine()) != null) {
			
			// Skip first line
			if(isNotFirstLine) {
				EmployeeParser ep = new EmployeeParser(line);
				Employee employee = new Employee();
				
				// Get employee information from the parser and save it to the employee 
				employee.setEmployeeName(ep.getEmployeeName());
				employee.setAddress(ep.getAddress());
				employee.setDOB(ep.getDOB());
				employee.setEID(ep.getEID());
				employee.setPhoneNumber(ep.getPhoneNumber());
				employee.setSecurityClearance(ep.getSecurityClearance());
				al.add(employee);	
			}
			isNotFirstLine = true;
		}
		br.close();
		
		// Converting employee array list back to array
		employees = al.toArray(employees);
		
		// Save the size
		size = al.size();
	}

	/**
	 * Add an employee to the array considering that employee is not already in it
	 * 
	 * @param e the employee to add
	 */
	public void add(Employee e) {
		int a = indexOf(e);
		// See if employee is in array
		if(a != -1) {
			// The employee exists already
			System.out.println("That employee is already in the list");
			return;
		}
		// Add employee to logical end of array and increment size
		employees[size++] = e;
	}

	/**
	 * Remove an employee to the array considering that employee is actually in it
	 * 
	 * @param e the employee to remove
	 */
	public void remove(Employee e) {
		int a = indexOf(e);
		if(a == -1) {
			System.out.println("That employee is not in the list");
			return;
		}	
		// Remove employee, move last element to empty index, and decrement size
		employees[a] = employees[size - 1];
		employees[size - 1] = null;
		size--;
	}

	/**
	 * Search for an employee by name and returns the information
	 * 
	 * @param e the employee
	 * @return the employee's information
	 */
	public Employee search(Employee e) {
		int m = indexOf(e);

		// Employee does not exist
		if(m == -1) {
			return null;
		}

		// Return employee at index m
		return employees[m];
	}

	/**
	 * Finds the index of an employee
	 * 
	 * @param e
	 * @return
	 */
	private int indexOf(Employee e) {
		for(int i = 0; i < size; i++) {
			// Check if employee names match
			if(e.getEmployeeName().equals(employees[i].getEmployeeName())) {
				return i;
			}
		}
		
		// Employee does not exist
		return -1;
	}

	/**
	 * Sort employee array using comparator
	 * @param comparator performs comparison needed for sorting
	 */
	public void sort(Comparator<Employee> comparator) {
		
		// Traverse the entire array excluding the last element and perform swaps
		for(int i = 0; i < size-1; i++) {
			
			// Find minimum employee name from next element to the end of the array
			int minIndex = findMin(i, comparator);
			
			// Swap current employee with minimum employee
			swap(i, minIndex);
		}
	}

	/**
	 * Sort by name
	 */
	public void sortByName() {
		sort(Employee.compareByName);
	}

	/**
	 * Sort by EID
	 */
	public void sortByID() {
		sort(Employee.compareByEID);
	}
	
	/**
	 * Sort by name and EID
	 */
	public void sortByNameandID() {
		sort(Employee.CompareByNameandEID);
	}

	/**
	 * Search through the array from a specified element to the end
	 * 
	 * @param i is the first element in a part of the employee array
	 * @return the index of the minimum element
	 */
	int findMin(int i, Comparator<Employee> comparator) {
		int minIndex = i;
		// Initialize minName to be name of first element
		Employee minEmployee = employees[i];
		// Traverse the rest of the array and find the smallest element
		for(int j = i+1; j < size; j++) {
			// Name of jth employee	
			Employee employee = employees[j];
			// Compare employees
			if(comparator.compare(minEmployee, employee) > 0) {
				minEmployee = employee;
				minIndex = j;
			}
		}
		return minIndex;
	}
	
	/**
	 * Find minimum employee
	 * 
	 * @param comparator performs comparisons needed to find the minimum employee
	 * @return minimum employee
	 */
	Employee findMin(Comparator<Employee> comparator) {
		int index = findMin(0, comparator);
		return employees[index];
	}

	/**
	 * Swap two elements in the employee array
	 * 
	 * @param i is the index of the first element
	 * @param j is the index of the second element
	 */
	private void swap(int i, int j) {
		// Switch jth employee with the ith employee
		Employee a = employees[i];
		employees[i] = employees[j];
		employees[j] = a;
	}
	
	public Employee getEmployee(int index) {
		return employees[index];
	}

	/**
	 * Display all employee information
	 */
	public void display() {
		// Iterate through each employee
		for(int i = 0; i < size; i++) {
			// Get an employee based on its index and display their information	
			Employee e = employees[i];	
			// Save information about employee
			String info = e.toString();
			// Print employee's information
			System.out.println(info);
		}
	}
	
	void display(List list) {
		list.removeAll();
		for(int i = 0; i < size; i++) {
		Employee e = employees[i];
		String info = e.toString();
		list.add(info);
		}
	}
}