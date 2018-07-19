package todo.todo_web_app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {

	@Id
	@GeneratedValue
	private long id;
	private String todoContent;

	public Todo() {
	}

	public Todo(Long id, String todoContent) {
		this.id = id;
		this.todoContent = todoContent;
	}

	public long getId() {
		return id;
	}

	public String getTodoContent() {
		return todoContent;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}
	
	@Override
	public String toString() {
		return todoContent;
	}

}
