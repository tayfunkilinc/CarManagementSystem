package com.tpe.controller;

import com.tpe.dto.CarDto;
import com.tpe.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    //1-a Kaydetme - GET
    @PostMapping
    public ResponseEntity<String> createCar(@Valid @RequestBody CarDto carDTO){
        carService.saveCar(carDTO);
        return new ResponseEntity<>("Araba başarıyla kayıt edildi.", HttpStatus.CREATED);
    }

    //2-a Tum Arabalari Getir - GET
    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCar(){

        List<CarDto> carDto = carService.printAllCars();

        return ResponseEntity.ok(carDto);
    }

    //3-a ID ile Araba Getirme
    @GetMapping("/{id}")
    public ResponseEntity<CarDto> foundCarById(@PathVariable("id") Long id){
        CarDto carDto = carService.getCarById(id);
        return ResponseEntity.ok(carDto);
    }

    //4-a Id'si verileni silelim
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable("id") Long id){
        carService.deleteById(id);
        return ResponseEntity.ok("Arac Silindi ");
    }




}
