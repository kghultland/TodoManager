import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import java.awt.event.*;

public class TodoUI {

	private static TodoItemList tIL;

	private JFrame frame;
	private Container contentPane;
	private static JList<TodoItem> listbox;
	private static DefaultListModel<TodoItem> listModel;

	private JButton addButton;
	private JButton searchButton;
	private static JButton nextButton;
	private JButton editButton;
	private JButton sortButton;
	private JButton doneButton;
	private JButton deleteButton;
	private JButton removeDoneButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton exitButton;

	private static JTextField title = new JTextField();
	private static JTextField body = new JTextField();
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static JFormattedTextField date = new JFormattedTextField(format);
	
	//private static JTextField search = new JTextField();

	private final static JComponent[] inputs = new JComponent[] { new JLabel("Uppgift"), title, new JLabel("Information"), body,
			new JLabel("Tidpunkt"), date };
	
	private final static JComponent[] exitDialog = new JComponent[] {new JLabel("Do you want to save your changes?") };
	
	private static String searchText="";
	
	public TodoUI(TodoItemList t) {
		super();

		tIL = t;

		// Initialise window
		frame = new JFrame("Todo system v1.0");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new MyWindowsListener());

		frame.setLocation(400, 200);
		frame.setSize(850, 600);

		// Fetch the window ContentPane and set its layout
		contentPane = frame.getContentPane();

		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);

		// Initialise the JList and add it to the ContentPane
		listModel = new DefaultListModel<TodoItem>();

		for (int i = 0; i < tIL.size(); i++) {
			listModel.addElement(tIL.getTodoList().get(i));
		}

		listbox = new JList<TodoItem>(listModel);

		listbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listbox.setLayoutOrientation(JList.VERTICAL);
		listbox.setVisibleRowCount(-1);
		listbox.addMouseListener(new MyMouseListener());
		
		
		listbox.setCellRenderer(new TodoItemRenderer());

		JScrollPane listScroller = new JScrollPane(listbox);
		listScroller.setPreferredSize(new Dimension(650, 550));

		SpringLayout.Constraints listCons = layout.getConstraints(listScroller);
		listCons.setX(Spring.constant(5));
		listCons.setY(Spring.constant(5));
		contentPane.add(listScroller);

		addButton = new JButton("ADD");
		addButton.addActionListener(new ActionAdd());

		SpringLayout.Constraints addCons = layout.getConstraints(addButton);
		addCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		addCons.setY(Spring.constant(5));
		addCons.setWidth(Spring.constant(150));
		contentPane.add(addButton);
		
		searchButton = new JButton("SEARCH");
		searchButton.addActionListener(new ActionSearch());
		
		SpringLayout.Constraints searchCons = layout.getConstraints(searchButton);
		searchCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		searchCons.setY(Spring.sum(Spring.constant(25), addCons.getConstraint(SpringLayout.SOUTH)));
		searchCons.setWidth(Spring.constant(150));
		contentPane.add(searchButton);
		
		nextButton = new JButton("FIND NEXT");
		nextButton.addActionListener(new ActionNext());
		
		SpringLayout.Constraints nextCons = layout.getConstraints(nextButton);
		nextCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		nextCons.setY(Spring.sum(Spring.constant(10), searchCons.getConstraint(SpringLayout.SOUTH)));
		nextCons.setWidth(Spring.constant(150));
		contentPane.add(nextButton);		
		

		editButton = new JButton("EDIT");
		editButton.addActionListener(new ActionEdit());

		SpringLayout.Constraints editCons = layout.getConstraints(editButton);
		editCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		editCons.setY(Spring.sum(Spring.constant(25), nextCons.getConstraint(SpringLayout.SOUTH)));
		editCons.setWidth(Spring.constant(150));
		contentPane.add(editButton);

		sortButton = new JButton("SORT");
		sortButton.addActionListener(new ActionSort());

		SpringLayout.Constraints sortCons = layout.getConstraints(sortButton);
		sortCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		sortCons.setY(Spring.sum(Spring.constant(10), editCons.getConstraint(SpringLayout.SOUTH)));
		sortCons.setWidth(Spring.constant(150));
		contentPane.add(sortButton);

		doneButton = new JButton("TOGGLE DONE");
		doneButton.addActionListener(new ActionToggle());

		SpringLayout.Constraints doneCons = layout.getConstraints(doneButton);
		doneCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		doneCons.setY(Spring.sum(Spring.constant(25), sortCons.getConstraint(SpringLayout.SOUTH)));
		doneCons.setWidth(Spring.constant(150));
		contentPane.add(doneButton);

		deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(new ActionDelete());

		SpringLayout.Constraints deleteCons = layout.getConstraints(deleteButton);
		deleteCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		deleteCons.setY(Spring.sum(Spring.constant(25), doneCons.getConstraint(SpringLayout.SOUTH)));
		deleteCons.setWidth(Spring.constant(150));
		contentPane.add(deleteButton);

		removeDoneButton = new JButton("REMOVE DONE");
		removeDoneButton.addActionListener(new ActionRemove());

		SpringLayout.Constraints removeCons = layout.getConstraints(removeDoneButton);
		removeCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		removeCons.setY(Spring.sum(Spring.constant(10), deleteCons.getConstraint(SpringLayout.SOUTH)));
		removeCons.setWidth(Spring.constant(150));
		contentPane.add(removeDoneButton);

		loadButton = new JButton("LOAD");
		loadButton.addActionListener(new ActionLoad());

		SpringLayout.Constraints loadCons = layout.getConstraints(loadButton);
		loadCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		loadCons.setY(Spring.sum(Spring.constant(25), removeCons.getConstraint(SpringLayout.SOUTH)));
		loadCons.setWidth(Spring.constant(150));
		contentPane.add(loadButton);

		saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionSave());

		SpringLayout.Constraints saveCons = layout.getConstraints(saveButton);
		saveCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		saveCons.setY(Spring.sum(Spring.constant(10), loadCons.getConstraint(SpringLayout.SOUTH)));
		saveCons.setWidth(Spring.constant(150));
		contentPane.add(saveButton);

		exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionExit());

		SpringLayout.Constraints exitCons = layout.getConstraints(exitButton);
		exitCons.setX(Spring.sum(Spring.constant(10), listCons.getConstraint(SpringLayout.EAST)));
		exitCons.setY(Spring.sum(Spring.constant(25), saveCons.getConstraint(SpringLayout.SOUTH)));
		exitCons.setWidth(Spring.constant(150));
		contentPane.add(exitButton);
		
		SwingUtilities.getRootPane(nextButton).setDefaultButton(nextButton);
		
		frame.setVisible(true);
	}

 
	
	static class MyWindowsListener implements WindowListener {
		
		@Override
		public void windowOpened(WindowEvent e) {
	
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			doExit();				
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			
		}
	}
	
	static class MyMouseListener implements MouseListener {
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
		
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {

			int clickCount = e.getClickCount();
			
			if (clickCount==2) {	
				doEdit();					
			}
		}
	}	
	
	
	
	static class ActionExit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doExit();
		}
	}

	static class ActionSort implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doSort();
		}
	}

	static class ActionToggle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doToggle();
		}
	}

	static class ActionRemove implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doRemove();
		}
	}

	static class ActionDelete implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doDelete();
		}
	}

	static class ActionLoad implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doLoad();
		}
	}

	static class ActionSave implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doSave();
		}
	}

	static class ActionEdit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doEdit();
		}
	}

	static class ActionAdd implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doAdd();
		}
	}
	
	static class ActionSearch implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doSearch();
		}
	}
	
	static class ActionNext implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doNext();
		}
	}
	
	private static void updateList() {
		listModel.removeAllElements();
		for (TodoItem todo : tIL.getTodoList()) {
			listModel.addElement(todo);
		}
	}
	
	//@SuppressWarnings("serial")
	public class TodoItemRenderer extends JLabel implements ListCellRenderer<TodoItem> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TodoItemRenderer() {
			setOpaque(true);
		}

		
		// listbox.setFont(new Font("monospaced",Font.BOLD,14));
		@Override
		public Component getListCellRendererComponent(JList<? extends TodoItem> list, TodoItem value, int index,
				boolean isSelected, boolean cellHasFocus) {

			Date now = new Date();
			Date event = value.getDueDateTime();

			setFont(new Font("monospaced",Font.BOLD,15));
			setText(value.toString());

			if (now.compareTo(event) > 0) {
				setForeground(Color.RED);
			} else {
				setForeground(list.getForeground());
			}

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				// setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				// setForeground(list.getForeground());
			}

			return this;
		}
	} 
	
	private static void doEdit() {
		Date enteredDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		TodoItem selected = listbox.getSelectedValue();
		if (selected != null) {
			title.setText(selected.getTitle());
			body.setText(selected.getBody());
			date.setText(selected.getDueDateTimeAsString().toString());

			int result = JOptionPane.showConfirmDialog(null, inputs, "Ändra TodoItem",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == 0) {
				try {
					enteredDate = df.parse(date.getText());
					selected.setTitle(title.getText());
					selected.setBody(body.getText());
					selected.setDueDateTime(enteredDate);
					updateList();
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Invalid Date and/or Time");
					return;
				}
			}
		}
	}
	
	private static void doExit() {
		int result=JOptionPane.showConfirmDialog(null, exitDialog, "Exit program",
				JOptionPane.YES_NO_CANCEL_OPTION);
		switch(result) {
		case JOptionPane.YES_OPTION:
			doSave();
			break;
		case JOptionPane.NO_OPTION:
			break;
		case JOptionPane.CANCEL_OPTION:
			return;
		}	
		System.exit(0);
	}
	
	private static void doSave() {
		tIL.serializeToXML("todoList.xml");
	}
	
	private static void doAdd() {
		Date enteredDate;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String newTitle;
		String newBody;

		title.setText("");
		body.setText("");
		date.setText(df.format(new Date()).toString());

		int result = JOptionPane.showConfirmDialog(null, inputs, "Nytt TodoItem", JOptionPane.OK_CANCEL_OPTION);
		if (result == 0) {
			try {
				enteredDate = df.parse(date.getText());
				newTitle = title.getText();
				newBody = body.getText();
				tIL.addItem(new TodoItem(newTitle, newBody, enteredDate));
				updateList();
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Invalid Date and/or Time");
				return;
			}
		}
	}
	
	private static void doLoad() {
		tIL.initializeFromXML("todoList.xml");
		updateList();
	}
	
	private static void doDelete() {
		//tIL.removeAllDone();
		TodoItem selected = listbox.getSelectedValue();
		if (selected != null) {
			int selNum = listbox.getSelectedIndex();
			tIL.getTodoList().remove(selNum);
			updateList();
		}
	}
	
	private static void doRemove() {
		tIL.removeAllDone();
		updateList();
	}
	
	private static void doToggle() {
		TodoItem selected = listbox.getSelectedValue();
		if (selected != null) {
			int selNum = listbox.getSelectedIndex();
			tIL.getTodoList().get(selNum).setToggle();
			updateList();
		}
	}
	
	private static void doSort() {
		tIL.sortList();
		updateList();
	}

	private static void doSearch() {
		String line;
		int currentItem = 0;

		searchText = JOptionPane.showInputDialog("Search text");
		if (searchText != null) {
			searchText=searchText.toLowerCase();

			listbox.clearSelection();
			// searchText = search.getText().toLowerCase();

			for (TodoItem item : tIL.getTodoList()) {
				line = item.toString().toLowerCase();

				if (line.contains(searchText)) {
					listbox.setSelectedIndex(currentItem);
					nextButton.requestFocus();
					return;
				}
				currentItem++;
			}
		}
	}
		
	private static void doNext() {
		String line;
		int startLine=0;
		
		if (!listbox.isSelectionEmpty()) {
			startLine=listbox.getSelectedIndex()+1;
		}		
		for (int i=startLine; i<listbox.getModel().getSize(); i++) {
			line = listbox.getModel().getElementAt(i).toString().toLowerCase();
			if (line.contains(searchText)) {
				listbox.setSelectedIndex(i);
				return;
			}						
		}		
	}
	
	
}
