import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class LibraryFrame extends JFrame
{
	public LibraryFrame()
	{
		setTitle("Library Lending System");
		setResizable(true);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerWindow(this);
		setLocationRelativeTo(null);
		setSize(850,450);
		
		try
		{
		UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		getContentPane().add(new LibraryGUI());
		
		setVisible(true);

	}
	
	private void centerWindow(Window w)
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);	
	}
}