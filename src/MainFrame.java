import java.awt.Toolkit;

import javax.swing.JFrame;


public class MainFrame {
	public MainFrame(JFrame f,int TABLE_WIDTH,int TABLE_HETGHT){
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		f.setLocation((width-TABLE_WIDTH)/2, (height-TABLE_HETGHT)/2);		
	}
}