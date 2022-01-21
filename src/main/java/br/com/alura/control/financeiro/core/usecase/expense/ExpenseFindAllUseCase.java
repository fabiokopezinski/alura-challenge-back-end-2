package br.com.alura.control.financeiro.core.usecase.expense;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.mapper.ExpenseModelMapper;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseFindAllUseCase {
    
    private ExpenseFindAll expenseFindAll;

    public Page<ExpenseResponse> findAll(int limit,int offset){

        log.info("findAll");

        return ExpenseModelMapper.INSTANCE.allEntityToallResponse(expenseFindAll.findAll(limit, offset));
    }

}
