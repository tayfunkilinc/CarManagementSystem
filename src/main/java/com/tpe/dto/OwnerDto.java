package com.tpe.dto;

import com.tpe.domain.Car;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OwnerDto {

    @NotBlank(message = "Bos Gecilemez")
    private String name;

    @NotBlank(message = "Bos Gecilemez")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Lutfen gecerli bir email adresi giriniz")
    //@Email("Lutfen Gecerli Mail Giriniz")
    private String email;

    private List<CarDto> carList = new ArrayList<>();

}
