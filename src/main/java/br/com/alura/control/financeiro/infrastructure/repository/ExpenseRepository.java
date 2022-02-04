package br.com.alura.control.financeiro.infrastructure.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.control.financeiro.core.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    
    Page<Expense> findAll(Pageable page);

    Optional<Expense> findById(Long id);

    Optional<Expense> findByDescriptionAndValueAndData(String description,BigDecimal value,LocalDate data);
}
