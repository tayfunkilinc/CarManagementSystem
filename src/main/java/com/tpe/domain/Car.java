package com.tpe.domain;



import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Lutfen Marka Giriniz...")
    @NotNull(message = "Bos Gecilemez")
    @Size(min = 2, max = 50, message = "Deger 2 ile 50 Karakter Arasinda Olmali")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Lutfen Renk Giriniz...")
    @NotNull(message = "Bos Gecilemez")
    @Size(min = 2, max = 50, message = "Deger 2 ile 50 Karakter Arasinda Olmali")
    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    @NotNull(message = "Bos Gecilemez")
    private Integer year;
}
