package com.api.afiliados.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRODUCTS")
public class AffiliatesProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint unsigned")//Tipo serial, lembrar de verificar caso ouver erro
    private int id;
    @Column(nullable = false, columnDefinition = "bigint unsigned")
    private int IdMasterProducts;
    @Column(nullable = false, length = 250)
    private String description;
    @Column(nullable = false, columnDefinition = "bigint unsigned")
    private int idImage;
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime registrationDate = LocalDateTime.now();

}
