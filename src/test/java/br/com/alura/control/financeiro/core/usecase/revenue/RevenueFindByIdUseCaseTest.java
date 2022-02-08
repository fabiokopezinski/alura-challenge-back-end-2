package br.com.alura.control.financeiro.core.usecase.revenue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
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
import br.com.alura.control.financeiro.exceptions.BusinessException;
import br.com.alura.control.financeiro.feature.RevenueScenarioFactory;
import br.com.alura.control.financeiro.validations.Message;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class RevenueFindByIdUseCaseTest {

    @InjectMocks
    private RevenueFindByIdUseCase revenueFindByIdUseCase;

    @Mock
    private IRevenue revenue;

    private Logger logger = (Logger) LoggerFactory.getLogger(RevenueFindByIdUseCase.class);

    private ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void findByIdUseCase_WhenExist_ThenReturnAExpense(){

        when(revenue.findById(anyLong())).thenReturn(RevenueScenarioFactory.REVENUE_ONE);

        RevenueResponse findById = revenueFindByIdUseCase.findById(1L);

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(findById);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("findById", logsList.get(0).getMessage());

        verify(revenue).findById(anyLong());

    }
    
    @Test
    public void findByIdUseCase_WhenExist_ThenReturnException(){

        when(revenue.findById(anyLong())).thenThrow(Message.NOT_FOUND_REVENUE.asBusinessException());

        List<ILoggingEvent> logsList = loggers.list;
        
        assertThrows(BusinessException.class, ()-> revenueFindByIdUseCase.findById(1L));

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("findById", logsList.get(0).getMessage());

        verify(revenue).findById(anyLong());

    }
    
}
