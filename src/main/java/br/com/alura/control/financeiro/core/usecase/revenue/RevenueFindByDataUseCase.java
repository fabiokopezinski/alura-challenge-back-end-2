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
public class RevenueFindByDataUseCase {

    private IRevenue revenue;

    public List<RevenueResponse> findByData(String month, String year) {

        log.info("findByDate");

        return RevenueModelMapper.INSTANCE.listEntityToListResponse(this.revenue.findByData(month.concat("-" + year)));
    }

}
