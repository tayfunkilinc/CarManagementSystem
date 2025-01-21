package com.tpe.service;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDto;
import com.tpe.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public void saveOwner(@Valid OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setName(ownerDto.getName());
        owner.setLastName(ownerDto.getLastName());
        owner.setEmail(ownerDto.getEmail());
        //owner.setCarList(ownerDto.getCarList());
    }
}
