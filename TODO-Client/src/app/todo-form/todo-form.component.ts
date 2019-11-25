import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoService } from '../service/todo.service';
import { Todo } from '../model/todo';

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.scss']
})
export class TodoFormComponent implements OnInit {

  private todo: Todo;
  private file: any;

  constructor(private route: ActivatedRoute, private router: Router, private todoService: TodoService) {
    this.todo = new Todo();
  }

  ngOnInit() { }

  onFileChange(e) {
    this.todo.file = e.target.files[0];
  }
  
  onSubmit() {
    this.todoService.save(this.todo).subscribe(newTodo => {
      this.todoService.todos.push(newTodo);
      this.router.navigate(['/']);
    })
  }

  clearFile() {
    this.file = null;
    this.todo.file = "";
  }

  close(e) {
    if(e.target === e.currentTarget) {
      this.router.navigate(['/']);
    }
  }
}
