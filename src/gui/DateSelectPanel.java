package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

public class DateSelectPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Spinners for the start date
	private JSpinner dayS;
	private JSpinner monthS;
	private JSpinner yearS;
	
	//Sprinners for the final date
	private JSpinner dayF;
	private JSpinner monthF;
	private JSpinner yearF;
	
	
	public DateSelectPanel () {
		
		BoxLayout layout= new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(layout);
		TitledBorder title = 
				BorderFactory.createTitledBorder("Date Selection");
		this.setBorder(title);
		
		//Different panels for start and final date
		JPanel top = new JPanel();
		JPanel bot = new JPanel();
		BorderLayout topLayout= new BorderLayout();
		BorderLayout botLayout= new BorderLayout();
		top.setLayout(topLayout);
		bot.setLayout(botLayout);
		this.add(top);
		this.add(bot);
				
		/*
		 * Spinners creation...
		 */
		SpinnerModel modelDayS =
				new SpinnerNumberModel(1, //initial value
				1, //min
				31, //max
				1); //step
		SpinnerModel modelMonthS = new SpinnerNumberModel(1, 1, 12, 1); 
		SpinnerModel modelYearS = new SpinnerNumberModel(2000, 2000, 2014, 1);
		
		SpinnerModel modelDayF =
				new SpinnerNumberModel(1, //initial value
				1, //min
				31, //max
				1); //step
		SpinnerModel modelMonthF = new SpinnerNumberModel(1, 1, 12, 1); 
		SpinnerModel modelYearF = new SpinnerNumberModel(2000, 2000, 2014, 1); 
		
		dayS = new JSpinner(modelDayS);
		monthS = new JSpinner(modelMonthS);
		yearS = new JSpinner(modelYearS);
		dayF = new JSpinner(modelDayF);
		monthF = new JSpinner(modelMonthF);
		yearF = new JSpinner(modelYearF);
		
		top.add(dayS,BorderLayout.EAST);
		top.add(monthS,BorderLayout.CENTER);
		top.add(yearS,BorderLayout.WEST);
		
		bot.add(dayF,BorderLayout.EAST);
		bot.add(monthF,BorderLayout.CENTER);
		bot.add(yearF,BorderLayout.WEST);		
		
		this.setPreferredSize(new Dimension(200,75));

	}

}
