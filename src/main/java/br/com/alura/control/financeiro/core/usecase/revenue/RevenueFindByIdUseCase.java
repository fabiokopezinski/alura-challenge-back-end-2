package br.com.alura.control.financeiro.core.usecase.revenue;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.mapper.RevenueModelMapper;
import br.com.alura.control.financeiro.core.response.RevenueResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class RevenueFindByIdUseCase {

    private RevenueFindById revenueFindById;

    public RevenueResponse findById(Long id){

        log.info("findById");

        return RevenueModelMapper.INSTANCE.entityToResponse(revenueFindById.findById(id));
    }
    
}
