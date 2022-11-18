package com.example.servicesections.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nom;
    @NotEmpty
    private String filiere;
    @NotEmpty
    private String session;
    @NotEmpty
    private String semestre;
}