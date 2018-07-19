package todo.todo_web_app;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = { "http://localhost:4200" })
public class TodoController {

	private TodoRepository repo;

	public TodoController(TodoRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Collection<Todo> getTodos() {
		return repo.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Todo> postTodo(@RequestBody Todo todoItem) {
		System.out.println(todoItem.getTodoContent() + " " + todoItem.getId());
		repo.save(todoItem);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		System.out.println(ResponseEntity.created(location).build());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping({ "{todoId}" })
	public void deleteTodo(@PathVariable long todoId) {
		Optional<Todo> toDelete = repo.findById(todoId);

		if (toDelete.isPresent()) {
			repo.delete(toDelete.get());
		}
	}

	@GetMapping({ "todoId" })
	public Optional<Todo> getTodo(@PathVariable long todoId) {
		return repo.findById(todoId);
	}
}
