package com.api.afiliados.repositories;

import com.api.afiliados.models.AffiliatesMasterProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliatesMasterProductsRepository extends JpaRepository<AffiliatesMasterProductsModel, Integer> {
}
