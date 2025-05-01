import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContructionComponent } from './contruction/contruction.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ContructionComponent,
    LoginComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    ContructionComponent

  ]
})
export class ComponentsModule { }
