import { Component, OnInit } from '@angular/core';
import { Todo } from '../todo';
import { TodoService } from '../todo.service'

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css'],
  providers: [TodoService]
})
export class TodosComponent implements OnInit {
  todos: Array<Todo>;
  currTodoContent: string;
  currTodoContentIsValid: boolean = true;

  constructor(private todoService: TodoService) { }

  ngOnInit() {
    this.getTodos();
  }

  getTodos(): void {
    this.todoService.getTodos().subscribe(
      (data: Todo[]) => {
        console.log(data);
        this.todos = data;
      },
      error => console.log(error)
    )
  }

  removeTodo(todo: Todo): void {
    this.todoService.deleteTodo(todo).subscribe(
      x => this.getTodos()
    );
  }

  addTodo(): void {
    this.currTodoContentIsValid = !(!this.currTodoContent);

    if (this.currTodoContentIsValid) {
      this.todoService.addTodo(this.currTodoContent).subscribe(
        x => this.getTodos(),
        err => console.error("Observer got an error: " + err),
        () => this.currTodoContent = ''
      );
    }
  }

  

}
