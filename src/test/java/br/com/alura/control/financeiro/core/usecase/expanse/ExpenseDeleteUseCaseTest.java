package br.com.alura.control.financeiro.core.usecase.expanse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import br.com.alura.control.financeiro.core.usecase.expense.ExpenseDeleteUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.IExpense;
import br.com.alura.control.financeiro.exceptions.BusinessException;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class ExpenseDeleteUseCaseTest {

    @InjectMocks
    private ExpenseDeleteUseCase expenseDeleteUseCase;
    
    @Mock
    private IExpense eIExpense;

    Logger logger = (Logger) LoggerFactory.getLogger(ExpenseDeleteUseCase.class);

    ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void expenseDeleteUseCase_WhenExiste_ThenDelete(){

        doNothing().when(eIExpense).delete(1L);

        expenseDeleteUseCase.expenseDelete(1L);

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("expenseDelete", logsList.get(0).getFormattedMessage());
    }

    @Test
    public void expenseDeleteUseCase_WhenNotExist_ThenDelete(){

        doThrow(BusinessException.class).when(eIExpense).delete(1L);

        assertThrows(BusinessException.class, ()->expenseDeleteUseCase.expenseDelete(1L));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("expenseDelete", logsList.get(0).getFormattedMessage());
    }

}
