package br.com.alura.control.financeiro.infrastructure.gateway;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.control.financeiro.infrastructure.repository.RevenueRepository;

@ExtendWith(MockitoExtension.class)
public class RevenueGatewayTest {

    @InjectMocks
    private RevenueGateway expenseGateway;

    @Mock
    private RevenueRepository expenseRepository;

    
}
