package com.tpe.service;

import com.tpe.domain.Car;
import com.tpe.dto.CarDto;
import com.tpe.exception.CarNotFoundException;
import com.tpe.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    //1-b
    public void saveCar(@Valid CarDto carDTO) {
        Car car = new Car();
        // BeanUtils.copyProperties(carDTO, car); bunu bir arasstir direk set methodunun yaptigini yapiyor
        /*BeanUtils.copyProperties(carDTO, car); satırı, carDTO nesnesindeki tüm özellikleri car
        nesnesine kopyalamak için kullanılır. Bu, Spring Framework'ün BeanUtils sınıfının bir yöntemidir.
         Bu yöntem, iki nesne arasında aynı ada sahip özellikleri eşleştirir ve değerlerini kopyalar.
          Bu sayede, CarDTO nesnesindeki veriler Car nesnesine aktarılır.*/

        car.setBrand(carDTO.getBrand());
        car.setColor(carDTO.getColor());
        car.setYear(carDTO.getYear());
        carRepository.save(car);
    }
    //2-b
    public List<CarDto> printAllCars() {

        List<Car> cars = carRepository.findAll();
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car:cars){
            CarDto carDto = new CarDto(car);
            carDtos.add(carDto);
        }
        return carDtos;

    }

    //3-b
    public Car getCarById(Long id) {

        Car car = carRepository.findById(id).orElseThrow(()->new CarNotFoundException("Car Not Found!!!"));
        return car;
    }

    public CarDto getCarDtoById(Long id) {
        Car cars = getCarById(id);
        return new CarDto(cars);
    }


    public void deleteById(Long id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }

    public List<CarDto> findCarDTOByBrand(String brand) {
        List<Car> carList = carRepository.findCarByBrand(brand);
        List<CarDto> carDtoList = new ArrayList<>();
        CarDto carDto;
        for (Car car : carList){
            carDto = new CarDto(car);
            carDtoList.add(carDto);
        }
        return carDtoList;
    }

    //1.yol
    /*public Page<Car> getDtoPage(Pageable pageable) {
        return carRepository.findAll(pageable);
    }*/

    //2.yol
    public Page<CarDto> getDtoPage(Pageable pageable) {
        Page<Car> carPage = carRepository.findAll(pageable);
        return carPage.map(CarDto::new);
    }

    //3.yol
    public Page<CarDto> getDTOPage2(Pageable pageable) {
        Page<Car> carPage = carRepository.findAll(pageable);
        List<CarDto> carDTOList = new ArrayList<>();
        for (Car car : carPage) {
            carDTOList.add(new CarDto(car));
        }
        return new PageImpl<>(carDTOList, pageable, carPage.getTotalElements());
    }

    /*PageImpl, Spring Data JPA tarafından sağlanan bir sınıftır ve Page arayüzünün somut bir uygulamasıdır.
     Page arayüzü, sayfalama ve sıralama işlemleri için kullanılır ve PageImpl bu arayüzü uygulayarak
     sayfalama sonuçlarını temsil eder.
     PageImpl sınıfı, bir listeyi sayfalama bilgileriyle birlikte döndürmek için kullanılır.
    Örneğin, PageImpl kullanarak bir List<CarDTO> nesnesini sayfalama bilgileriyle birlikte döndürebilirsiniz.
    Bu, sayfalama işlemlerinde kullanışlıdır çünkü sayfa numarası, sayfa boyutu ve toplam eleman sayısı gibi
    bilgileri içerir.*/

    //stream yapisiyla yapmak
    /*public List<CarDTO> findCarDTOByBrand(String brand) {
    List<Car> carList = carRepository.findCarByBrand(brand);
    return carList.stream()
          .map(CarDTO::new)
          .collect(Collectors.toList());
    }*/

    public void updateCarDtoById(Long id, @Valid CarDto carDto) {
        Car car = getCarById(id);
        car.setYear(carDto.getYear());
        car.setColor(carDto.getColor());
        car.setBrand(carDto.getBrand());
        carRepository.save(car);
    }






}
