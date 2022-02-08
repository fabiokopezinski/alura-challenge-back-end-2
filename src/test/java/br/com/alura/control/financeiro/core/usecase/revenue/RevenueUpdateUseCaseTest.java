package br.com.alura.control.financeiro.core.usecase.revenue;

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

import br.com.alura.control.financeiro.core.response.RevenueResponse;
import br.com.alura.control.financeiro.exceptions.BusinessException;
import br.com.alura.control.financeiro.feature.RevenueScenarioFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class RevenueUpdateUseCaseTest {

    @InjectMocks
    private RevenueUpdateUseCase revenueUpdateUseCase;

    @Mock
    private IRevenue eIRevenue;

    Logger logger = (Logger) LoggerFactory.getLogger(RevenueUpdateUseCase.class);

    ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void expenseUpdateUseCase_WhenExpenseExist_ThenUpdateExpense() {

        when(eIRevenue.findById(anyLong())).thenReturn(RevenueScenarioFactory.REVENUE_ONE);

        when(eIRevenue.update(anyLong(), any())).thenReturn(RevenueScenarioFactory.REVENUE_ONE);

        RevenueResponse expenseUpdate = revenueUpdateUseCase.revenueUpdate(1L, RevenueScenarioFactory.REVENUE_REQUEST);

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(expenseUpdate);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("revenueUpdate", logsList.get(0).getMessage());
    }

    @Test
    public void expenseUpdateUseCase_WhenExpenseExist_ThenUpdateException() {

        when(eIRevenue.findById(anyLong())).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class,
                () -> revenueUpdateUseCase.revenueUpdate(1L, RevenueScenarioFactory.REVENUE_REQUEST));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("revenueUpdate", logsList.get(0).getMessage());

    }
    
}
