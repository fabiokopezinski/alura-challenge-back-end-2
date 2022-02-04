package br.com.alura.control.financeiro.core.usecase.expense;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.control.financeiro.core.mapper.ExpenseModelMapper;
import br.com.alura.control.financeiro.core.request.ExpenseRequest;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import br.com.alura.control.financeiro.validations.OnCreate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Validated
@Slf4j
public class ExpenseSaveUseCase {

    private IExpense expenseSave;

    @Validated(OnCreate.class)
    public ExpenseResponse expenseSave(@Valid ExpenseRequest request) {

        log.info("expenseSave");

        return ExpenseModelMapper.INSTANCE
                .entityToResponse(expenseSave.saveExpense(ExpenseModelMapper.INSTANCE.requestToEntity(request)));
    }
}
