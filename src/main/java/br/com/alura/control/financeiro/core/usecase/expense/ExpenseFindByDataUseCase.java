package br.com.alura.control.financeiro.core.usecase.expense;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.mapper.ExpenseModelMapper;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class ExpenseFindByDataUseCase {
    
    private IExpense expense;

    public List<ExpenseResponse> findByDate(String month,String year){

        log.info("month={} year={}", month, year);

        return ExpenseModelMapper.INSTANCE.listEntityToListResponse(expense.findByData(month.concat("-")+year));
    }
}
