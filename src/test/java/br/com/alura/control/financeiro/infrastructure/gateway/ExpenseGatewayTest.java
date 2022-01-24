package br.com.alura.control.financeiro.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.exceptions.BusinessException;
import br.com.alura.control.financeiro.feature.ExpenseScenarioFactoryoFactory;
import br.com.alura.control.financeiro.infrastructure.repository.ExpenseRepository;
import br.com.alura.control.financeiro.validations.Message;

@ExtendWith(MockitoExtension.class)
public class ExpenseGatewayTest {

    @InjectMocks
    private ExpenseGateway expenseGateway;

    @Mock
    private ExpenseRepository expenseRepository;


    @Test
    public void findAll_WhenExistAnyExpense_ThenReturnAList() {

        when(expenseRepository.findAll(any(Pageable.class)))
                .thenReturn(ExpenseScenarioFactoryoFactory.EXPENSE_FIND_ALL);

        Page<Expense> findAll = expenseGateway.findAll(2,0);

        assertNotNull(findAll);

        verify(expenseRepository).findAll(any(Pageable.class));

    }

    @Test
    public void findById_WhenExisted_ThenReturnExpense() {

        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(ExpenseScenarioFactoryoFactory.EXPENSE_ONE));

        Expense existed = expenseGateway.findById(1L);

        assertNotNull(existed);

        verify(expenseRepository).findById(anyLong());

    }

    @Test
    public void findById_WhenNotExisted_ThenReturnException(){

        when(expenseRepository.findById(anyLong())).thenThrow(Message.NOT_FOUND_EXPENSE.asBusinessException());

         assertThrows(BusinessException.class,  ()->expenseGateway.findById(1L));
    }

    @Test
    public void saveExpense_WhenNotExistedInTheDatabase_ThenReturnAExpense(){

        when(expenseRepository.findByDescriptionAndValueAndData(anyString(), any(), anyString())).thenReturn(Optional.empty());

        when(expenseRepository.save(any())).thenReturn(ExpenseScenarioFactoryoFactory.EXPENSE_ONE);

        Expense saveExpense = expenseGateway.saveExpense(ExpenseScenarioFactoryoFactory.EXPENSE_ONE);

        assertNotNull(saveExpense);

        assertEquals(saveExpense, ExpenseScenarioFactoryoFactory.EXPENSE_ONE);

        verify(expenseRepository).save(any());
    }

    @Test
    public void saveExpense_WhenExistInTheDatabase_thenReturnException(){
        
        when(expenseRepository.findByDescriptionAndValueAndData(anyString(), any(), anyString())).thenThrow(Message.IS_PRESENT_EXPENSE.asBusinessException());

        assertThrows(BusinessException.class,  ()->expenseGateway.saveExpense(ExpenseScenarioFactoryoFactory.EXPENSE_ONE));

    }

    @Test
    public void update_WhenExisted_thenReturnExpense(){

        Expense update = expenseGateway.update(1L, ExpenseScenarioFactoryoFactory.EXPENSE_ONE);

        assertNotNull(update);
    }

    @Test
    public void delete_WhenExist_ThenDelete(){

        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(ExpenseScenarioFactoryoFactory.EXPENSE_ONE));

        expenseGateway.delete(1L);

    }

    @Test
    public void delete_WhenNotExist_ThenReturnExceptin(){

        when(expenseRepository.findById(anyLong())).thenThrow(Message.NOT_FOUND_EXPENSE.asBusinessException());

        assertThrows(BusinessException.class,  ()->expenseGateway.delete(1L));

    }
}
