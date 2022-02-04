package br.com.alura.control.financeiro.core.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.alura.control.financeiro.enums.CategoryEnum;
import br.com.alura.control.financeiro.validations.OnCreate;
import br.com.alura.control.financeiro.validations.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ExpenseRequest {
    
    @NotBlank(groups = { OnCreate.class, OnUpdate.class }, message = "O description está inválido")
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class }, message = "O description está inválido")
    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O description está inválido")
    private String description;

    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O valor está inválido")
    private BigDecimal value;

    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O data está inválido")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using=LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate data;

    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O category está inválido")
    private CategoryEnum category;
}
