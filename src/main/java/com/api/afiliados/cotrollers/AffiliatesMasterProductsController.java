package com.api.afiliados.cotrollers;

import com.api.afiliados.dtos.AffiliatesMasterProductsDto;
import com.api.afiliados.models.AffiliatesMasterProductsModel;
import com.api.afiliados.services.AffiliatesMasterProductsService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/master")
public class AffiliatesMasterProductsController {
    final AffiliatesMasterProductsService affiliatesMasterProductsService;

    public AffiliatesMasterProductsController(AffiliatesMasterProductsService affiliatesMasterProductsService) {
        this.affiliatesMasterProductsService = affiliatesMasterProductsService;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<Object> saveAffiliatesMasterProduct(@RequestBody AffiliatesMasterProductsDto masterProductsDto){
        var affiliatesMasterProduct = new AffiliatesMasterProductsModel();
        System.out.println("Cadastrando produto mestre....");
        BeanUtils.copyProperties(masterProductsDto, affiliatesMasterProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(affiliatesMasterProductsService.save(affiliatesMasterProduct));
    }

    @GetMapping
    public ResponseEntity<Page<AffiliatesMasterProductsModel>> getAffiliatesMasterProducts(@PageableDefault(page = 0, size = 50, sort = "id",
                                                                                           direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(affiliatesMasterProductsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAffiliatesMasterProduct(@PathVariable(value = "id") Integer id){
        Optional<AffiliatesMasterProductsModel> affiliatesMasterProductsModelOptional = affiliatesMasterProductsService.findById(id);
        if(!affiliatesMasterProductsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Mestre não Encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(affiliatesMasterProductsModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAffiliatesMasterProduct(@PathVariable(value = "id") Integer id){
        Optional<AffiliatesMasterProductsModel> affiliatesMasterProductsModelOptional = affiliatesMasterProductsService.findById(id);
        if(!affiliatesMasterProductsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Mestre não Encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAffiliatesMasterProduct(@PathVariable(value = "id") Integer id,
                                                                @RequestBody @Valid AffiliatesMasterProductsDto affiliatesMasterProductsDto){
        Optional<AffiliatesMasterProductsModel> affiliatesMasterProductsModelOptional = affiliatesMasterProductsService.findById(id);
        if(!affiliatesMasterProductsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Mestre não Encontrado.");
        }
        var affiliatesMasterProductModel = new AffiliatesMasterProductsModel();
        BeanUtils.copyProperties(affiliatesMasterProductsDto, affiliatesMasterProductModel);
        affiliatesMasterProductModel.setId(affiliatesMasterProductsModelOptional.get().getId());
        affiliatesMasterProductModel.setRegistrationDate(affiliatesMasterProductsModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(affiliatesMasterProductsService.save(affiliatesMasterProductModel));
    }
}
