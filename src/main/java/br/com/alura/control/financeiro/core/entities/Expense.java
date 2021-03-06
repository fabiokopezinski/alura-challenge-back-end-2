package br.com.alura.control.financeiro.core.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.alura.control.financeiro.core.request.ExpenseRequest;
import br.com.alura.control.financeiro.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "tb_despesas")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_despesas_id_seq")
    @SequenceGenerator(name = "tb_despesas_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "descricao", nullable = false, columnDefinition = "CHARACTER VARYING(255)")
    private String description;

    @Column(name = "valor", nullable = false, columnDefinition = "NUMERIC(12,3)", precision = 12, scale = 3)
    private BigDecimal value;

    @Column(name = "data", nullable = false, columnDefinition = "CHARACTER VARYING")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false, columnDefinition = "CHARACTER VARYING")
    private CategoryEnum category;


    public void update(ExpenseRequest expenseRequest) {

        this.description = expenseRequest.getDescription();
        this.value = expenseRequest.getValue();
        this.data = expenseRequest.getData();
        this.category=expenseRequest.getCategory();
    }
}
