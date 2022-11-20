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
@Table(name = "TB_MASTER_PRODUCTS")
public class AffiliatesMasterProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint unsigned")//Tipo serial, lembrar de verificar caso ouver erro
    private int id;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(nullable = false, columnDefinition = "text")
    private String additionalInformation;
    @Column(columnDefinition = "decimal(8,2) default '0.00'")
    private BigDecimal price;
    @URL
    @Column(nullable = false)
    private String linkCheckOut;
    @Column
    private String linkBtnMore;
    @Column(nullable = false, columnDefinition = "bigint unsigned")
    private int idImage;
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime registrationDate = LocalDateTime.now();
}
