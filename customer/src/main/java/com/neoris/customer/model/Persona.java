package com.neoris.customer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class Persona {

    @Column
    private String nombre;
    @Column
    private String genero;
    @Column
    private Integer edad;
    @Column
    private String identificacion;
    @Column
    private String direccion;
    @Column
    private String telefono;

}
