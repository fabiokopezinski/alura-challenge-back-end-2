package br.com.alura.control.financeiro.core.usecase.revenue;

import org.springframework.stereotype.Service;

import br.com.alura.control.financeiro.core.mapper.RevenueModelMapper;
import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class RevenueSaveUseCase {

    private RevenueSave revenueSave;

    public RevenueResponse save(RevenueRequest revenueRequest) {

        log.info("save");

        return RevenueModelMapper.INSTANCE
                .entityToResponse(revenueSave.revenueSave(RevenueModelMapper.INSTANCE.requestToEntity(revenueRequest)));
    }
}
