import java.util.HashMap;

// A collection of library items
public class BorrowerCollection 
{
	HashMap <String, Borrower> borrowerList = new HashMap <String, Borrower>();
	
	BorrowerCollection()
	{
		// Borrowers registered with the library
		Borrower borrower = new Borrower(
		"001",
		"D. Manson");
		borrowerList.put(borrower.ID, borrower);
	
		Borrower borrower2 = new Borrower(
		"002",
		"Z. Ma");
		borrowerList.put(borrower2.ID, borrower2);
		
		Borrower borrower3 = new Borrower(
		"003",
		"S. Curl");
		borrowerList.put(borrower3.ID, borrower3);
	}
	
	// Return an item from the collection
	Borrower getBorrower(String ID)
	{
		return borrowerList.get(ID);
	}

}
