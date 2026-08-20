import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';

export interface AuthResponse {
  token: string;
  nome: string;
  email: string;
  role: 'USER' | 'ADMIN';
}

export interface RegisterPayload {
  nome: string;
  email: string;
  senha: string;
  telefone?: string;
}

const TOKEN_KEY = 'sjs_token';
const NOME_KEY = 'sjs_nome';
const ROLE_KEY = 'sjs_role';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly apiUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient) {}

  login(email: string, senha: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, { email, senha }).pipe(
      tap((res) => this.armazenarSessao(res))
    );
  }

  register(payload: RegisterPayload): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, payload).pipe(
      tap((res) => this.armazenarSessao(res))
    );
  }

  logout(): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(NOME_KEY);
    localStorage.removeItem(ROLE_KEY);
  }

  getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }

  getNome(): string | null {
    return localStorage.getItem(NOME_KEY);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  isAdmin(): boolean {
    return localStorage.getItem(ROLE_KEY) === 'ADMIN';
  }

  private armazenarSessao(res: AuthResponse): void {
    localStorage.setItem(TOKEN_KEY, res.token);
    localStorage.setItem(NOME_KEY, res.nome);
    localStorage.setItem(ROLE_KEY, res.role);
  }
}
