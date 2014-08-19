package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataPanel data;
	private ChartsPanel chart;
	   
	public MainWindow() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BorderLayout layout = new BorderLayout();
		chart = new ChartsPanel();
		data = new DataPanel();
		
		data.updateList();
		
		this.getContentPane().setLayout(layout);
		this.getContentPane().add(data,	BorderLayout.WEST);
		this.getContentPane().add(chart,BorderLayout.EAST);
		
		this.setVisible(true);
		this.setSize(800,400);
	}


    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }

}
