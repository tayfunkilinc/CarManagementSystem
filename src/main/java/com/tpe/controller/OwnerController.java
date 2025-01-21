package com.tpe.controller;

import com.tpe.dto.OwnerDto;
import com.tpe.repository.OwnerRepository;
import com.tpe.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    /*B - Sahip Endpoints
    Sahip Kaydet
    HTTP Yöntemi: POST
    URL: http://localhost:8080/owners/save*/

    @PostMapping("/save")
    public ResponseEntity<String> createOwner(@Valid @RequestBody OwnerDto ownerDto){
            ownerService.saveOwner(ownerDto);
            return ResponseEntity.ok("Sahip Kaydedildi");
    }
}
