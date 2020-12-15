package dr_gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JWindow;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JDialog;



public class QAwindow {
	
	private JDialog frame;

	
	private JPanel panel;
	
	
	private JPanel yesAnswer;

	
	private ButtonGroup radioButtonGroup;
	

	private static Point windowLocation;
	

	public QAwindow(String question, String[] answers) {

		frame = new JDialog((JWindow)null, (String) question);

		frame.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		frame.setSize(350, 300);
		frame.setLocationRelativeTo(null); //okno na srodku ekranu
		if (windowLocation != null) {
			frame.setLocation(windowLocation);
		}
		panel = new JPanel();
		yesAnswer = new JPanel();
		
		if (answers[0] != "yes") {
			Border border = BorderFactory.createTitledBorder("Yes");
			yesAnswer.setBorder(border);
		}
		// Set the layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(30, 30, 20, 20));  // jest przerwa pomiêdzy okinkiem a tekstem
		yesAnswer.setLayout(new BoxLayout(yesAnswer, BoxLayout.Y_AXIS));

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		radioButtonGroup = new ButtonGroup();

		addQuestionLabel(question);
		panel.add(yesAnswer);

		for (String answer : answers) {
			addRadioButton(answer);
		}
		createConfirmButton();
		frame.setVisible(true);
	}

	
	public String getSelectedOption() {
		for (Enumeration<AbstractButton> buttons = radioButtonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
	                return button.getText();
			}
		}
		return null;
	}


	private void addQuestionLabel(String question) {
		JLabel label = new JLabel((String)question);
		panel.add(label);
	}


	private void addRadioButton(String text) {
		JRadioButton jRadioButton1 = new JRadioButton();
		jRadioButton1.setText((String)text); 
		radioButtonGroup.add(jRadioButton1);
		if (text == "no") {
			panel.add(jRadioButton1);
		}
		else {
			yesAnswer.add(jRadioButton1);
		}
		jRadioButton1.setSelected(true);
	}


	private void createConfirmButton() {
		JButton confirmButton = new JButton("Next");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowLocation = frame.getLocation();
				frame.setVisible(false);
			}
		});
		panel.add(confirmButton,BorderLayout.CENTER);
	}
	

}