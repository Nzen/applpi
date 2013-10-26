// Decompiled from classes via JD-Gui: http://jd.benow.ca/

import com.github.hollingsworthd.applpi.gui.Main;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ApplPi extends JFrame
{
  public static void main(String[] args)
  {
	try
	{
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				new ApplPi();
			}
		} );
	}
	catch (Exception e) {
	  e.printStackTrace();
	}
  }

  private ApplPi() {
	Main view = new Main();
	getContentPane().add(view);
	setTitle("ApplPi#3.1 sourceforge.net/projects/applpi (c) 2009-2013 Daniel Hollingsworth");
	setSize(600, 80);
	setDefaultCloseOperation(3);
	setVisible(true);
  }
}