package name.lourie.kevin;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * 
 */
public class EmployeeDialog extends JDialog {

	/** */
	private static final long serialVersionUID = 1L;
	private Label label;
	private Button button;
	private TextField tfName;
	private TextField tfEID;
	private TextField tfAddress;
	private TextField tfPhoneNumber;
	private TextField tfDOB;
	private TextField tfSecurityClearance;
	private Employee employee;
	private EmployeeList employeeList;

	EmployeeDialog(JFrame jf, EmployeeList aEmployeeList, Employee aEmployee) {
		super(jf, true);
		// Needed by updateFields to fill TextFields
		employee = aEmployee;
		employeeList = aEmployeeList;
		setTitle("Information");
		setLayout(new FlowLayout());
		setSize(400, 500);
		label = new Label("Name");
		add(label);
		tfName = new TextField("", 20);
		add(tfName);
		label = new Label("EID");
		add(label);
		tfEID = new TextField("", 8);
		add(tfEID);
		label = new Label("Address");
		add(label);
		tfAddress = new TextField("", 30);
		add(tfAddress);
		label = new Label("Phone Number");
		add(label);
		tfPhoneNumber = new TextField("", 10);
		add(tfPhoneNumber);
		label = new Label("DOB");
		add(label);
		tfDOB = new TextField("", 10);
		add(tfDOB);
		label = new Label("Security Clearance");
		add(label);
		tfSecurityClearance = new TextField("", 1);
		add(tfSecurityClearance);
		button = new Button("OK");
		add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				retrieveFields();
			}
		});
		button = new Button("Search");
		add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create employee field
				retrieveFields();
				
				// Search for employee
				employee = employeeList.search(employee);
				if(employee == null) {
					System.out.println("Employee not found");
					return;
				}
				// Fill TextFields
				updateFields();
			}
		});
		updateFields();
		// New window has been created
		setVisible(true);
	}

	/**
	 * Create employee based on the TextFields
	 */
	public void retrieveFields() {
		employee = new Employee();
		employee.setEmployeeName(tfName.getText());
		employee.setEID(tfEID.getText());
		employee.setAddress(tfAddress.getText());
		employee.setPhoneNumber(tfPhoneNumber.getText());

		String startDateString = tfDOB.getText();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate;
		try {
			startDate = df.parse(startDateString);
		} catch (ParseException e) {
			startDate = new Date();
			System.out.println("Defaulting date to today");
		}
		employee.setDOB(startDate);
		employee.setSecurityClearance(tfSecurityClearance.getText());
	}

	public Employee getEmployee() {
		return employee;
	}

	public void updateFields() {
		if(employee == null) {
			return;
		}
		tfName.setText(employee.getEmployeeName());
		tfEID.setText(employee.getEID());
		tfAddress.setText(employee.getAddress());
		tfPhoneNumber.setText(employee.getPhoneNumber());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date = employee.getDOB();
		String dateString = df.format(date);
		tfDOB.setText(dateString);
		tfSecurityClearance.setText(employee.getSecurityClearance());
	}
}
