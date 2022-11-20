package com.api.afiliados.services;

import com.api.afiliados.models.AffiliatesImageModel;
import com.api.afiliados.repositories.AffiliatesImageRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AffiliatesImageService {
    final AffiliatesImageRepository affiliatesImageRepository;

    public AffiliatesImageService(AffiliatesImageRepository affiliatesImageRepository){
        this.affiliatesImageRepository = affiliatesImageRepository;
    }

    @Transactional
    public AffiliatesImageModel save(AffiliatesImageModel affiliatesImageModel){
        return affiliatesImageRepository.save(affiliatesImageModel);
    }

    public Optional<AffiliatesImageModel> findById(Integer id) {
        return  affiliatesImageRepository.findById(id);
    }

    public List<Integer> ListIdImg() {
        return affiliatesImageRepository.selectAllIdImage();
    }
}
