import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
  erro = '';
  carregando = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    this.erro = '';
    this.carregando = true;
    const { email, senha } = this.loginForm.value;
    this.authService.login(email, senha).subscribe({
      next: () => {
        this.carregando = false;
        this.router.navigate([this.authService.isAdmin() ? '/financeiro' : '/']);
      },
      error: () => {
        this.carregando = false;
        this.erro = 'E-mail ou senha inválidos.';
      }
    });
  }
  voltar(): void {
    this.router.navigate(['/']); // Rota padrão para o componente inicial
  }

}
