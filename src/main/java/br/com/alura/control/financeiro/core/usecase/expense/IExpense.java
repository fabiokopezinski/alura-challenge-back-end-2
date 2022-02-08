package br.com.alura.control.financeiro.core.usecase.expense;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import br.com.alura.control.financeiro.core.entities.Expense;

public interface IExpense {
    void delete(Long id);
    Page<Expense> findAllExpense(int limit,int offset);
    Expense findById(Long id);
    Expense saveExpense(@Valid Expense expense);
    Expense update(Long id,Expense expense);
    List<Expense> findByDescription(String expense);
    List<Expense> findByData(String data);
    List<Expense> findAll();
}
