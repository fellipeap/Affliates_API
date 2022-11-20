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
    final AffiliatesProductsRepository affiliatesProductRepository;

    public AffiliatesProductsService(AffiliatesProductsRepository affiliatesProductRepository) {
        this.affiliatesProductRepository = affiliatesProductRepository;
    }

    @Transactional
    public AffiliatesProductsModel save(AffiliatesProductsModel affiliatesProductModel) {
        return affiliatesProductRepository.save(affiliatesProductModel);
    }

    public Page<AffiliatesProductsModel> findAll(Pageable pageable) {
        return affiliatesProductRepository.findAll(pageable);
    }

    public Optional<AffiliatesProductsModel> findById(Integer id) {
        return affiliatesProductRepository.findById(id);
    }

    public List<AffiliatesProductsModel> findAllIdMasterProducts(Integer idMasterProducts){
        return affiliatesProductRepository.findAllIdMasterProducts(idMasterProducts);
    }

    @Transactional
    public void delete(AffiliatesProductsModel affiliatesProductModel) {
        affiliatesProductRepository.delete(affiliatesProductModel);
    }

}
