package br.com.alura.control.financeiro.core.usecase.revenue;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.mapper.RevenueModelMapper;
import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;
import br.com.alura.control.financeiro.validations.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class RevenueUpdateUseCase {
    
    private IRevenue revenueGateway;

    @Validated(OnUpdate.class)
    @Transactional
    public RevenueResponse revenueUpdate(Long id, @Valid RevenueRequest revenueRequest){

        log.info("revenueUpdate");

        Revenue revenue = revenueGateway.findById(id);

        revenue.update(revenueRequest);

        return RevenueModelMapper.INSTANCE.entityToResponse(revenueGateway.update(id, revenue));
    }
}
