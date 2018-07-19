import { Injectable } from '@angular/core';
import { Todo } from './todo'
import { Observable } from '../../node_modules/rxjs/Observable';
import { ConfigurationService } from './configuration.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

// partakes in dependency injection system
@Injectable()
export class TodoService {
  private opts = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    }
  )};
  private fullEndpoint: string;
  private resource: string = "todos";

  constructor(private http: HttpClient, private config: ConfigurationService) {
    this.fullEndpoint = config.ServerWithApiUrl + this.resource;
  }

  getTodos<Todo>(): Observable<Todo> {
    return this.http.get<Todo>(this.fullEndpoint);
  }

  addTodo(content: string): Observable<Todo> {
   
    
    let sendBody = JSON.stringify({todoContent: content});
    return this.http.post<Todo>(this.fullEndpoint, sendBody, this.opts);
  }

  deleteTodo(todo: Todo): Observable<Todo> {
    return this.http.delete<Todo>(this.fullEndpoint + '/' + todo.id, this.opts);
  }
}
