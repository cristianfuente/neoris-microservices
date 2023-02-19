package com.neoris.account.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CLIENTE", schema = "public")
public class Cliente {

    @Id
    private Long id;
    @Column
    private Boolean state;
    @Column
    private String nombre;
    @Column
    private String identificacion;
}
