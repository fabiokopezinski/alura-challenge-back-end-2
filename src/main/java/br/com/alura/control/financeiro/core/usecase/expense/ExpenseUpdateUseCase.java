package br.com.alura.control.financeiro.core.usecase.expense;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.core.mapper.ExpenseModelMapper;
import br.com.alura.control.financeiro.core.request.ExpenseRequest;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class ExpenseUpdateUseCase {
    
    private ExpenseUpdate expenseUpdate;

    private ExpenseFindById expenseFindById;

    public ExpenseResponse expenseUpdate(Long id, ExpenseRequest expenseRequest){

        log.info("expenseUpdate");

        Expense expense = expenseFindById.findById(id);
        
        expense.update(expenseRequest);

        return ExpenseModelMapper.INSTANCE.entityToResponse(expenseUpdate.update(id, expense));
    }
}
