public class TodoMain {
	private static TodoItemList tIL;
	public static final String FILENAME="todoList.xml";
		
	@SuppressWarnings("unused")
	private static TodoUI tUI;
		
	public TodoMain() {
		super();	
	}


	public static void main(String[] args) {	
		tIL = new TodoItemList();
		tIL.initializeFromXML(FILENAME);
		
		tUI = new TodoUI(tIL);		
	}
}		
