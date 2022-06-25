package com.api.afiliados.cotrollers;

import com.api.afiliados.dtos.AffiliatesProductsDto;
import com.api.afiliados.models.AffiliatesImageModel;
import com.api.afiliados.models.AffiliatesProductsModel;
import com.api.afiliados.services.AffiliatesImageService;
import com.api.afiliados.services.AffiliatesProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/affiliate-products")
public class AffiliatesProductsController {
    final AffiliatesProductsService affiliatesProductsService;
    final AffiliatesImageService affiliatesImageService;

    public AffiliatesProductsController(AffiliatesProductsService afiliadosPrdutoService, AffiliatesImageService affiliatesImageService){
        this.affiliatesProductsService = afiliadosPrdutoService;
        this.affiliatesImageService = affiliatesImageService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> saveAfiliadosProduto(@RequestPart("product") AffiliatesProductsDto afiliadosDto, @RequestParam("file") MultipartFile file){
        var afiliadosProdutoModel = new AffiliatesProductsModel();
        var afiliadosImageModel = new AffiliatesImageModel();
        BeanUtils.copyProperties(afiliadosDto, afiliadosProdutoModel);
        System.out.println("----------=========  "+afiliadosProdutoModel.getCategoria());
        afiliadosProdutoModel.setCategoria(afiliadosProdutoModel.getCategoria().substring(0,1).toUpperCase(Locale.ROOT));

        try {
            afiliadosImageModel.setImagem(file.getBytes());
        } catch (IOException e) {
            log.error("Erro ao processar o arquivo",e);
        }
        afiliadosProdutoModel.setId_imagem(affiliatesImageService.save(afiliadosImageModel).getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(affiliatesProductsService.save(afiliadosProdutoModel));
    }
    @GetMapping
    public ResponseEntity<Page<AffiliatesProductsModel>> getAfiliadosProduto(@PageableDefault(page = 0, size = 50, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(affiliatesProductsService.findAll(pageable));
    }

    @GetMapping(value = "/categoria/{categoria}")
    public ResponseEntity<Object> getAfiliadosProdutoTpoCadastro(@PathVariable(value = "categoria") String categoria){
        List<AffiliatesProductsModel> afiliadosProdutoModelList = affiliatesProductsService.buscaPorCategoria(categoria);
        if(afiliadosProdutoModelList.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(afiliadosProdutoModelList);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(afiliadosProdutoModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAfiliadosProduto(@PathVariable(value = "id") Integer id){
        Optional<AffiliatesProductsModel> afiliadosProdutoModelOptional = affiliatesProductsService.findById(id);
        if(!afiliadosProdutoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(afiliadosProdutoModelOptional.get());
    }

    @GetMapping(value="/img/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getOneAffiliatesimage(@PathVariable(value = "id") Integer id){
        Optional<AffiliatesImageModel> affiliatesImageModelOptional = affiliatesImageService.findById(id);
        return affiliatesImageModelOptional.get().getImagem();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAfiliadosProduto(@PathVariable(value = "id") Integer id){
        Optional<AffiliatesProductsModel> afiliadosProdutoModelOptional = affiliatesProductsService.findById(id);
        if(!afiliadosProdutoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        affiliatesProductsService.delete(afiliadosProdutoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAfiliadosProduto(@PathVariable(value = "id") Integer id,
                                                         @RequestBody @Valid AffiliatesProductsDto afiliadosProdutoDto){
        Optional<AffiliatesProductsModel> afiliadosProdutoModelOptional = affiliatesProductsService.findById(id);
        if(!afiliadosProdutoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        var afiliadosProdutoModel = new AffiliatesProductsModel();
        BeanUtils.copyProperties(afiliadosProdutoDto,afiliadosProdutoModel);
        afiliadosProdutoModel.setId(afiliadosProdutoModelOptional.get().getId());
        afiliadosProdutoModel.setRegistrationDate(afiliadosProdutoModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(affiliatesProductsService.save(afiliadosProdutoModel));
    }
}
