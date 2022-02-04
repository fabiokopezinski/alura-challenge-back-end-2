package br.com.alura.control.financeiro.core.usecase.expense;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class ExpenseDeleteUseCase {
    
    private IExpense expenseDelete;

    public void expenseDelete(Long id){

        log.info("expenseDelete");

        this.expenseDelete.delete(id);
    }
}
