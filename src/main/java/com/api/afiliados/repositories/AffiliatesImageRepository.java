package com.api.afiliados.repositories;

import com.api.afiliados.models.AffiliatesImageModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliatesImageRepository extends JpaRepository<AffiliatesImageModel, Integer> {
    //deve declarar os novos metodos abaixo caso não existão por padrão no Jpa
}
