package com.api.afiliados.repositories;

import com.api.afiliados.models.AffiliatesProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliatesProductsRepository extends JpaRepository<AffiliatesProductsModel, Integer> {
    //deve declarar os novos metodos abaixo caso não existão por padrão no Jpa

   // @Query("select p.descricao from TB_PRODUTOS p where p.tpo_cadastro = :tipo")
    //List<AffiliatesProductsModel> findAllCourse(@Param("tipo") String tipo);

    List<AffiliatesProductsModel> findByCategoria(String categoria);
}
