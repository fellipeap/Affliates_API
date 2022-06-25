package com.api.afiliados.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRODUTOS")
public class AffiliatesProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint unsigned")//Tipo serial, lembrar de verificar caso ouver erro
    private int id;
    @Column(nullable = false, length = 250)
    private String descricao;
    @Column(nullable = false, columnDefinition = "text")
    private String inf_complementar;
    @Column(columnDefinition = "decimal(8,2) default '0.00'")
    private BigDecimal preco;
    @Column(columnDefinition = "varchar(1) default 'I'")
    private String categoria;
    @URL
    @Column(nullable = false)
    private String link_venda;
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime registrationDate = LocalDateTime.now();
    @Column(nullable = false, columnDefinition = "bigint unsigned")
    private int id_imagem;

}
