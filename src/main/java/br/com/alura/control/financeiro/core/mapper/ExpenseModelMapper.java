package br.com.alura.control.financeiro.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.core.request.ExponseRequest;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;

@Mapper
public interface ExpenseModelMapper {

    ExpenseModelMapper INSTANCE = Mappers.getMapper(ExpenseModelMapper.class);

    ExpenseResponse entityToResponse(Expense expense);

    Expense requestToEntity(ExponseRequest request);
}
