import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TodoListComponent } from './todo-list/todo-list.component';
import { TodoFormComponent } from './todo-form/todo-form.component';
import { TodoViewComponent } from './todo-view/todo-view.component';

const routes: Routes = [
  { path: 'todo/add', component: TodoFormComponent },
  { path: 'todo/view/:id', component: TodoViewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
