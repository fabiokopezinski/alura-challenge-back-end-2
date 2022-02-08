package br.com.alura.control.financeiro.core.usecase.expanse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
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
import br.com.alura.control.financeiro.core.usecase.expense.ExpenseFindByDataUseCase;
import br.com.alura.control.financeiro.core.usecase.expense.IExpense;
import br.com.alura.control.financeiro.feature.ExpenseScenarioFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class ExpenseFindByDataUseCaseTest {

    @InjectMocks
    private ExpenseFindByDataUseCase expenseFindByDataUseCase;

    @Mock
    private IExpense expense;

    private Logger logger = (Logger) LoggerFactory.getLogger(ExpenseFindByDataUseCase.class);

    private ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void expenseFindByDataUseCase_ThenReturn() {

        when(expense.findByData(anyString())).thenReturn(ExpenseScenarioFactory.EXPENSE_FIND_ALL.toList());

        List<ExpenseResponse> findByDate = expenseFindByDataUseCase.findByDate("10", "2022");

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(findByDate);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("month=10 year=2022", logsList.get(0).getFormattedMessage());

        verify(expense).findByData(anyString());
    }

}
