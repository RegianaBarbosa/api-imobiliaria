package edu.ifma.lpweb.imobiliaria.domain.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Locacao {

    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
	@ManyToOne
    private Cliente inquilino;
    
    @NotNull
	@ManyToOne
    private Imovel imovel;

    @NotBlank
    private boolean ativo;

    @NotBlank @DateTimeFormat
    private Date dt_inicio;

    @NotBlank @DateTimeFormat
    private Date dt_fim;

    @NotBlank
    private int dia_vencimento;

    @NotBlank
    private double perc_multa;

    @NotBlank
    private double valor_aluguel;

    private String obs;

}
