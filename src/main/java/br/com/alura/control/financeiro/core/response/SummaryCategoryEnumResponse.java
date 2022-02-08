package br.com.alura.control.financeiro.core.response;

import br.com.alura.control.financeiro.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Setter
public class SummaryCategoryEnumResponse {
    
    private CategoryEnum category;

    private Double value;
}
