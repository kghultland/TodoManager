import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class TodoItem {
	private String title;
	private String body;
	
	private Date dueDateTime;
	
	private boolean done;
	
	public TodoItem() {
		super();
		this.title = "title";
		this.body = "body";
		this.dueDateTime = null;
		this.done = false;		
	}

	public TodoItem(String title, String body, Date dueDateTime) {
		super();
		this.title = title;
		this.body = body;
		this.dueDateTime = dueDateTime;
		this.done = false;
	}
	
	public TodoItem(String title, String body, String dueDateTime) {
		super();	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
		
		
		try {
			this.dueDateTime = df.parse(dueDateTime);
		}
		catch(ParseException e) {
			this.dueDateTime = new Date();
			System.out.println("FEL! kan ej tolka "+dueDateTime);
		}	
		
		this.title = title;
		this.body = body;		
		this.done = false;		
			
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDueDateTime() {
		return dueDateTime;
	}
	
	public String getDueDateTimeAsString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(dueDateTime);		
	}

	public void setDueDateTime(Date dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public void print() {
		System.out.println(this.toString());		
	}
	
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		return ((done)?"[X] ":"[ ] ") +  df.format(dueDateTime) +" ["+title+"]  "+body;
	}

	public void setToggle() {
		this.done = !this.done;
		
	}
	

	
	
}
