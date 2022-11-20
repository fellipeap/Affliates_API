package com.api.afiliados.cotrollers;

import com.api.afiliados.models.AffiliatesImageModel;
import com.api.afiliados.services.AffiliatesImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/affiliate/image")
public class AffiliatesImageController {
    final AffiliatesImageService affiliatesImageService;

    public AffiliatesImageController(AffiliatesImageService affiliatesImageService){
        this.affiliatesImageService = affiliatesImageService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> saveImage(@RequestParam("image") MultipartFile image){
        var affiliatesImageModel = new AffiliatesImageModel();
        try {
            affiliatesImageModel.setImage(image.getBytes());
        } catch (IOException e) {
            log.error("Erro ao processar o arquivo",e);
        }
        affiliatesImageService.save(affiliatesImageModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(affiliatesImageModel.getId());
    }

    @GetMapping(value="/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getOneAffiliatesImage(@PathVariable(value = "id") Integer id){
        Optional<AffiliatesImageModel> affiliatesImageModelOptional = affiliatesImageService.findById(id);
        return affiliatesImageModelOptional.get().getImage();
    }

    @GetMapping
    public List<Integer> getAffiliatesImage() {
        return affiliatesImageService.ListIdImg();
    }
}

