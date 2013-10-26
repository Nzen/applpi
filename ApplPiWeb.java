// Decompiled from classes via JD-Gui: http://jd.benow.ca/

import com.github.hollingsworthd.applpi.gui.Main;
import java.awt.Container;
import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class ApplPiWeb extends JApplet
{
	private Main view = null;

	public void init()
	{
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					ApplPiWeb.this.createGUI();
				}
			} );
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
	}

	private void createGUI() {
		this.view = new Main();
		getContentPane().add(this.view);
	}

	public void destroy()
	{
		super.destroy();
		this.view.stop();
	}
}