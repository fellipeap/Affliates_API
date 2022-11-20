package com.api.afiliados.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  AffiliatesMasterProductsDto {
    @NotBlank(message = "DESCRIÇÃO não informada(MasterProductsDto)")
    @Size(max = 250, message = "A DESCRIÇÃO não deve exceder 250 caracteres.(MasterProductsDto)")
    private String description;
    @NotBlank(message = "Informação Complementar não informada.(MasterProductsDto)")
    private String additionalInformation;
    @NotBlank(message = "Link Checkout deve ser informado.(MasterProductsDto)")
    private String linkCheckOut;
    @NotBlank(message = "Id imagem não foi informado(MasterProductsDto)")
    private Integer idImage;

    private String linkBtnMore;
    private BigDecimal price;
}