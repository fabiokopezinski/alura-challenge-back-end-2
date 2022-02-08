package br.com.alura.control.financeiro.core.usecase.expanse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseSaveUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.IExpense;
import br.com.alura.control.financeiro.exceptions.BusinessException;
import br.com.alura.control.financeiro.feature.ExpenseScenarioFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class ExpenseSaveUseCaseTest {

    @InjectMocks
    private ExpenseSaveUseCase expenseSaveUseCase;

    @Mock
    private IExpense eIExpense;

    Logger logger = (Logger) LoggerFactory.getLogger(ExpenseSaveUseCase.class);

    ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void saveUseCase_WhenIsNotSaved_ThenSaveOk() {

        when(eIExpense.saveExpense(any())).thenReturn(ExpenseScenarioFactory.EXPENSE_ONE);

        ExpenseResponse expenseSave = expenseSaveUseCase.expenseSave(ExpenseScenarioFactory.EXPENSE_REQUEST);

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(expenseSave);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("expenseSave", logsList.get(0).getMessage());
    }

    @Test
    public void saveUseCase_WhenIsSaved_ThenReturnException() {

        when(eIExpense.saveExpense(any())).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class,
                () -> expenseSaveUseCase.expenseSave(ExpenseScenarioFactory.EXPENSE_REQUEST));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("expenseSave", logsList.get(0).getMessage());
    }

}
