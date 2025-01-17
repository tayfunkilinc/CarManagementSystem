package com.tpe.dto;

import com.tpe.domain.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor //parametresiz cont icin bunu kullanmaliyiz hibernate kullandigi icin
public class CarDto {

    @NotBlank(message = "Lutfen Marka Giriniz...")
    @NotNull(message = "Bos Gecilemez")
    @Size(min = 2, max = 50, message = "Deger 2 ile 50 Karakter Arasinda Olmali")
    private String brand;

    @NotBlank(message = "Lutfen Renk Giriniz...")
    @NotNull(message = "Bos Gecilemez")
    @Size(min = 2, max = 50, message = "Deger 2 ile 50 Karakter Arasinda Olmali")
    private String color;

    @NotNull(message = "Bos Gecilemez")
    private Integer year;

    public CarDto(Car car) {
        this.brand = car.getBrand();
        this.color = car.getColor();
        this.year = car.getYear();
    }

}
