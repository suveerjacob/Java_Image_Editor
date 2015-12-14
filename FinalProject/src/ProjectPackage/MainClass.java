package ProjectPackage;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


// Also Available on Github
public class MainClass extends JFrame{
	ImagePanel imagePanel;
	public MainClass() {
		imagePanel = new ImagePanel();
		setLayout(new BorderLayout(10,10));
		add(imagePanel, BorderLayout.WEST);
		add(new ButtonPanel(imagePanel,this), BorderLayout.EAST);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		JFrame f = new MainClass();
		f.setTitle("Employee Details");
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		
	}//

}
