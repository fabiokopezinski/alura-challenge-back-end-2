package br.com.alura.control.financeiro.core.usecase.expense;

import org.springframework.data.domain.Page;

import br.com.alura.control.financeiro.core.entities.Expense;

public interface ExpenseFindAll {
    Page<Expense> findAll(int limit,int offset);
}
