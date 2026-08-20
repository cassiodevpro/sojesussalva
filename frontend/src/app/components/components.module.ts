import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContructionComponent } from './contruction/contruction.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MembrosComponent } from './membros/membros.component';
import { FinanceiroComponent } from './financeiro/financeiro.component';


@NgModule({
  declarations: [
    ContructionComponent,
    LoginComponent,
    MembrosComponent,
    FinanceiroComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    ContructionComponent,
    LoginComponent,
    FinanceiroComponent
  ]
})
export class ComponentsModule { }
