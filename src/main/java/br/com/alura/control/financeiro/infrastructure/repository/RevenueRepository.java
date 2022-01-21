package br.com.alura.control.financeiro.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.control.financeiro.core.entities.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue,Long> {
    
}
