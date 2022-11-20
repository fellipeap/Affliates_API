package com.api.afiliados.services;

import com.api.afiliados.models.AffiliatesMasterProductsModel;
import com.api.afiliados.repositories.AffiliatesMasterProductsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AffiliatesMasterProductsService {
    final AffiliatesMasterProductsRepository affiliatesMasterProductRepository;

    public AffiliatesMasterProductsService(AffiliatesMasterProductsRepository affiliatesMasterProductsRepository) {
        this.affiliatesMasterProductRepository = affiliatesMasterProductsRepository;
    }

    @Transactional
    public AffiliatesMasterProductsModel save(AffiliatesMasterProductsModel affiliatesMasterProductsModel){
        return affiliatesMasterProductRepository.save(affiliatesMasterProductsModel);
    }

    public Page<AffiliatesMasterProductsModel> findAll(Pageable pageable){
        return affiliatesMasterProductRepository.findAll(pageable);
    }

    public Optional<AffiliatesMasterProductsModel> findById(Integer id){
        return affiliatesMasterProductRepository.findById(id);
    }

    @Transactional
    public void delete(AffiliatesMasterProductsModel affiliatesMasterProductsModel){
        affiliatesMasterProductRepository.delete(affiliatesMasterProductsModel);
    }

}
