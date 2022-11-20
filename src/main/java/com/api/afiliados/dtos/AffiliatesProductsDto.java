package com.api.afiliados.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffiliatesProductsDto {
    @NotBlank(message = "idMasterProduct não informado(ProductsDto)")
    private Integer IdMasterProducts;
    @NotBlank(message = "O preechimento do campo DESCRIÇÃO é obrigatório(ProductsDto)")
    @Size(max = 230, message = "A DESCRIÇÃO não deve exceder 230 caracteres.(ProductsDto)")
    private String description;
    @NotBlank(message = "Id imagem não foi informado(ProductsDto)")
    private Integer idImage;
}