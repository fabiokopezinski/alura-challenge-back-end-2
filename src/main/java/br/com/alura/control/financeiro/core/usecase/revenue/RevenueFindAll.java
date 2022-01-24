package br.com.alura.control.financeiro.core.usecase.revenue;

import org.springframework.data.domain.Page;

import br.com.alura.control.financeiro.core.entities.Revenue;

public interface RevenueFindAll {
    Page<Revenue> findAll(int limit,int offset);
}
