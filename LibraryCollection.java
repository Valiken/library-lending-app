import java.util.HashMap;

public class LibraryCollection
{
	public HashMap<String, Item> collection = new HashMap <String, Item>();
	
	LibraryCollection()
	{
		// Books in the library
		Book b1 = new Book(
		"HPDH-1",
		"Harry Potter and the Deathly Hallows",
		"Wizarding",
		"J.K. Rowling",
		"Book");
		b1.quantity = 1;		
		collection.put(b1.ID, b1);
		
		Book b2 = new Book(
		"HPDH-2",
		"Harry Potter and the Deathly Hallows",
		"Wizarding",
		"J.K. Rowling",
		"Book");
		b2.quantity = 1;
		collection.put(b2.ID, b2);
		
		Book b3 = new Book(
		"HPDH-3",
		"Harry Potter and the Deathly Hallows",
		"Wizarding",
		"J.K. Rowling",
		"Book");
		b3.quantity = 1;
		collection.put(b3.ID, b3);
		
		Book b4 = new Book(
		"PL-1",
		"Pirate Latitudes",
		"The Caribbean, 1665",
		"Michael Crichton",
		"Book");
		b4.quantity = 1;		
		collection.put(b4.ID, b4);	
		
		Book b5 = new Book(
		"CSH-1",
		"The Complete Sherlock Holmes",
		"Who Done it",
		"Sir Arthur Conan Doyle",
		"Book");
		b5.quantity = 1;		
		collection.put(b5.ID, b5);
		
		Book b6 = new Book(
		"CSH-2",
		"The Complete Sherlock Holmes",
		"Who Done it",
		"Sir Arthur Conan Doyle",
		"Book");
		b6.quantity = 1;
		collection.put(b6.ID, b6);
		
		//Music in the inventory
		Music m1 = new Music(
		"HWGA-1",
		"Here We Go Again",
		"Music",
		"Wynton Marsalis",
		"blues",
		"MP3 download");
		m1.quantity = 1;		
		collection.put(m1.ID, m1);
		
		Music m2 = new Music(
		"HWGA-2",
		"Here We Go Again",
		"Music",
		"Wynton Marsalis",
		"blues",
		"MP3 download");
		m2.quantity = 1;				
		collection.put(m2.ID, m2);

		Music m3 = new Music(
		"HWGA-3",
		"Here We Go Again",
		"Music",
		"Wynton Marsalis",
		"blues",
		"MP3 download");
		m3.quantity = 1;				
		collection.put(m3.ID, m3);

		
		Music m4 = new Music(
		"MC-1",
		"McCartney",
		"Music",
		"Paul McCartney",
		"rock",
		"CD");
		m4.quantity = 1;				
		collection.put(m4.ID, m4);
		
		Music m5 = new Music(
		"MC-2",
		"McCartney",
		"Music",
		"Paul McCartney",
		"rock",
		"CD");
		m5.quantity = 1;		
		collection.put(m5.ID, m5);
		
		Music m6 = new Music(
		"LZ2-1",
		"Led Zeppelin 2",
		"Music",
		"Led Zeppelin",
		"hard rock",
		"CD");
		m6.quantity = 1;			
		collection.put(m6.ID, m6);

		Music m7 = new Music(
		"LZ2-2",
		"Led Zeppelin 2",
		"Music",
		"Led Zeppelin",
		"hard rock",
		"CD");
		m7.quantity = 1;				
		collection.put(m7.ID, m7);

		Music m8 = new Music(
		"TO-1",
		"Time Out",
		"Music",
		"The Dave Brubeck Quartet",
		"jazz",
		"CD");
		m8.quantity = 1;		
		collection.put(m8.ID, m8);

		Music m9 = new Music(
		"EOMS-1",
		"Exile On Main Street",
		"Music",
		"The Rolling Stones",
		"blues rock",
		"CD");
		m9.quantity = 1;				
		collection.put(m9.ID, m9);
		
		Music m10 = new Music(
		"EOMS-2",
		"Exile On Main Street",
		"Music",
		"The Rolling Stones",
		"blues rock",
		"CD");
		m10.quantity = 1;				
		collection.put(m10.ID, m10);
	}
	
	// Return an item from the collection
	Item getItem(String ID)
	{
		return collection.get(ID);
	}

}