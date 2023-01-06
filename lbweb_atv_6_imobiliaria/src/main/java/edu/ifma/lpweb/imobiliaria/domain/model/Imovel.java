package edu.ifma.lpweb.imobiliaria.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Imovel {
    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String tipo;

    @NotBlank
    private String endereco;

    @NotBlank @Size(min= 8)
    private String cep;

    private int dormitorios;

    private int banheiros;

    private int suites;

    @NotBlank
    private int metragem;

    @NotBlank
    private double valor;

    private String obs;
}
