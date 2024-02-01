package com.patient.hub.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 100)
    @NotNull @NotEmpty
    String patientName;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfBirth;
    @Column
    String address;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    Gender gender;
}
