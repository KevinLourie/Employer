package name.lourie.kevin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * Extract elements from a line in the file. Elements are separated by commas. Construct the class with the line, call getters to get information
 */
public class EmployeeParser {
	private int r;
	private int i;
	private String line;
	// Will contain elements from a String
	private String[] column = new String[6];

	/**
	 * Get employee name from first column
	 * @return the name
	 */
	public String getEmployeeName() {
		return column[0];
	}

	/**
	 * Get EID from second column
	 * @return the EID
	 */
	public String getEID() {
		return column[1];
	}

	/**
	 * Get address from third column
	 * @return the address
	 */
	public String getAddress() {
		return column[2];
	}

	/**
	 * Get phone number from fourth column
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return column[3];
	}

	/**
	 * Get date of birth from fifth column. This will convert it from a String to a date object.
	 * @return the date of birth
	 */
	public Date getDOB() {
		String pattern = "MM/dd/yyyy";
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    try {
	      Date date = format.parse(column[4]);
	      return date;
	    } catch (ParseException e) {
	      e.printStackTrace();
	      return null;
	    }
	}

	/**
	 * Get security clearance from sixth column
	 * @return the security clearance
	 */
	public String getSecurityClearance() {
		return column[5];
	}

	/**
	 * Takes a String with elements separated by commas, then creates an array with those elements. Those elements may 
	 * be surrounded by quotation marks. The quotation marks will be deleted. 
	 * 
	 * @param Has elements separated by commas
	 * @return an array of strings where each element is a piece of information about an employee
	 */ 
	EmployeeParser(String line) {
		this.line = line;
		r = 0;
		i = 0;
		// Traverses through entire String
		for(i = 0;; ) {
			// Look at the current character and see if it is a quotation mark
			if(line.charAt(i) == '"') {
				// Extract the next quoted element
				if(getQuotedElement()) {
					// This is the last element
					break;
				}
			}
			else {
				// Extract the next unquoted element
				if(getUnquotedElement()) {
					// This is the last element
					break;
				}
			}
		}

	}

	/**
	 * Extracts the next unquoted element out of it and adds it to the logical end of the array
	 * 
	 * @return true if last element
	 */
	private Boolean getUnquotedElement() {
		// Find index of comma, if it exists
		int x = line.indexOf(',', i);
		String element;
		if(x == -1) {
			// This is the last element
			element = line.substring(i);
		}
		else {
			// This is the next element
			element = line.substring(i, x);
			i = x + 1;
		}
		// Add element to logical end of array
		column[r++] = element;
		return x==-1;
	}

	/**
	 * Extracts the next element out of it and adds it to the logical end of the array
	 * 
	 * @return true if last element
	 */
	private Boolean getQuotedElement() {
		// Advance past opening quotation mark
		i++;
		// Find index of closing quotation mark, if it exists
		int x = line.indexOf('"', i);
		String element;
		if(x == -1) {
			// This is the last element
			element = line.substring(i);
		}
		else {
			// This is the next element
			element = line.substring(i, x);
			// Advance past closing quotation mark
			i = x + 1;
			char a = line.charAt(i);
			if(a == ',') {
				// Advance past comma
				i++;
			}
			else {
				// This is the last element
				x = -1;
			}
		}
		// Add element to logical end of array
		column[r++] = element;
		return x==-1;
	}

}