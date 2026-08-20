import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export type TipoLancamento = 'ENTRADA' | 'SAIDA';

export type CategoriaFinanceira =
  | 'DIZIMO'
  | 'OFERTA'
  | 'DOACAO'
  | 'EVENTO'
  | 'ALUGUEL'
  | 'CONTAS_CONSUMO'
  | 'MANUTENCAO'
  | 'DEPARTAMENTO'
  | 'OUTROS';

export type FormaPagamento = 'DINHEIRO' | 'PIX' | 'CARTAO' | 'TRANSFERENCIA' | 'BOLETO';

export interface LancamentoFinanceiro {
  id?: number;
  tipo: TipoLancamento;
  categoria: CategoriaFinanceira;
  descricao: string;
  valor: number;
  data: string;
  formaPagamento: FormaPagamento;
  observacoes?: string;
}

export interface ResumoFinanceiro {
  totalEntradas: number;
  totalSaidas: number;
  saldo: number;
}

@Injectable({ providedIn: 'root' })
export class FinanceiroService {
  private readonly apiUrl = `${environment.apiUrl}/financeiro`;

  constructor(private http: HttpClient) {}

  listar(): Observable<LancamentoFinanceiro[]> {
    return this.http.get<LancamentoFinanceiro[]>(this.apiUrl);
  }

  resumo(): Observable<ResumoFinanceiro> {
    return this.http.get<ResumoFinanceiro>(`${this.apiUrl}/resumo`);
  }

  criar(lancamento: LancamentoFinanceiro): Observable<LancamentoFinanceiro> {
    return this.http.post<LancamentoFinanceiro>(this.apiUrl, lancamento);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
