package br.com.alura.control.financeiro.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;

@Mapper
public interface RevenueModelMapper {

    RevenueModelMapper INSTANCE = Mappers.getMapper(RevenueModelMapper.class);

    Revenue requestToEntity(RevenueRequest request);

    RevenueResponse entityToResponse(RevenueResponse response);
}
