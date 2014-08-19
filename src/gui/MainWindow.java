package gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataPanel data;
	private ChartsPanel chart;
	private FinancialElementSelectPanel fesp;
	private JPanel optionsPanel;
	private DateSelectPanel date;
	   
	public MainWindow() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BorderLayout layout = new BorderLayout();
		chart = new ChartsPanel();
		data = new DataPanel();
		fesp = new FinancialElementSelectPanel ();
		optionsPanel = new JPanel();
		date = new DateSelectPanel();
		
		/*
		 * BoxLayout: A layout manager that allows multiple components 
		 * to be laid out either vertically or horizontally
		 */
		BoxLayout optionsPanelLayout = 
				new BoxLayout(optionsPanel, BoxLayout.Y_AXIS);
		optionsPanel.setLayout(optionsPanelLayout);
		
		data.updateList();
		
		this.getContentPane().setLayout(layout);
		this.getContentPane().add(optionsPanel,	BorderLayout.WEST);
		this.getContentPane().add(chart,BorderLayout.CENTER);
	
		this.setSize(800,600);
		
		optionsPanel.add(fesp);
		optionsPanel.add(date);
		optionsPanel.add(data);
		
		this.setVisible(true);
	}


    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }

}
