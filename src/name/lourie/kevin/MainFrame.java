package name.lourie.kevin;

// Using AWT's containers and components
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Make a read employee button Create List component Add display to employee
 * list that takes a List component, clears it, and adds all employee strings
 * Call new display method instead of existing display method
 */
public class MainFrame extends JFrame {

	/** */
	private static final long serialVersionUID = 1L;
	EmployeeList employeeList;
	List list;
	Label label;

	MainFrame() throws IOException {
		// Create a panel
		JPanel panel = new JPanel();

		// Set horizontal layout for panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		// Create empty space
		panel.add(Box.createVerticalGlue());

		// Add panel to frame
		add(panel);

		// Create a list of employees
		list = new List(35);

		// Add list to panel
		panel.add(list);

		setTitle("Employees"); // "super" Frame sets title
		setSize(1000, 700); // "super" Frame sets initial size

		employeeList = readEmployees();

		addButtons(panel);

		setVisible(true);
	}

	private void addButtons(JPanel panel) {
		// Create button panel
		JPanel buttonPanel = new JPanel();
		
		// Set vertical for panel
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		// Add button panel to first panel
		panel.add(buttonPanel);

		Button button; 
		button = new Button("New");
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				EmployeeDialog ed = new EmployeeDialog(MainFrame.this, employeeList, null, flag);
				Employee f = ed.getEmployee();
				employeeList.add(f);
				employeeList.display(list);
			}
		});

		button = new Button("Read");
		buttonPanel.add(button);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 0)));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					employeeList = readEmployees();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		button = new Button("Sort by Name");
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByName();
			}
		});
		button = new Button("Sort by EID");
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByEID();
			}
		});
		button = new Button("Sort by Name and EID");
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortByNameAndEID();
			}
		});
		button = new Button("Remove");
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedItem = list.getSelectedIndex();
				if(selectedItem == -1) {
					System.out.println("No employee selected");
					return;
				}
				Employee f = employeeList.getEmployee(selectedItem);
				employeeList.remove(f);
				employeeList.display(list);
				list.select(selectedItem);
			}
		});
		button = new Button("Update");
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedItem = list.getSelectedIndex();
				if(selectedItem == -1) {
					System.out.println("No employee selected");
					return;
				}
				boolean flag = false; 
				Employee f = employeeList.getEmployee(selectedItem);
				EmployeeDialog ed = new EmployeeDialog(MainFrame.this, employeeList, f, flag);
				Employee g = ed.getEmployee();
				f.update(g);
				employeeList.display(list);
				list.select(selectedItem);
			}
		});
		button = new Button("Oldest");
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findOldest();
			}
		});
		button = new Button("Youngest");
		buttonPanel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findYoungest();
			}
		});
	}

	private EmployeeList readEmployees() throws IOException {
		// Read employees from the file
		String path = "Employees.csv";
		File file = new File(path);
		EmployeeList employeeList = new EmployeeList(file);

		// Display all employees from file
		employeeList.display(list);
		return employeeList;
	}

	private void findOldest() {
		// Search for oldest employee
		Employee old = employeeList.findMin(Employee.compareByDOB);
		String employee = old.toString();
		System.out.println("\nOldest employee");
		System.out.println(employee);
	}

	private void findYoungest() {
		// Search for youngest employee
		Employee young = employeeList.findMin(Employee.reverseCompareByDOB);
		String employee = young.toString();
		System.out.println("\nYoungest employee");
		System.out.println(employee);
	}

	private void sortByNameAndEID() {
		// Sort by name and EID
		employeeList.sortByNameandID();
		employeeList.display(list);
	}

	private void sortByEID() {
		// Sort array by EID
		employeeList.sortByID();
		employeeList.display(list);
	}

	private void sortByName() {
		// Sort array by name
		employeeList.sortByName();
		employeeList.display(list);
	}
}