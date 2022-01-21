package br.com.alura.control.financeiro.infrastructure.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.control.financeiro.core.entities.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue,Long> {
    
    Page<Revenue> findAll(Pageable page);

    Optional<Revenue> findById(Long id);

    Optional<Revenue> findByDescriptionAndValueAndData(String description,BigDecimal value,String data);

}
