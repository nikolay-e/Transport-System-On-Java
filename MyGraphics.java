import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MyGraphics {
	protected static MyPanel panel = new MyPanel();
	protected static MyFrame frame;
	protected static Coordinates mapCenter = new Coordinates();

	protected static double scale;
	protected static double horizontalShift;
	protected static double verticalShift;

	public static void runGUI() {
		while (true) {
			panel.repaint();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

    public static void initFrame() {
		frame = new MyFrame(500, 500, panel);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		SwingUtilities.updateComponentTreeUI(frame);
	}

	public static void init () {
		initFrame();


	}

}