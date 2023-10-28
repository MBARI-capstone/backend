package com.MBARI.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="Ships")
public class ShipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipId")
    private Integer shipId;

    @Column(name="shipName")
    private String shipName;

    @Column(name="shipDescription")
    private String shipDesc;

}
