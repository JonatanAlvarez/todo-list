import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Todo } from '../model/todo';
import { TodoService } from '../service/todo.service';

@Component({
  selector: 'app-todo-view',
  templateUrl: './todo-view.component.html',
  styleUrls: ['./todo-view.component.scss']
})
export class TodoViewComponent implements OnInit {

  private id: string;
  private todo = new Todo();
  private isImg = false;

  constructor(private route: ActivatedRoute, private router: Router, private todoService: TodoService) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.todoService.findByID(this.id).subscribe(todo => {
      this.todo = todo;
      if(todo.file !== undefined && todo.file !== null && todo.file !== '') {
        const ext = todo.file.substr(todo.file.lastIndexOf('.') + 1);
        if(ext == 'jpg' || ext == 'jpeg' || ext == 'png' || ext == 'bmp' || ext == 'git') {
          this.isImg = true;
        }
      }
    })
  }

  onSubmit() {
    this.todoService.update(this.todo).subscribe( () => {
      this.todoService.findAll().subscribe(results => this.todoService.todos = results);
      this.router.navigate(['/']);
    })
  }

  close(e) {
    if(e.target === e.currentTarget) {
      this.router.navigate(['/']);
    }
  }

}
