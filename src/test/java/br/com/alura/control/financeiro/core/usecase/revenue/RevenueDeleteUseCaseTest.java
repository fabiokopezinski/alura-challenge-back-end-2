package br.com.alura.control.financeiro.core.usecase.revenue;

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

import br.com.alura.control.financeiro.exceptions.BusinessException;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class RevenueDeleteUseCaseTest {

    @InjectMocks
    private RevenueDeleteUseCase revenueDeleteUseCase;

    @Mock
    private IRevenue iRevenue;

    Logger logger = (Logger) LoggerFactory.getLogger(RevenueDeleteUseCase.class);

    ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void revenueDeleteUseCase_WhenExiste_ThenDelete(){

        doNothing().when(iRevenue).delete(1L);

        revenueDeleteUseCase.revenueDelete(1L);

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("revenueDelete", logsList.get(0).getFormattedMessage());
    }

    @Test
    public void revenueDeleteUseCase_WhenNotExist_ThenDelete(){

        doThrow(BusinessException.class).when(iRevenue).delete(1L);

        assertThrows(BusinessException.class, ()->revenueDeleteUseCase.revenueDelete(1L));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("revenueDelete", logsList.get(0).getFormattedMessage());
    }

}
