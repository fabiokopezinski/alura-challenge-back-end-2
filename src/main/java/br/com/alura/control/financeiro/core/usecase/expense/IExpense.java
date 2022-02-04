package br.com.alura.control.financeiro.core.usecase.expense;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import br.com.alura.control.financeiro.core.entities.Expense;

public interface IExpense {
    void delete(Long id);
    Page<Expense> findAll(int limit,int offset);
    Expense findById(Long id);
    Expense saveExpense(@Valid Expense expense);
    Expense update(Long id,Expense expense);
}
