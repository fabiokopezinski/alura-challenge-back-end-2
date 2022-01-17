package br.com.alura.control.financeiro.core.mapper;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.core.entities.Expense.ExpenseBuilder;
import br.com.alura.control.financeiro.core.request.ExponseRequest;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import br.com.alura.control.financeiro.core.response.ExpenseResponse.ExpenseResponseBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-17T19:55:44-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class ExpenseModelMapperImpl implements ExpenseModelMapper {

    @Override
    public ExpenseResponse entityToResponse(Expense expense) {
        if ( expense == null ) {
            return null;
        }

        ExpenseResponseBuilder expenseResponse = ExpenseResponse.builder();

        expenseResponse.data( expense.getData() );
        expenseResponse.description( expense.getDescription() );
        expenseResponse.id( expense.getId() );
        expenseResponse.valor( expense.getValor() );

        return expenseResponse.build();
    }

    @Override
    public Expense requestToEntity(ExponseRequest request) {
        if ( request == null ) {
            return null;
        }

        ExpenseBuilder expense = Expense.builder();

        expense.data( request.getData() );
        expense.description( request.getDescription() );
        expense.valor( request.getValor() );

        return expense.build();
    }
}
