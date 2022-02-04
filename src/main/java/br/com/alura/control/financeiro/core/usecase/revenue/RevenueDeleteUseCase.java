package br.com.alura.control.financeiro.core.usecase.revenue;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class RevenueDeleteUseCase {
    
    private IRevenue revenueDelete;

    public void revenueDelete(Long id){

        log.info("revenueDelete");

        this.revenueDelete.delete(id);
    }
}
