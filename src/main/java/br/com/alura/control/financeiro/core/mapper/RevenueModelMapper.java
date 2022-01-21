package br.com.alura.control.financeiro.core.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;

@Mapper
public interface RevenueModelMapper {

    RevenueModelMapper INSTANCE = Mappers.getMapper(RevenueModelMapper.class);
  
    Revenue requestToEntity(RevenueRequest request);

    List<RevenueResponse> allEntityToAllResponse(Page<Revenue> page);

    RevenueResponse entityToResponse(Revenue response);
}
