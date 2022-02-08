package br.com.alura.control.financeiro.core.usecase.revenue;

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

import br.com.alura.control.financeiro.core.response.RevenueResponse;
import br.com.alura.control.financeiro.feature.RevenueScenarioFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class RevenueFindByDataUseCaseTest {
    
    @InjectMocks
    private RevenueFindByDataUseCase revenueFindByDataUseCase;

    @Mock
    private IRevenue revenue;

    private Logger logger = (Logger) LoggerFactory.getLogger(RevenueFindByDataUseCase.class);

    private ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void revenueFindByDataUseCase_ThenReturn() {

        when(revenue.findByData(anyString())).thenReturn(RevenueScenarioFactory.REVENUE_FIND_ALL.toList());

        List<RevenueResponse> findByDate = revenueFindByDataUseCase.findByData("10", "2022");

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(findByDate);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("findByDate", logsList.get(0).getMessage());

        verify(revenue).findByData(anyString());
    }
}
