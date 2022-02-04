package br.com.alura.control.financeiro.core.usecase.expense;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.core.mapper.ExpenseModelMapper;
import br.com.alura.control.financeiro.core.request.ExpenseRequest;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import br.com.alura.control.financeiro.validations.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class ExpenseUpdateUseCase {
    
    private IExpense expenseGatewayExpense;

    @Validated(OnUpdate.class)
    @Transactional
    public ExpenseResponse expenseUpdate(Long id, @Valid ExpenseRequest expenseRequest){

        log.info("expenseUpdate");

        Expense expense = expenseGatewayExpense.findById(id);
        
        expense.update(expenseRequest);

        return ExpenseModelMapper.INSTANCE.entityToResponse(expenseGatewayExpense.update(id, expense));
    }
}
