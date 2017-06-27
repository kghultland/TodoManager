
import java.util.Date; 

public class TodoTester {
	private static TodoItemList tIL;
	private static TodoUI tUI;
		
	

	public TodoTester() {
		super();
		
	}


	public static void main(String[] args) {
		
		tIL = new TodoItemList();
		tIL.initializeFromXML("todoList.xml");
		tIL.sortList();
		
		tUI = new TodoUI(tIL);
		
		
		
		
		
//		System.out.println("\nReading from XML");
//		tIL.initializeFromXML("todoListTester.xml");
//		
//		System.out.println("\nList contains");
//		tIL.print();
		
//		System.out.println("\nCreating Item1");
//		TodoItem item1= new TodoItem("Item1", "Städa rummet", "2017-07-02 14:45");
//		item1.print();
//		
//		System.out.println("\nCreating Item2");
//		TodoItem item2 = new TodoItem("Item2", "rasta hunden", new Date());
//		item2.print();
//		
//		System.out.println("\nCreating Item3");
//		TodoItem item3 = new TodoItem("Item3", "sov middag", "2017-06-29 13:00");
//		item3.print();
//		
//		System.out.println("\nCreating Item4");
//		TodoItem item4 = new TodoItem("Item4", "klipp gräset", "2017-06-25 17:00");
//		item4.print();
//
//		System.out.println("\nAdding Item3 to list");
//		tIL.addItem(item3);
//		
//		System.out.println("\nAdding Item2 to list");	
//		tIL.addItem(item2);
//		
//		System.out.println("\nAdding Item1 to list");
//		tIL.addItem(item1);
//		
//		System.out.println("\nAdding Item4 to list");
//		tIL.addItem(item4);	
//		
//		System.out.println("\nList contains");
//		tIL.print();
//		
//		System.out.println("\nSet Item1 to done (not using list)");
//		item1.setDone(true);
//		
//		System.out.println("\nList contains");
//		tIL.print();
//		
//		System.out.println("\nSet Item2 to done (not using list)");
//		item2.setDone(true);
//		
//		System.out.println("\nList contains");
//		tIL.print();
//		
//		System.out.println("\nRemove index 2 from list");
//		tIL.removeItem(2);
//
//		System.out.println("\nList contains");
//		tIL.print();
//		
//		System.out.println("\nsetItemDone(2)");
//		tIL.setItemDone(2);
//		
//		System.out.println("\nList contains");
//		tIL.print();
//		
//		System.out.println("\nsetItemNotDone(1)");
//		tIL.setItemNotDone(1);
//		
//		System.out.println("\nList contains");
//		tIL.print();
//		
//		
//		System.out.println("\nSORTING LIST");
//		tIL.sortList();
//		
//		System.out.println("\nList contains");
//		tIL.print();
//		
//		
//		System.out.println("\nRemoving all Done items");
//		tIL.removeAllDone();
//		
//		System.out.println("\nList contains");
//		tIL.print();
//	
//		tIL.serializeToXML("todoList.xml");
		
		
	}

}
