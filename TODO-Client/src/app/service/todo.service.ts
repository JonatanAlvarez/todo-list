import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Todo } from '../model/todo';
import { Observable } from 'rxjs';

@Injectable()
export class TodoService {

  private todosUrl: string;
  public todos: Todo[];
  public description = "";
  public status = "";

  constructor(private http: HttpClient) {
    this.todosUrl = 'http://localhost:8080/api/todos'
  }
  
  public findAll(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.todosUrl + "?description=" + this.description + "&status=" + this.status);
  }

  public findByID(id: String): Observable<Todo> {
    return this.http.get<Todo>(this.todosUrl + "/" + id);
  }

  public save(todo: Todo) {
    const formData = new FormData();
    formData.append('description', todo.description);
    formData.append('status', todo.status);
    formData.append('file', todo.file);
    return this.http.post<Todo>(this.todosUrl, formData);
  }

  public update(todo: Todo) {
    return this.http.put<Todo>(this.todosUrl + "/" + todo.id, todo);
  }

}
