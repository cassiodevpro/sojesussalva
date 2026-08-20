import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  constructor(public router: Router, public authService: AuthService) {}

  irParaConstrucao(): void {
    this.router.navigate(['construcao']);
  }

  logar(): void {
    this.router.navigate(['login']);
  }

  irParaFinanceiro(): void {
    this.router.navigate(['financeiro']);
  }

  sair(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
