package br.com.alura.control.financeiro.core.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.core.request.ExpenseRequest;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;

@Mapper
public interface ExpenseModelMapper {

    ExpenseModelMapper INSTANCE = Mappers.getMapper(ExpenseModelMapper.class);

    ExpenseResponse entityToResponse(Expense expense);

    List<ExpenseResponse> allEntityToallResponse(Page<Expense> page);

    Expense requestToEntity(ExpenseRequest request);
}
