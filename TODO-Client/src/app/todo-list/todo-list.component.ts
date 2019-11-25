import { Component, OnInit } from '@angular/core';
import { Todo } from '../model/todo';
import { TodoService } from '../service/todo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent implements OnInit {

  constructor(private todoService: TodoService, private router: Router) { }

  ngOnInit() {
    this.todoService.findAll().subscribe(results => {
      this.todoService.todos = results;
    });
  }

  showTodo(id:string) {
    this.router.navigate(['/todo/view/' + id]);
  }

}
