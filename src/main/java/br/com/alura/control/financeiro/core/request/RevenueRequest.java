package br.com.alura.control.financeiro.core.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class RevenueRequest {
    
    @NotBlank(groups = { OnCreate.class, OnUpdate.class }, message = "O description está inválido")
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class }, message = "O description está inválido")
    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O description está inválido")
    private String description;

    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O valor está inválido")
    private BigDecimal value;

    @NotBlank(groups = { OnCreate.class, OnUpdate.class }, message = "O data está inválido")
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class }, message = "O data está inválido")
    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O data está inválido")
    private String data;
}
