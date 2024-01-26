package com.patient.hub.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(nullable = false, length = 100)
    String patientName;
    @Column(nullable = false)
    LocalDate dateOfBirth;
    @Column
    String address;
    @Enumerated(EnumType.STRING)
    Gender gender;
}
