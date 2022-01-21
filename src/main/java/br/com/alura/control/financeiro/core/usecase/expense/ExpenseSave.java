package br.com.alura.control.financeiro.core.usecase.expense;

import javax.validation.Valid;

import br.com.alura.control.financeiro.core.entities.Expense;

public interface ExpenseSave {
    Expense saveExpense(@Valid Expense expense);
}
