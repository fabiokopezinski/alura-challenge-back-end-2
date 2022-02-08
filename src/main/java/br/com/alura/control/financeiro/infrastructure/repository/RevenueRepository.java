package br.com.alura.control.financeiro.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.control.financeiro.core.entities.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue,Long> {
    
    Page<Revenue> findAll(Pageable page);

    Optional<Revenue> findById(Long id);

    Optional<Revenue> findByDescriptionAndValueAndData(String description,BigDecimal value);

    List<Revenue> findByDescription(String description);

    @Query(value = "SELECT e FROM Revenue e WHERE TO_CHAR(e.data,'MM-YYYY') = ?1")
    List<Revenue> findByData(String data);

    @Query(value = "DELETE FROM Revenue e WHERE e.description = ?1 and e.value = ?2")
    Optional<Revenue> deleteByDescriptionAndValue(String description,String value);

}
