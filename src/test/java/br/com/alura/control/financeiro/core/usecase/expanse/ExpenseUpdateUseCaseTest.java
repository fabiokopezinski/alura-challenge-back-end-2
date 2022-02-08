package br.com.alura.control.financeiro.core.usecase.expanse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseUpdateUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.IExpense;
import br.com.alura.control.financeiro.exceptions.BusinessException;
import br.com.alura.control.financeiro.feature.ExpenseScenarioFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class ExpenseUpdateUseCaseTest {

    @InjectMocks
    private ExpenseUpdateUseCase expenseUpdateUseCase;

    @Mock
    private IExpense eIExpense;

    Logger logger = (Logger) LoggerFactory.getLogger(ExpenseUpdateUseCase.class);

    ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void expenseUpdateUseCase_WhenExpenseExist_ThenUpdateExpense() {

        when(eIExpense.findById(anyLong())).thenReturn(ExpenseScenarioFactory.EXPENSE_ONE);

        when(eIExpense.update(anyLong(), any())).thenReturn(ExpenseScenarioFactory.EXPENSE_ONE);

        ExpenseResponse expenseUpdate = expenseUpdateUseCase.expenseUpdate(1L, ExpenseScenarioFactory.EXPENSE_REQUEST);

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(expenseUpdate);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("expenseUpdate", logsList.get(0).getMessage());
    }

    @Test
    public void expenseUpdateUseCase_WhenExpenseExist_ThenUpdateException() {

        when(eIExpense.findById(anyLong())).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class,
                () -> expenseUpdateUseCase.expenseUpdate(1L, ExpenseScenarioFactory.EXPENSE_REQUEST));

        List<ILoggingEvent> logsList = loggers.list;

         assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("expenseUpdate", logsList.get(0).getMessage());

    }

}
