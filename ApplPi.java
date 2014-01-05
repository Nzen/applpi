// Decompiled from classes via JD-Gui: http://jd.benow.ca/

import com.github.hollingsworthd.applpi.gui.Main;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class ApplPi extends JFrame
{
	private static final long serialVersionUID = 1L;

public static void main(String[] args)
  {
	EventQueue.invokeLater( new Runnable()
	{
		public void run()
		{
			try
			{
				ApplPi frame = new ApplPi();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	} );
  }

  private ApplPi() {
	Main view = new Main();
	getContentPane().add(view);
	setTitle("ApplPi 3.2 sourceforge.net/projects/applpi c:2009-13 D Hollingsworth");
	setSize(900, 160);
	setDefaultCloseOperation(3);
	setVisible(true);
  }
}