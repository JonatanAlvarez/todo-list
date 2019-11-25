import { Component } from '@angular/core';
import { TodoService } from './service/todo.service';
import { Todo } from './model/todo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'TODO List - Client App';
  text = "";
  pending = false;
  resolved = false;

  constructor(private todoService:TodoService) {

  }

  filter() {
    if(this.text !== '' && !isNaN(Number(this.text))) {
      this.pending = false;
      this.resolved = false;
      this.todoService.findByID(this.text).subscribe(result => {
        this.todoService.todos = new Array<Todo>();
        this.todoService.todos.push(result);
      });
    } else {
      this.todoService.description = this.text;

      if(this.pending && !this.resolved) {
        this.todoService.status = "pendiente";
      } else if(this.resolved && !this.pending) {
        this.todoService.status = "resuelto";
      } else {
        this.todoService.status = "";
      }
      
      this.todoService.findAll().subscribe(results => this.todoService.todos = results);
    }
  }
}
