package com.tpe.service;

import com.tpe.domain.Car;
import com.tpe.dto.CarDto;
import com.tpe.exception.CarNotFoundException;
import com.tpe.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
    public Car getCarDtoById(Long id) {

        Car car = carRepository.findById(id).orElseThrow(()->new CarNotFoundException("Car Not Found!!!"));
        return car;
    }

    public CarDto getCarById(Long id) {
        Car cars = getCarDtoById(id);
        return new CarDto(cars);
    }


    public void deleteById(Long id) {
        Car car = getCarDtoById(id);
        carRepository.delete(car);
    }
}
