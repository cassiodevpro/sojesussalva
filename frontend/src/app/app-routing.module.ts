import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home/home.component';
import { ContructionComponent } from './components/contruction/contruction.component';
import { LoginComponent } from './components/login/login.component';
import { FinanceiroComponent } from './components/financeiro/financeiro.component';
import { AdminGuard } from './guards/admin.guard';



  const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'construcao', component: ContructionComponent },
    { path: 'login', component: LoginComponent },
    { path: 'financeiro', component: FinanceiroComponent, canActivate: [AdminGuard] },
    { path: '**', redirectTo: '' }
  ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
