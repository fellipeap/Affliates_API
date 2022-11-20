package com.api.afiliados.cotrollers;

import com.api.afiliados.dtos.AffiliatesProductsDto;
import com.api.afiliados.models.AffiliatesProductsModel;
import com.api.afiliados.services.AffiliatesProductsService;
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
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/master/products")
public class AffiliatesProductsController {
    final AffiliatesProductsService affiliatesProductsService;

    public AffiliatesProductsController(AffiliatesProductsService affiliatesProductService){
        this.affiliatesProductsService = affiliatesProductService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> saveAffiliatesProduct(@RequestBody AffiliatesProductsDto affiliatesProductsDto){
        var affiliatesProductsModel = new AffiliatesProductsModel();
        BeanUtils.copyProperties(affiliatesProductsDto, affiliatesProductsModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(affiliatesProductsService.save(affiliatesProductsModel));
    }

    @GetMapping
    public ResponseEntity<Page<AffiliatesProductsModel>> getAffiliatesProducts(@PageableDefault(page = 0, size = 50, sort = "id",
                                                                             direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(affiliatesProductsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAffiliatesProduct(@PathVariable(value = "id") Integer id){
        Optional<AffiliatesProductsModel> affiliatesProductModelOptional = affiliatesProductsService.findById(id);
        if(!affiliatesProductModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(affiliatesProductModelOptional.get());
    }

    @GetMapping("/masterid/{idMasterProducts}")
    public List<AffiliatesProductsModel> listProductsByMasterId(@PathVariable("idMasterProducts") Integer idMasterProducts){
        return affiliatesProductsService.findAllIdMasterProducts(idMasterProducts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAffiliatesProduct(@PathVariable(value = "id") Integer id){
        Optional<AffiliatesProductsModel> affiliatesProductModelOptional = affiliatesProductsService.findById(id);
        if(!affiliatesProductModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        affiliatesProductsService.delete(affiliatesProductModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAffiliatesProduct(@PathVariable(value = "id") Integer id,
                                                          @RequestBody @Valid AffiliatesProductsDto afiliadosProdutoDto){
        Optional<AffiliatesProductsModel> affiliatesProductModelOptional = affiliatesProductsService.findById(id);
        if(!affiliatesProductModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        var affiliatesProductModel = new AffiliatesProductsModel();
        BeanUtils.copyProperties(afiliadosProdutoDto,affiliatesProductModel);
        affiliatesProductModel.setId(affiliatesProductModelOptional.get().getId());
        affiliatesProductModel.setRegistrationDate(affiliatesProductModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(affiliatesProductsService.save(affiliatesProductModel));
    }

}
