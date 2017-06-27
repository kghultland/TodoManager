import java.beans.XMLDecoder; 
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TodoItemList {
	
	private ArrayList<TodoItem> todoList;

	public TodoItemList() {
		super();
		
		this.todoList = new ArrayList<TodoItem>();
		
	}

	public ArrayList<TodoItem> getTodoList() {
		return todoList;
	}
	
	public void addItem(TodoItem item) {
		todoList.add(item);
	}
	
	public void removeItem(int itemNumber) {
		todoList.remove(itemNumber);
	}
	
	public void setItemDone(int itemNumber) {
		todoList.get(itemNumber).setDone(true);		
	}
	
	public void setItemNotDone(int itemNumber) {
		todoList.get(itemNumber).setDone(false);		
	}
	
	public void removeAllDone() {	
		for (int i=todoList.size()-1; i>=0; i--) {
			if (todoList.get(i).isDone()) {
				todoList.remove(i);
			}			
		}
	}
	
	public void sortList() {
		Collections.sort(todoList, new Comparator<TodoItem>() {
		        @Override
		        public int compare(TodoItem item2, TodoItem item1)
		        {
		            return  item2.getDueDateTime().compareTo(item1.getDueDateTime());
		        }
		    });
	}
	
	public void serializeToXML(String fileName)
	{
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File "+fileName);
		}
				
		encoder.writeObject(todoList);
		
		encoder.close();
	}
	
	@SuppressWarnings("unchecked")
	public void initializeFromXML(String fileName)
	{
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File "+fileName+" not found");
		}
		todoList=(ArrayList<TodoItem>)decoder.readObject();
		decoder.close();
	}
	
	
	public void print() {
		int i=0;
		for (TodoItem tdi : todoList) {
			System.out.print("["+i+"] ");  
			tdi.print();
			i++;
		}	
	}

	public int size() {
		return todoList.size();
	}
}
