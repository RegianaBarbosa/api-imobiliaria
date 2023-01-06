package edu.ifma.lpweb.imobiliaria.api.dto.input;


import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter @Setter
public class ClienteRequest {
    @NotNull
    private Integer id;
}