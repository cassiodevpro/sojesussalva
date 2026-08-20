package com.sjs.backend.repository;

import com.sjs.backend.model.LancamentoFinanceiro;
import com.sjs.backend.model.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Long> {

    @Query("""
            SELECT l FROM LancamentoFinanceiro l
            WHERE (:tipo IS NULL OR l.tipo = :tipo)
            AND (:categoria IS NULL OR l.categoria = :categoria)
            AND (:dataInicio IS NULL OR l.data >= :dataInicio)
            AND (:dataFim IS NULL OR l.data <= :dataFim)
            ORDER BY l.data DESC, l.id DESC
            """)
    List<LancamentoFinanceiro> buscarComFiltros(
            @Param("tipo") TipoLancamento tipo,
            @Param("categoria") com.sjs.backend.model.CategoriaFinanceira categoria,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );

    @Query("SELECT COALESCE(SUM(l.valor), 0) FROM LancamentoFinanceiro l WHERE l.tipo = :tipo")
    BigDecimal somarPorTipo(@Param("tipo") TipoLancamento tipo);
}
