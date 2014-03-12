
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.text.*;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class LibraryGUI extends JPanel implements ActionListener, ListSelectionListener
{
	private Controller ctrl = new Controller();
	private JTextArea viewArea;
	private JScrollPane scroll, listScroll;
	private JToolTip tip;
	private JDialog Dialog;
	private JButton catalogButton, checkOutButton, returnButton, historyButton, clearButton;
	private JTextField borrowerField, dateField;
	private JLabel borrowerLabel, dateLabel;
	private JList list;
	private DefaultListModel model;
	String listItem ="";
	
	private static final LibraryGUI instance = new LibraryGUI();

	public static LibraryGUI getInstance() 
   {
        return instance;
   }
	
	LibraryGUI()
	{
		createButtonPanel();
		createDisplayPanel();
		createInputPanel();
	}
	
	void createButtonPanel()
	{	
		//establish layouts for the panel
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBorder(BorderFactory.createTitledBorder("Controls:"));
		GridLayout grid = new GridLayout(0,1);
		ButtonPanel.setLayout(grid);
		grid.setVgap(5);

		tip = new JToolTip();
		tip.setBackground(Color.YELLOW);
      tip.setForeground(Color.RED);
		
		//create buttons
		catalogButton = new JButton("Catalog")
		{
		  public JToolTip createToolTip() 
		  {
      	  JToolTip tip = super.createToolTip();
      	  tip.setBackground(Color.BLUE);
      	  tip.setForeground(Color.YELLOW);
      	  return tip;
     	 }
  	  };
		
		checkOutButton = new JButton("Check Out");
		returnButton = new JButton("Return");
		historyButton = new JButton("History");
		clearButton = new JButton("Clear");
	
		catalogButton.setToolTipText("View the Catalog");
		checkOutButton.setToolTipText("Check out an item");
		returnButton.setToolTipText("Return an item");
		historyButton.setToolTipText("View borrower history");
		clearButton.setToolTipText("Clear output section");
		
		//enable buttons
		catalogButton.setEnabled(true);
		checkOutButton.setEnabled(true);
		returnButton.setEnabled(true);
		historyButton.setEnabled(true);
		clearButton.setEnabled(true);
		
		//add actionListeners to buttons
		catalogButton.addActionListener(this);
		checkOutButton.addActionListener(this);
		returnButton.addActionListener(this);
		historyButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		//add buttons to panel
		ButtonPanel.add(catalogButton);
		ButtonPanel.add(checkOutButton);
		ButtonPanel.add(returnButton);
		ButtonPanel.add(historyButton);
		ButtonPanel.add(clearButton);
		
		//add panel to frame
		add(ButtonPanel, BorderLayout.WEST);	
	}
	
	void createDisplayPanel()
	{
		//create panel
		JPanel DisplayPanel = new JPanel();
		
		//establish borders and layout
		DisplayPanel.setBorder(BorderFactory.createTitledBorder("Output:"));
		
		//add text and scroll area 
		viewArea = new JTextArea(15,45);
		viewArea.setWrapStyleWord(true);
		scroll = new JScrollPane(viewArea);
		model = new DefaultListModel();
		list = new JList(model);
		listScroll = new JScrollPane(list);
		list.addListSelectionListener(this);
		
		//add to panel
		DisplayPanel.add(scroll);
		DisplayPanel.add(listScroll);
		
		
		//add to frame
		add(DisplayPanel, BorderLayout.CENTER);
	}
	
	void createInputPanel()
	{
		JPanel InputPanel = new JPanel();
		InputPanel.setBorder(BorderFactory.createTitledBorder("Input"));
		
		dateLabel = new JLabel("Date:");
		dateField = new JTextField(10);
		
		borrowerLabel = new JLabel("Borrower:"); 
		borrowerField = new JTextField(10);
		
		InputPanel.add(dateLabel);
		InputPanel.add(dateField);
		InputPanel.add(borrowerLabel);
		InputPanel.add(borrowerField);
		
		add(InputPanel, BorderLayout.SOUTH);
	}
	
	public void valueChanged(ListSelectionEvent event)
	{
		listItem = list.getSelectedValue().toString();
		String[] itemFields = listItem.split(":");
		JOptionPane.showMessageDialog(null, "You selected code: " + itemFields[0]);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		
		if (source == catalogButton)
		{
			// Get catalog details -- add other component fields as necessary
			// to complete the required arguments
			ArrayList <String> myList = ctrl.getCatalog();
			for (String s : myList)
			{
				model.addElement(s);
			}
		}
		else
		if (source == checkOutButton)
		{
		
		String date = dateField.getText();
		String borrower = borrowerField.getText();
		
		listItem = list.getSelectedValue().toString();
		String[] itemFields = listItem.split(":");
				
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);

		boolean valid = false; // date error checking
		while(!valid)
		{		
			
			try
			{
				sdf.parse(date);
				valid = true;
			}
			catch(Exception e) 
			{
				date = JOptionPane.showInputDialog(null, 
														"The date is incorrect.\nPlease enter a valid date.", 
														"", 
														JOptionPane.ERROR_MESSAGE);
			}
		}
		
			viewArea.append(ctrl.checkOut(date, borrower, itemFields[0]));
		}
		else
		if (source == returnButton)
		{
			String date = dateField.getText();
		
			listItem = list.getSelectedValue().toString();
			String[] itemFields = listItem.split(":");
					
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				sdf.setLenient(false);
		
			boolean valid = false; // date error checking
			while(!valid)
			{		
				try
				{
					sdf.parse(date);
					valid = true;
				}
				catch(Exception e) 
				{
					date = JOptionPane.showInputDialog(null, 
														"The date is incorrect.\nPlease enter a valid date.", 
														"", 
														JOptionPane.ERROR_MESSAGE);
				}
			}
			viewArea.append(ctrl.returnItem(date, itemFields[0]));
		}
		else
		if (source == historyButton)
		{
				String borrowerID = borrowerField.getText();

				if (ctrl.checkBorrower(borrowerID))
				{
					Borrower borrower = ctrl.borrowersOnFile.getBorrower(borrowerID);
					viewArea.append("\n\nBorrower History for ID: " + borrowerID + "\nName: " + borrower.name);
					viewArea.append(ctrl.history(borrowerID));						
				}
				else
				if(!ctrl.checkBorrower(borrowerID))
				{
					JOptionPane.showMessageDialog(null, "Sorry Invalid ID \n Returning to main menu.");
				}
			
		}
		else
		if(source == clearButton)
		{
			viewArea.setText("");
		}

	}	
	
}