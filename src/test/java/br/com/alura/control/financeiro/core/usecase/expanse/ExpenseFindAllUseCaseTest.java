package br.com.alura.control.financeiro.core.usecase.expanse;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseFindAllUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.IExpense;
import br.com.alura.control.financeiro.feature.ExpenseScenarioFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class ExpenseFindAllUseCaseTest {
    
    @InjectMocks
    private ExpenseFindAllUseCase expenseFindAllUseCase;

    @Mock
    private IExpense eIExpense;

    private Logger logger = (Logger) LoggerFactory.getLogger(ExpenseFindAllUseCase.class);

    private ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void expenseFindAllUseCase_ThenReturn(){

        when(eIExpense.findAllExpense(anyInt(), anyInt())).thenReturn(ExpenseScenarioFactory.EXPENSE_FIND_ALL);
        
        List<ExpenseResponse> findAll = expenseFindAllUseCase.findAll(2, 0);
        
        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(findAll);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("findAll", logsList.get(0).getMessage());

        verify(eIExpense).findAllExpense(anyInt(),anyInt());
    }
}
