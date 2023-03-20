import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class frontEnd {
	
	public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		
		JFrame frame = new JFrame();
		frame.setSize(1366,768);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		ArrayList<Integer> rgb = new ArrayList<>();
		

		panel.setOpaque(true);
		panel.setSize(1366,768);
		frame.add(panel);
		frame.setVisible(true);
		while(true) {
			//panel.setBackground(new Color(128, 0, 0));
			//panel.setBackground(new Color(139,0,0));
			//panel.setBackground(new Color(165,42,42));
			panel.setBackground(new Color(0,0,0));
			//panel.setBackground(new Color(250,0,0));
			panel.setBackground(new Color(150,120,200));
			
		}
		
		
	}
	
}
