package dr_gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class BookWindow {
	
	private JDialog frame;

	private JPanel panel;

	private JPanel[] bookPanel;


	private static Point windowLocation;

	public BookWindow(String[] booksData) {
		int booksNumber = booksData.length;
		String windowTitle;
		if (booksNumber == 1) {
			windowTitle = "Book recommended for you:";
		} else {
			windowTitle = "Books recommended for you:";
		}
		frame = new JDialog((JWindow)null, windowTitle);
		frame.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		frame.setSize(350, 300);
		frame.setLocationRelativeTo(null);
		if (windowLocation != null) {
			frame.setLocation(windowLocation);
		}
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20)); 

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		JLabel windowTitleLabel = new JLabel(windowTitle);
		panel.add(windowTitleLabel,BorderLayout.CENTER);
		bookPanel = new JPanel[booksNumber];
		for (int i = 0; i < booksNumber; ++i) {
			bookPanel[i] = new JPanel();
			bookPanel[i].setLayout(new BoxLayout(bookPanel[i], BoxLayout.Y_AXIS));
			String singleBookShort = booksData[i];
			addBook(singleBookShort, i);

			panel.add(bookPanel[i]);
		}
		createConfirmButton();
		frame.setVisible(true);
	}
	
	
	private void addBook(String bookShort, int index) {

		Border border = BorderFactory.createTitledBorder("");
		bookPanel[index].setBorder(border);
		JLabel bookLabel = new JLabel(bookShort);
		bookPanel[index].add(bookLabel);

	}

	private void createConfirmButton() {
		JButton confirmButton = new JButton("OK");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowLocation = frame.getLocation();
				frame.setVisible(false);
			}
		});
		panel.add(confirmButton,BorderLayout.CENTER);
	}
}

