package br.com.alura.control.financeiro.core.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import br.com.alura.control.financeiro.core.response.SummaryResponse;
import br.com.alura.control.financeiro.core.usecase.expense.IExpense;
import br.com.alura.control.financeiro.core.usecase.revenue.IRevenue;
import br.com.alura.control.financeiro.feature.ExpenseScenarioFactory;
import br.com.alura.control.financeiro.feature.RevenueScenarioFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class SummaryUseCaseTest {

    @InjectMocks
    private SummaryUseCase summaryUseCase;

    @Mock
    private IExpense iExpense;

    @Mock
    private IRevenue iRevenue;

    private Logger logger = (Logger) LoggerFactory.getLogger(SummaryUseCase.class);

    private ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void summaryUseCase_WhenCall_ThenReturnSummary() {

        when(iRevenue.findAll()).thenReturn(RevenueScenarioFactory.REVENUE_FIND_ALL.toList());

        when(iExpense.findAll()).thenReturn(ExpenseScenarioFactory.EXPENSE_FIND_ALL.toList());

        SummaryResponse summary = summaryUseCase.summary("10", "2021");

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(summary);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("sumExpense={} sumRevenue={} balance={} expensesCategory={}", logsList.get(0).getMessage());
        
    }

}
