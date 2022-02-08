package br.com.alura.control.financeiro.core.usecase.expense;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.mapper.ExpenseModelMapper;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseFindByDescriptionUseCase {

    private IExpense expense;

    public List<ExpenseResponse> findByDescrExpense(String description) {

        log.info("findByDescrExpense");

        return ExpenseModelMapper.INSTANCE.listEntityToListResponse(this.expense.findByDescription(description));
    }
}
