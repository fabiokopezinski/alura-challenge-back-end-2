package br.com.alura.control.financeiro.core.usecase.revenue;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.alura.control.financeiro.core.mapper.RevenueModelMapper;
import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;
import br.com.alura.control.financeiro.validations.OnCreate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class RevenueSaveUseCase {

    private IRevenue revenueSave;

    @Validated(OnCreate.class)
    public RevenueResponse save(@Valid RevenueRequest revenueRequest) {

        log.info("save");

        return RevenueModelMapper.INSTANCE
                .entityToResponse(revenueSave.revenueSave(RevenueModelMapper.INSTANCE.requestToEntity(revenueRequest)));
    }
}
