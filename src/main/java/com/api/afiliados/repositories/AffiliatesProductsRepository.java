package com.api.afiliados.repositories;

import com.api.afiliados.models.AffiliatesProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AffiliatesProductsRepository extends JpaRepository<AffiliatesProductsModel, Integer> {
    //deve declarar os novos metodos abaixo caso não existão por padrão no Jpa
    @Query(value = "SELECT * FROM TB_PRODUCTS as p WHERE p.id_master_products = :idMasterProducts", nativeQuery = true)
    List<AffiliatesProductsModel> findAllIdMasterProducts(Integer idMasterProducts);
}
