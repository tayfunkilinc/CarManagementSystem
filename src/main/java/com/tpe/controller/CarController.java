package com.tpe.controller;

import com.tpe.domain.Car;
import com.tpe.dto.CarDto;
import com.tpe.dto.OwnerDto;
import com.tpe.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        CarDto carDto = carService.getCarDtoById(id);
        return ResponseEntity.ok(carDto);
    }
    //3-a ID ile Araba Getirme
    @GetMapping("/a/{id}")
    public ResponseEntity<CarDto> foundCawrById(@PathVariable("id") Long id){
        CarDto carDto = carService.getCarDtoById(id);
        return ResponseEntity.ok(carDto);
    }

    //4-a Id'si verileni silelim
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable("id") Long id){
        carService.deleteById(id);
        return ResponseEntity.ok("Arac Silindi ");
    }

    /*RequestParam Kullanarak ID ile Araba Getir
    HTTP Yöntemi: GET
    URL: http://localhost:8080/car/q?id=2*/
    @GetMapping("/q")
    public ResponseEntity<CarDto> getCarByIdRequestParam(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(carService.getCarDtoById(id));
    }

    /*RequestParam Kullanarak Modeline Göre Araba Getir
    HTTP Yöntemi: GET
    URL: http://localhost:8080/car/search?model=Toyota*/
    @GetMapping("/search")
    public ResponseEntity<List<CarDto>> getCarsByBrand(@RequestParam(name = "brand") String brand) {
        List<CarDto> carDTOList = carService.findCarDTOByBrand(brand);
        return ResponseEntity.ok(carDTOList);
    }

    /*1.yol
    Sayfalandırma İle Arabaları Getir
    HTTP Yöntemi: GET
    URL: http://localhost:8080/car/s?page=1&size=2&sort=productionYear&direction=ASC*/
    @GetMapping("/s")
    public ResponseEntity<Page<CarDto>> getCarDtoPageable(Pageable pageable){
        Page<CarDto> carPage =  carService.getDtoPage(pageable);
        return ResponseEntity.ok(carPage);
    }
    //2.yol
    @GetMapping("/c")
    public ResponseEntity<Page<CarDto>> getCarDtoPageable2(@RequestParam(name = "page") int page,
                                                           @RequestParam(name = "size") int size,
                                                           @RequestParam(name = "sort") String sort,
                                                           @RequestParam(name = "direction")Sort.Direction direction){
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,sort));
        Page<CarDto> carPage =  carService.getDtoPage(pageable);
        return ResponseEntity.ok(carPage);
    }
    /*DTO Kullanarak Araba Güncelle
    HTTP Yöntemi: PUT
    URL: http://localhost:8080/car/update/2*/
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> ubdateCarDto(@PathVariable(name = "id") Long id,@Valid @RequestBody CarDto carDto){
        carService.updateCarDtoById(id,carDto);
        return ResponseEntity.ok("Arac Guncellendi");
    }



}
