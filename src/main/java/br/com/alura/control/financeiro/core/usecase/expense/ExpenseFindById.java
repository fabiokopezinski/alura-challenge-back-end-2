package br.com.alura.control.financeiro.core.usecase.expense;

import br.com.alura.control.financeiro.core.entities.Expense;

public interface ExpenseFindById {
    Expense findById(Long id);
}
