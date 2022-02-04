package br.com.alura.control.financeiro.core.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.alura.control.financeiro.core.request.RevenueRequest;
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
@Table(name = "tb_receitas")
public class Revenue {
    
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_receitas_id_seq")
	@SequenceGenerator(name = "tb_receitas_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Long id;

    @Column(name = "descricao", nullable = false, columnDefinition = "CHARACTER VARYING(255)")
    private String description;

    @Column(name = "valor", nullable = false, columnDefinition = "NUMERIC(12,3)",precision = 12,scale = 3)
    private BigDecimal value;

    @Column(name = "data", nullable = false, columnDefinition = "CHARACTER VARYING")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using=LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private LocalDate data;

    public void update(RevenueRequest revenue) {

        this.description = revenue.getDescription();
        this.value = revenue.getValue();
        this.data = revenue.getData();
    }
}
