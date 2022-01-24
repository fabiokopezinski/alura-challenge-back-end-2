package br.com.alura.control.financeiro.core.usecase.expense;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.mapper.ExpenseModelMapper;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseFindByIdUseCase {
    
    private ExpenseFindById expenseFindById;

    public ExpenseResponse findById(Long id){
        
        log.info("findById");
    
        return ExpenseModelMapper.INSTANCE.entityToResponse(expenseFindById.findById(id));
    }
}
