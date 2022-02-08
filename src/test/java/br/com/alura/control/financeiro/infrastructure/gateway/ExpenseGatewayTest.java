package br.com.alura.control.financeiro.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.exceptions.BusinessException;
import br.com.alura.control.financeiro.feature.ExpenseScenarioFactory;
import br.com.alura.control.financeiro.infrastructure.repository.ExpenseRepository;
import br.com.alura.control.financeiro.validations.Message;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;



@ExtendWith(MockitoExtension.class)
public class ExpenseGatewayTest {

    @InjectMocks
    private ExpenseGateway expenseGateway;

    @Mock
    private ExpenseRepository expenseRepository;

    Logger logger = (Logger) LoggerFactory.getLogger(ExpenseGateway.class);

    ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }


    @Test
    public void findAll_WhenExistAnyExpense_ThenReturnAList() {

        when(expenseRepository.findAll(any(Pageable.class)))
                .thenReturn(ExpenseScenarioFactory.EXPENSE_FIND_ALL);

        Page<Expense> findAll = expenseGateway.findAllExpense(2,0);

        assertNotNull(findAll);

        verify(expenseRepository).findAll(any(Pageable.class));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("offset=0 limit=2", logsList.get(0).getFormattedMessage());


    }

    @Test
    public void findById_WhenExisted_ThenReturnExpense() {

        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE_ONE));

        Expense existed = expenseGateway.findById(1L);

        assertNotNull(existed);

        verify(expenseRepository).findById(anyLong());

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1", logsList.get(0).getFormattedMessage());

    }

    @Test
    public void findById_WhenNotExisted_ThenReturnException(){

        when(expenseRepository.findById(anyLong())).thenThrow(Message.NOT_FOUND_EXPENSE.asBusinessException());

        assertThrows(BusinessException.class,  ()->expenseGateway.findById(1L));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1", logsList.get(0).getFormattedMessage());


    }

    @Test
    public void saveExpense_WhenNotExistedInTheDatabase_ThenReturnAExpense(){

        when(expenseRepository.findByDescriptionAndValue(anyString(), any())).thenReturn(Optional.empty());

        when(expenseRepository.save(any())).thenReturn(ExpenseScenarioFactory.EXPENSE_ONE);

        Expense saveExpense = expenseGateway.saveExpense(ExpenseScenarioFactory.EXPENSE_ONE);

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(saveExpense);

        assertEquals(saveExpense, ExpenseScenarioFactory.EXPENSE_ONE);

        verify(expenseRepository).save(any());


        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("expense=".concat(ExpenseScenarioFactory.EXPENSE_ONE.toString()),
                logsList.get(0).getFormattedMessage());
    }

    @Test
    public void saveExpense_WhenExistInTheDatabase_thenReturnException(){
       
        when(expenseRepository.findByDescriptionAndValue(anyString(), any())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE_ONE));

        assertThrows(BusinessException.class,  ()->expenseGateway.saveExpense(ExpenseScenarioFactory.EXPENSE_ONE));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("expense=".concat(ExpenseScenarioFactory.EXPENSE_ONE.toString()),
        logsList.get(0).getFormattedMessage());

    }

    @Test
    public void update_WhenExisted_thenReturnExpense(){

        Expense update = expenseGateway.update(1L, ExpenseScenarioFactory.EXPENSE_ONE);

        assertNotNull(update);

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1".concat(" expense=").concat(update.toString()), logsList.get(0).getFormattedMessage());

    }

    @Test
    public void delete_WhenExist_ThenDelete(){

        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(ExpenseScenarioFactory.EXPENSE_ONE));

        expenseGateway.delete(1L);

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1", logsList.get(0).getFormattedMessage());

    }

    @Test
    public void delete_WhenNotExist_ThenReturnExceptin(){

        when(expenseRepository.findById(anyLong())).thenThrow(Message.NOT_FOUND_EXPENSE.asBusinessException());

        assertThrows(BusinessException.class,  ()->expenseGateway.delete(1L));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1", logsList.get(0).getFormattedMessage());

    }

    @Test
    public void findByDescription_ThenReturn(){

        when(expenseRepository.findByDescription(any())).thenReturn(ExpenseScenarioFactory.EXPENSE_FIND_ALL.toList());

        List<Expense> findByDescription = expenseGateway.findByDescription("ReceitaUm");

        assertNotNull(findByDescription);

        verify(expenseRepository).findByDescription(anyString());

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("description=ReceitaUm", logsList.get(0).getFormattedMessage());
    }

    @Test
    public void findByData_ThenReturn(){
        when(expenseRepository.findByData(any())).thenReturn(ExpenseScenarioFactory.EXPENSE_FIND_ALL.toList());

        List<Expense> findByData = expenseGateway.findByData("10-2021");

        assertNotNull(findByData);

        verify(expenseRepository).findByData(anyString());

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("data=10-2021", logsList.get(0).getFormattedMessage());
    }

    @Test
    public void findByAll_ThenReturn(){

        when(expenseRepository.findAll()).thenReturn(ExpenseScenarioFactory.EXPENSE_FIND_ALL.toList());

        List<Expense> findAll = expenseGateway.findAll();

        assertNotNull(findAll);

        verify(expenseRepository).findAll();
    }
}
