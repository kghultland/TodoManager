import java.awt.Container; 
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
 
public class TodoUI {
	// Test
	private JFrame frame;
	private Container contentPane;
	private JList<TodoItem> listbox;

	
	public TodoUI(TodoItemList tIL) {
		super();
		
		// Initialise window
		frame = new JFrame("Todo system v1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocation(400, 200);
        frame.setSize(800, 600);
        
        // Fetch the window ContentPane and set its layout 
        contentPane = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
       
        
        // Initialise the JList and add it to the ContentPane
        DefaultListModel<TodoItem> listModel = new DefaultListModel<TodoItem>();
        
        for(int i=0; i<tIL.size(); i++) {
        	listModel.addElement(tIL.getTodoList().get(i));
        }
        
        listbox = new JList<TodoItem>(listModel);
        
        
        listbox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listbox.setLayoutOrientation(JList.VERTICAL);
        listbox.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(listbox);
        listScroller.setPreferredSize(new Dimension(600, 550));
        
        contentPane.add(listScroller);
        
        frame.setVisible(true);
	}
		

}
