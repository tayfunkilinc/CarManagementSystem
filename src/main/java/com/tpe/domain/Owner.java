package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Bos Gecilemez")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Bos Gecilemez")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Bos Gecilemez")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Lutfen gecerli bir email adresi giriniz")
    //@Email("Lutfen Gecerli Mail Giriniz")
    @Column(nullable = false, unique = true)
    private String email;

    @Setter(AccessLevel.NONE)
    private LocalDateTime registrationDate;

    @PrePersist
    public void setRegistrationDate(){
        this.registrationDate = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "owner")
    private List<Car> carList = new ArrayList<>();
}
