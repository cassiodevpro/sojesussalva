package com.sjs.backend.controller;

import com.sjs.backend.dto.LancamentoFinanceiroDTO;
import com.sjs.backend.dto.ResumoFinanceiroDTO;
import com.sjs.backend.model.CategoriaFinanceira;
import com.sjs.backend.model.LancamentoFinanceiro;
import com.sjs.backend.model.TipoLancamento;
import com.sjs.backend.model.Usuario;
import com.sjs.backend.repository.LancamentoFinanceiroRepository;
import com.sjs.backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private final LancamentoFinanceiroRepository repository;
    private final UsuarioRepository usuarioRepository;

    public FinanceiroController(LancamentoFinanceiroRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<LancamentoFinanceiro> listar(
            @RequestParam(required = false) TipoLancamento tipo,
            @RequestParam(required = false) CategoriaFinanceira categoria,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim
    ) {
        return repository.buscarComFiltros(tipo, categoria, dataInicio, dataFim);
    }

    @GetMapping("/resumo")
    public ResumoFinanceiroDTO resumo() {
        BigDecimal totalEntradas = repository.somarPorTipo(TipoLancamento.ENTRADA);
        BigDecimal totalSaidas = repository.somarPorTipo(TipoLancamento.SAIDA);
        return new ResumoFinanceiroDTO(totalEntradas, totalSaidas, totalEntradas.subtract(totalSaidas));
    }

    @PostMapping
    public ResponseEntity<LancamentoFinanceiro> criar(@RequestBody LancamentoFinanceiroDTO dto, Authentication authentication) {
        LancamentoFinanceiro lancamento = new LancamentoFinanceiro();
        aplicarDTO(lancamento, dto);
        lancamento.setResponsavel(usuarioLogado(authentication));
        return ResponseEntity.ok(repository.save(lancamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoFinanceiro> atualizar(@PathVariable Long id, @RequestBody LancamentoFinanceiroDTO dto) {
        LancamentoFinanceiro lancamento = repository.findById(id).orElseThrow();
        aplicarDTO(lancamento, dto);
        return ResponseEntity.ok(repository.save(lancamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void aplicarDTO(LancamentoFinanceiro lancamento, LancamentoFinanceiroDTO dto) {
        lancamento.setTipo(dto.getTipo());
        lancamento.setCategoria(dto.getCategoria());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setValor(dto.getValor());
        lancamento.setData(dto.getData());
        lancamento.setFormaPagamento(dto.getFormaPagamento());
        lancamento.setObservacoes(dto.getObservacoes());
    }

    private Usuario usuarioLogado(Authentication authentication) {
        return usuarioRepository.findByEmail(authentication.getName()).orElse(null);
    }
}
