import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {
  CategoriaFinanceira,
  FinanceiroService,
  FormaPagamento,
  LancamentoFinanceiro,
  ResumoFinanceiro,
  TipoLancamento
} from '../../services/financeiro.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-financeiro',
  templateUrl: './financeiro.component.html',
  styleUrls: ['./financeiro.component.scss']
})
export class FinanceiroComponent implements OnInit {
  lancamentos: LancamentoFinanceiro[] = [];
  resumo: ResumoFinanceiro = { totalEntradas: 0, totalSaidas: 0, saldo: 0 };
  carregando = false;
  erro = '';

  readonly tipos: TipoLancamento[] = ['ENTRADA', 'SAIDA'];
  readonly categorias: CategoriaFinanceira[] = [
    'DIZIMO', 'OFERTA', 'DOACAO', 'EVENTO', 'ALUGUEL', 'CONTAS_CONSUMO', 'MANUTENCAO', 'DEPARTAMENTO', 'OUTROS'
  ];
  readonly formasPagamento: FormaPagamento[] = ['DINHEIRO', 'PIX', 'CARTAO', 'TRANSFERENCIA', 'BOLETO'];

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private financeiroService: FinanceiroService,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      tipo: ['ENTRADA', Validators.required],
      categoria: ['DIZIMO', Validators.required],
      descricao: ['', Validators.required],
      valor: [null, [Validators.required, Validators.min(0.01)]],
      data: [new Date().toISOString().substring(0, 10), Validators.required],
      formaPagamento: ['DINHEIRO', Validators.required],
      observacoes: ['']
    });
  }

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.carregando = true;
    this.financeiroService.listar().subscribe({
      next: (lista) => (this.lancamentos = lista),
      error: () => (this.erro = 'Não foi possível carregar os lançamentos.'),
      complete: () => (this.carregando = false)
    });
    this.financeiroService.resumo().subscribe({
      next: (r) => (this.resumo = r)
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }
    this.erro = '';
    this.financeiroService.criar(this.form.value).subscribe({
      next: () => {
        this.form.reset({
          tipo: 'ENTRADA',
          categoria: 'DIZIMO',
          descricao: '',
          valor: null,
          data: new Date().toISOString().substring(0, 10),
          formaPagamento: 'DINHEIRO',
          observacoes: ''
        });
        this.carregarDados();
      },
      error: () => (this.erro = 'Não foi possível salvar o lançamento.')
    });
  }

  excluir(id?: number): void {
    if (!id) {
      return;
    }
    this.financeiroService.excluir(id).subscribe({ next: () => this.carregarDados() });
  }

  sair(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
