package br.com.alura.control.financeiro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryEnum {
 
    Alimentacao("Alimentação"),
    Saude("Saúde"),
    Moradia("Moradia"),
    Transporte("Transporte"),
    Educacao("Educação"),
    Lazer("Lazer"),
    Imprevistos("Imprevistos"),
    Outras("Outras");

    
    private String categoria;    
}
