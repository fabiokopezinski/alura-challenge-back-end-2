package br.com.alura.control.financeiro.core.usecase.revenue;

import br.com.alura.control.financeiro.core.entities.Revenue;

public interface RevenueFindById {
    Revenue findById(Long id);
}
