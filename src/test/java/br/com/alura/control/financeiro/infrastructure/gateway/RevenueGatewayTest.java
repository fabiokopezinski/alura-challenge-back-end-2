package br.com.alura.control.financeiro.infrastructure.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.exceptions.BusinessException;
import br.com.alura.control.financeiro.feature.RevenueScenarioFactory;
import br.com.alura.control.financeiro.infrastructure.repository.RevenueRepository;
import br.com.alura.control.financeiro.validations.Message;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(MockitoExtension.class)
public class RevenueGatewayTest {

    @InjectMocks
    private RevenueGateway revenueGateway;

    @Mock
    private RevenueRepository revenueRepository;

    Logger logger = (Logger) LoggerFactory.getLogger(RevenueGateway.class);

    ListAppender<ILoggingEvent> loggers = new ListAppender<>();

    @BeforeEach
    public void beforeEach() {

        loggers.start();

        logger.addAppender(loggers);
    }

    @Test
    public void findAll_WhenExistAnyRevenue_ThenReturnList() {

        when(revenueRepository.findAll(any(Pageable.class))).thenReturn(RevenueScenarioFactory.REVENUE_FIND_ALL);

        Page<Revenue> findAll = revenueGateway.findAll(2, 0);

        assertNotNull(findAll);

        verify(revenueRepository).findAll(any(Pageable.class));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("limit=2 offset=0", logsList.get(0).getFormattedMessage());

    }

    @Test
    public void findById_WhenExisted_ThenReturnRevenue() {

        when(revenueRepository.findById(anyLong())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE_ONE));

        Revenue findById = revenueGateway.findById(1L);

        assertNotNull(findById);

        verify(revenueRepository).findById(anyLong());

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1", logsList.get(0).getFormattedMessage());
    }

    @Test
    public void findById_WhenNotExisted_ThenReturnException() {

        when(revenueRepository.findById(anyLong())).thenThrow(Message.NOT_FOUND_REVENUE.asBusinessException());

        assertThrows(BusinessException.class, () -> revenueGateway.findById(1L));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1", logsList.get(0).getFormattedMessage());

    }

    @Test
    public void revenueSave_WhenRevenueNotExist_ThenSaveOk() {

        when(revenueRepository.findByDescriptionAndValueAndData(anyString(), any())).thenReturn(Optional.empty());

        when(revenueRepository.save(any())).thenReturn(RevenueScenarioFactory.REVENUE_ONE);

        Revenue revenueSave = revenueGateway.revenueSave(RevenueScenarioFactory.REVENUE_ONE);

        List<ILoggingEvent> logsList = loggers.list;

        assertNotNull(revenueSave);

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("revenue = ".concat("" + RevenueScenarioFactory.REVENUE_ONE.toString()),
                logsList.get(0).getFormattedMessage());
    }

    @Test
    public void revenueSave_WhenRevenueExist_ThenReturnException() {

        when(revenueRepository.findByDescriptionAndValueAndData(anyString(), any()))
                .thenReturn(Optional.of(RevenueScenarioFactory.REVENUE_ONE));

        assertThrows(BusinessException.class, () -> revenueGateway.revenueSave(RevenueScenarioFactory.REVENUE_ONE));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("revenue = ".concat("" + RevenueScenarioFactory.REVENUE_ONE.toString()),
                logsList.get(0).getFormattedMessage());
    }

    @Test
    public void delete_WhenRevenueExist_ThenDelete() {

        when(revenueRepository.findById(any())).thenReturn(Optional.of(RevenueScenarioFactory.REVENUE_ONE));

        doNothing().when(revenueRepository).deleteById(any());

        revenueGateway.delete(1L);

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1", logsList.get(0).getFormattedMessage());
    }

    @Test
    public void delete_WhenRevenueNotExist_ThenReturnException() {

        when(revenueRepository.findById(any())).thenThrow(Message.NOT_FOUND_REVENUE.asBusinessException());

        assertThrows(BusinessException.class, () -> revenueGateway.delete(1L));

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1", logsList.get(0).getFormattedMessage());
    }

    @Test
    public void findByDescription_ThenReturn() {

        when(revenueRepository.findByDescription(any())).thenReturn(RevenueScenarioFactory.REVENUE_FIND_ALL.toList());

        List<Revenue> findByDescription = revenueGateway.findByDescription("ReceitaUm");

        assertNotNull(findByDescription);

        verify(revenueRepository).findByDescription(anyString());

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("description=ReceitaUm", logsList.get(0).getFormattedMessage());

    }

    @Test
    public void findByData_ThenReturn() {
        when(revenueRepository.findByData(any())).thenReturn(RevenueScenarioFactory.REVENUE_FIND_ALL.toList());

        List<Revenue> findByData = revenueGateway.findByData("10-2021");

        assertNotNull(findByData);

        verify(revenueRepository).findByData(anyString());

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("data=10-2021", logsList.get(0).getFormattedMessage());
    }

    @Test
    public void findByAll_ThenReturn() {

        when(revenueRepository.findAll()).thenReturn(RevenueScenarioFactory.REVENUE_FIND_ALL.toList());

        List<Revenue> findAll = revenueGateway.findAll();

        assertNotNull(findAll);

        verify(revenueRepository).findAll();
    }

    @Test
    public void update_WhenExisted_thenReturnExpense(){

        Revenue update = revenueGateway.update(1L, RevenueScenarioFactory.REVENUE_ONE);

        assertNotNull(update);

        List<ILoggingEvent> logsList = loggers.list;

        assertEquals(1, logsList.size());

        assertEquals(Level.INFO, logsList.get(0).getLevel());

        assertEquals("id=1".concat(" revenue=").concat(update.toString()), logsList.get(0).getFormattedMessage());

    }

}
