package br.com.alura.control.financeiro.core.usecase.expense;

import br.com.alura.control.financeiro.core.entities.Expense;

public interface ExpenseUpdate {
    Expense update(Long id,Expense expense);
}
