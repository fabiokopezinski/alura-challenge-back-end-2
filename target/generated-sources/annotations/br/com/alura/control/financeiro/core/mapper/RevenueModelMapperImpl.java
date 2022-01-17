package br.com.alura.control.financeiro.core.mapper;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.entities.Revenue.RevenueBuilder;
import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;
import br.com.alura.control.financeiro.core.response.RevenueResponse.RevenueResponseBuilder;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-17T19:55:39-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class RevenueModelMapperImpl implements RevenueModelMapper {

    @Override
    public Revenue requestToEntity(RevenueRequest request) {
        if ( request == null ) {
            return null;
        }

        RevenueBuilder revenue = Revenue.builder();

        revenue.data( request.getData() );
        revenue.description( request.getDescription() );
        revenue.valor( request.getValor() );

        return revenue.build();
    }

    @Override
    public RevenueResponse entityToResponse(RevenueResponse response) {
        if ( response == null ) {
            return null;
        }

        RevenueResponseBuilder revenueResponse = RevenueResponse.builder();

        revenueResponse.data( response.getData() );
        revenueResponse.description( response.getDescription() );
        revenueResponse.id( response.getId() );
        revenueResponse.valor( response.getValor() );

        return revenueResponse.build();
    }
}
