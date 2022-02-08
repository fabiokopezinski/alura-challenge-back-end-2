package br.com.alura.control.financeiro.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.control.financeiro.core.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    
    Page<Expense> findAll(Pageable page);

    Optional<Expense> findById(Long id);

    Optional<Expense> findByDescriptionAndValue(String description,BigDecimal value);

    List<Expense> findByDescription(String description);

    @Query(value = "SELECT e FROM Expense e WHERE TO_CHAR(e.data,'MM-YYYY') = ?1")
    List<Expense> findByData(String data);

    @Query(value = "DELETE FROM Expense e WHERE e.description = ?1 and e.value = ?2")
    Optional<Expense> deleteByDescriptionAndValue(String description,String value);
}
