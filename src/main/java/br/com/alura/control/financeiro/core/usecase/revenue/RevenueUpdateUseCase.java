package br.com.alura.control.financeiro.core.usecase.revenue;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.mapper.RevenueModelMapper;
import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class RevenueUpdateUseCase {
    
    private RevenueUpdate revenueUpdate;

    private RevenueFindById revenueFindById;

    public RevenueResponse revenueUpdate(Long id, RevenueRequest revenueRequest){

        log.info("revenueUpdate");

        Revenue revenue = revenueFindById.findById(id);

        revenue.update(revenueRequest);

        return RevenueModelMapper.INSTANCE.entityToResponse(revenueUpdate.update(id, revenue));
    }
}
