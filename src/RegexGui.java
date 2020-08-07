import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class RegexGui {
	
	class RegexComboBox extends JComboBox<String>
	{
		Converter converter=new Converter();		
		JComboBox combo=new JComboBox();
		private Creator creator;
		private JTextField textField;
		private Tester tester=new Tester();
		
		private int comboIndex;
		
		//String[] regexModel= {"Starts with","Ends with","Numerical"};
		
		
		
		public RegexComboBox(JTextField regexOutput,Creator creator,int comboIndex,JTextArea test)
		{
			super();
			this.creator=creator;
			this.textField=regexOutput;
			String[] regexModel;
			if(comboIndex==0)
			{
				regexModel=creator.getKeys(0);
			}
			else
			{
				regexModel=creator.getKeys(1);
			}
			System.out.println("ComboIndex:"+comboIndex);
			DefaultComboBoxModel<String> comboModel=new DefaultComboBoxModel<String>(regexModel);
			setModel(comboModel);
			creator.changeElement(creator.getElement(getSelectedItem().toString()),comboIndex);
			textField.setText(creator.getOutput());		
			
		
			addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					System.out.println("***************");
					creator.changeElement(creator.getElement(getSelectedItem().toString()),comboIndex);
					textField.setText(creator.getOutput());
					tester.tester(regexOutput.getText(), test);
				}
				
			}
			);
		}
		
	}
	
		
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private JFrame frame;
	private JTextField regexOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegexGui window = new RegexGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegexGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<RegexComboBox> comboContainer=new ArrayList<RegexComboBox>();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 677);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new MigLayout("", "[600px:600px,grow,center]", "[][grow]"));
		
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(Color.GRAY);
		mainPanel.add(upperPanel, "cell 0 0,grow");
		upperPanel.setLayout(new MigLayout("", "[600px:600px,grow,fill]", "[240.00][30px:30px:30px]"));
		
		JPanel upperUpperPanel = new JPanel();
		upperUpperPanel.setBackground(Color.YELLOW);
		upperPanel.add(upperUpperPanel, "flowx,cell 0 0,alignx left,aligny top");
		upperUpperPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		upperUpperPanel.setLayout(new GridLayout(0, 4, 0, 10));
		
		JPanel upperLowerPanel = new JPanel();
		upperPanel.add(upperLowerPanel, "cell 0 1,growx,aligny center");
		upperLowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAdd = new JButton("ADD");
		upperLowerPanel.add(btnAdd);
		
		JButton btnRemove = new JButton("REMOVE");
		upperLowerPanel.add(btnRemove);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		upperPanel.add(comboBox, "cell 0 0");
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.add(lowerPanel, "cell 0 1,grow");
		lowerPanel.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		regexOutput = new JTextField();
		lowerPanel.add(regexOutput, "cell 0 1,growx");
		regexOutput.setColumns(10);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		Creator creator=new Creator(regexOutput);
		
		JTextArea textAreaTest = new JTextArea();
		textAreaTest.setText("1234 asdfasdf\r\nabcda 123123\r\n@adfasdf");
		lowerPanel.add(textAreaTest, "cell 0 2,grow");
		
		
		
		btnAdd.addMouseListener(new MouseAdapter() 
		{
			int comboIndex=0;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				RegexComboBox newCombo=new RegexComboBox(regexOutput,creator,comboIndex,textAreaTest);
				upperUpperPanel.add(newCombo);
				comboContainer.add(newCombo);				
				upperUpperPanel.updateUI();
				comboIndex++;	
			}
		});
		
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(comboContainer.size()!=0)
				{
				System.out.println(comboContainer.size());
				System.out.println(upperUpperPanel.countComponents());
				upperUpperPanel.remove(comboContainer.size()-1);
				comboContainer.remove(comboContainer.size()-1);
				//remove(comboContainer.size());
				creator.removeElement(creator.getPattern().size()-1);
				regexOutput.setText(creator.getOutput());
				upperUpperPanel.updateUI();
				}
				else
				{
					System.out.println("NO object to remove!");
				}
			}
		});
	}

}
