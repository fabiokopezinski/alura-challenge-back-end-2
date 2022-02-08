package br.com.alura.control.financeiro.core.usecase.revenue;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.alura.control.financeiro.core.entities.Revenue;

public interface IRevenue {
    void delete(Long id);
    Page<Revenue> findAll(int limit,int offset);
    Revenue findById(Long id);
    Revenue revenueSave(Revenue revenue);
    Revenue update(Long id, Revenue revenue);
    List<Revenue> findByDescription(String description);
    List<Revenue> findByData(String data);
    List<Revenue> findAll();
}
