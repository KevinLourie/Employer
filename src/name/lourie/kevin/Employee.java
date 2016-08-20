package name.lourie.kevin;

import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents an employee
 */
public class Employee {

	private String employeeName;
	private String EID;
	private String address;
	private String phoneNumber;
	private Date DOB;
	private String securityClearance;

	Employee() {
		employeeName = "";
		EID = "";
		address = "";
		phoneNumber = "";
		DOB = new Date();
		securityClearance = "";
	}

	Employee(String employeeName, String EID, String address, String phoneNumber, Date DOB, String securityClearance) {
		// Set all field values to the corresponding parameters
		this.employeeName = employeeName;
		this.EID = EID;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.DOB = DOB;
		this.securityClearance = securityClearance;
	}

	Employee(String[] arr) {

	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getEID() {
		return EID;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Date getDOB() {
		return DOB;
	}

	public String getSecurityClearance() {
		return securityClearance;
	}

	public void setEmployeeName(String en) {
		employeeName = en;
	}

	public void setEID(String id) {
		EID = id;
	}

	public void setAddress(String a) {
		address = a;
	}

	public void setPhoneNumber(String pn) {
		phoneNumber = pn;
	}

	public void setDOB(Date d) {
		DOB = d;
	}

	public void setSecurityClearance(String sc) {
		securityClearance = sc;
	}

	/**
	 * Return the information about an employee as a String
	 * 
	 * @return employee information
	 */
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		return employeeName + " " + EID + " " + address + " " + phoneNumber + " " + sdf.format(DOB) + " " + securityClearance;
	}
	
	public void update(Employee e) {
		employeeName = e.employeeName;
		EID = e.EID;
		address = e.address;
		phoneNumber = e.phoneNumber;
		DOB = e.DOB;
		securityClearance = e.securityClearance;
	}

	/** 
	 * Compare two employees' names and sort them alphabetically
	 */
	public static Comparator<Employee> compareByName =  new Comparator<Employee>() {
		public int compare(Employee o1, Employee o2) {
			return o1.employeeName.compareTo(o2.employeeName);
		}
	};

	/**
	 * Compare two employees' EIDs and sort them in increasing order
	 */
	public static Comparator<Employee> compareByEID =  new Comparator<Employee>() {
		public int compare(Employee o1, Employee o2) {
			return o1.EID.compareTo(o2.EID);
		}
	};

	/**
	 * Compare two employees' DOB and sort them in decreasing order
	 */
	public static Comparator<Employee> compareByDOB = new Comparator<Employee>() {
		public int compare(Employee o1, Employee o2) {
			return o1.DOB.compareTo(o2.DOB);
		}
	};

	/**
	 * Compare two employees' DOB and sort them in increasing order
	 */
	public static Comparator<Employee> reverseCompareByDOB = new Comparator<Employee>() {
		public int compare(Employee o1, Employee o2) {
			return o2.DOB.compareTo(o1.DOB);
		}
	};

	/**
	 * Compare two employees' EID. If same, compare employee names. 
	 */
	public static Comparator<Employee> CompareByNameandEID =  new Comparator<Employee>(){
		public int compare(Employee o1, Employee o2) {
			if(!o1.employeeName.equals(o2.employeeName)) {		
				return o1.employeeName.compareTo(o2.employeeName);	
			}
			return o1.EID.compareTo(o2.EID);
		}
	};
}












