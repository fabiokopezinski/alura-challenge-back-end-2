package br.com.alura.control.financeiro.core.usecase.revenue;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.mapper.RevenueModelMapper;
import br.com.alura.control.financeiro.core.response.RevenueResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class RevenueFindAllUseCase {

    private IRevenue revenueFindAll;

    public List<RevenueResponse> findAll(int limit, int offset) {

        log.info("findAll");

        return RevenueModelMapper.INSTANCE.allEntityToAllResponse(revenueFindAll.findAll(limit, offset));
    }
}
