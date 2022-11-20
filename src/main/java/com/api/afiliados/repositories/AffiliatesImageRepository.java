package com.api.afiliados.repositories;

import com.api.afiliados.models.AffiliatesImageModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliatesImageRepository extends JpaRepository<AffiliatesImageModel, Integer> {
    //deve declarar os novos metodos abaixo caso não existão por padrão no Jpa
    @Query(value = "SELECT id FROM TB_IMAGE", nativeQuery = true)
    List<Integer> selectAllIdImage();
}
