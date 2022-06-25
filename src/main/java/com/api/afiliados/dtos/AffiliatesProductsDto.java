package com.api.afiliados.dtos;

import com.api.afiliados.validation.TpoCadastro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffiliatesProductsDto {
    @NotBlank(message = "O preechimento do campo DESCRIÇÃO é obrigatório")
    @Size(max = 230, message = "A DESCRIÇÃO não deve exceder 230 caracteres.")
    private String descricao;
    @NotBlank(message = "O preechimento do campo INFORMAÇÃO COMPLEMENTAR é obrigatório")
    @Size(max = 500, message = "A INFORMAÇÃO COMPLEMENTAR não deve exceder 500 caracteres.")
    private String inf_complementar;
    @DecimalMin(value = "0.00", message = "Valor negativo não é permitido.")
    private BigDecimal preco;
    @TpoCadastro(message = "Tipo de cadastro é inválido.")
    private String categoria;
    @NotBlank(message = "Este campo de URL deve ser preechido")
    @Size(max = 255, message = "A Url ou Link informado é muito grande, utilize o encurtador de url ou utilize outro link")
    private String link_venda;
}
