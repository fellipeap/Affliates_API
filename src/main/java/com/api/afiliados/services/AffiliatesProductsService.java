package com.api.afiliados.services;

import com.api.afiliados.models.AffiliatesProductsModel;
import com.api.afiliados.repositories.AffiliatesProductsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AffiliatesProductsService {
    final AffiliatesProductsRepository afiliadosProdutoRepository;

    public AffiliatesProductsService(AffiliatesProductsRepository afiliadosProdutoRepository) {
        this.afiliadosProdutoRepository = afiliadosProdutoRepository;
    }

    @Transactional
    public AffiliatesProductsModel save(AffiliatesProductsModel afiliadosProdutoModel) {
        return afiliadosProdutoRepository.save(afiliadosProdutoModel);
    }

    public Page<AffiliatesProductsModel> findAll(Pageable pageable) {
        return afiliadosProdutoRepository.findAll(pageable);
    }
/*
    public List<AffiliatesProductsModel> findAllCourse(String tipo) {
        return afiliadosProdutoRepository.findAllCourse(tipo);
    }*/

    public Optional<AffiliatesProductsModel> findById(Integer id) {
        return afiliadosProdutoRepository.findById(id);
    }

    @Transactional
    public void delete(AffiliatesProductsModel afiliadosProdutoModel) {
        afiliadosProdutoRepository.delete(afiliadosProdutoModel);
    }

    public List<AffiliatesProductsModel> buscaPorCategoria(String categoria) {
       return afiliadosProdutoRepository.findByCategoria(categoria);
    }
}
