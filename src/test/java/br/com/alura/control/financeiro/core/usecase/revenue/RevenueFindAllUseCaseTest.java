package br.com.alura.control.financeiro.core.usecase.revenue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import br.com.alura.control.financeiro.core.response.RevenueResponse;
import br.com.alura.control.financeiro.feature.RevenueScenarioFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class RevenueFindAllUseCaseTest {

    @InjectMocks
    private RevenueFindAllUseCase revenueFindAllUseCase;

    @Mock
    private IRevenue eIRevenue;

    private Logger logger = (Logger) LoggerFactory.getLogger(RevenueFindAllUseCase.class);

    private ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void revenueFindAllUseCase_ThenReturn() {

        when(eIRevenue.findAll(anyInt(), anyInt())).thenReturn(RevenueScenarioFactory.REVENUE_FIND_ALL);

        List<RevenueResponse> findAll = revenueFindAllUseCase.findAll(2, 0);

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(findAll);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("findAll", logsList.get(0).getMessage());

        verify(eIRevenue).findAll(anyInt(), anyInt());
    }

}
