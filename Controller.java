import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.JOptionPane;

// A controller is an artificial class used to manage
// communication among objects -- in this case, it separates
// the user interface from the remaining objects.
public class Controller 
{
	LibraryCollection itemsOnFile = new LibraryCollection();
	BorrowerCollection borrowersOnFile = new BorrowerCollection();	
		
	// Return catalog listing
	public ArrayList <String>getCatalog()
	{
		// This is a test case for one item only
		Vector <String> keys = new Vector <String> (itemsOnFile.collection.keySet());
		Collections.sort(keys);
		
		ArrayList <String> catalogList = new ArrayList <String>();
		for (Enumeration <String> e = keys.elements(); e.hasMoreElements();)
		{
			Item item = itemsOnFile.getItem(e.nextElement());
			catalogList.add(item.ID + ": " +item.name + " [" + item.quantity + "]");
		}
			
		return catalogList;
	}
	
	//checking out an item
	public String checkOut(String d, String b, String i)
	{		
		// Declare variables
		Boolean validBorrower;
		LibraryCollection myItems = new LibraryCollection();
		BorrowerCollection borrowers = new BorrowerCollection();
		String date = d;
		String borrowerID = b;
		String itemID = i;

			validBorrower = false; 
			while (!validBorrower)
			{
					//borrowerID = borrowerIn;
					if(borrowers.borrowerList.containsKey(borrowerID))
					{
						validBorrower = true;
					}
					else 
					{
						validBorrower = false;			
						borrowerID = JOptionPane.showInputDialog(null, "Invalid Borrower ID", "");
						
					}			
			}
			
				boolean validItem = false; 
				while (!validItem)
				{			
						//itemID = itemIn; 
						Item myItem = itemsOnFile.getItem(itemID);
						
						if(myItems.collection.containsKey(itemID))
						{
							if(myItem.quantity == 1 && borrowers.borrowerList.containsKey(borrowerID))
							{
								validItem = true; 
								myItem.quantity--;
								myItem.borrower = borrowerID;
								
								Borrower borrower = borrowersOnFile.getBorrower(borrowerID);
								borrower.history.addRecordInfo(date, itemID, Record.CHECKOUT);
								
								return "\nThe Item has successfully been checked out.";
							}
							else
							if(myItem.quantity == 0)
							{
								validItem = true; 
								JOptionPane.showMessageDialog(null, "That item is already checked out.");
							}
						}
						else
						if(!myItems.collection.containsKey(itemID))
						{
							validItem = true;
							JOptionPane.showMessageDialog(null, "Invalid item.");
							 
						}
				}
		return "Check Out complete.";
	}	

	
	//returning an item
	public String returnItem(String d, String i)
	{		
		//variables
		Boolean validItem;
		LibraryCollection myItems = new LibraryCollection();
		String itemID = i;
		String date = d;

			validItem = false;
			while (!validItem)
			{				
				try
				{
					Item myItem = itemsOnFile.getItem(itemID);
	
					if(myItems.collection.containsKey(itemID))
					{
						if(myItem.quantity == 0)
						{
							validItem = true; 
							myItem.quantity++;
							Borrower borrower = borrowersOnFile.getBorrower(myItem.borrower);
							borrower.history.addRecordInfo(date, itemID, Record.RETURNS);
							return "\nThe Item has successfully been returned." ;
						}
						else
						if(myItem.quantity == 1)
						{
							validItem = true;
							JOptionPane.showMessageDialog(null, "That item is already in stock.\nReturning to main menu.");
						}
					}
					else
					if(!myItems.collection.containsKey(itemID))
					{
							validItem = true;
							JOptionPane.showMessageDialog(null, "Invalid item.");
					}
				}
				catch(Exception e)
				{

				}

			 }

		return "";
	}
		
	boolean checkBorrower(String borrowerID)
	{
		return borrowersOnFile.borrowerList.containsKey(borrowerID);
	}
	boolean checkItemCode(String itemCode)
	{
		return itemsOnFile.collection.containsKey(itemCode);
	}

	public String history(String ID)
	{
		Borrower borrower = borrowersOnFile.getBorrower(ID);
		borrower.history.printRecord(ID);		
		
		return borrower.history.printRecord(ID);
	}

}
