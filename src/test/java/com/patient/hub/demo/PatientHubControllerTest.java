package com.patient.hub.demo;

import com.patient.hub.demo.api.http.PatientHubController;
import com.patient.hub.demo.model.Gender;
import com.patient.hub.demo.model.Patient;
import com.patient.hub.demo.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientHubControllerTest {

    @InjectMocks
    private PatientHubController patientHubController;

    @Mock
    PatientService patientService;

    @Test
    public void givenValidPatientId_shouldGetPatient() {

        Patient patient = Patient.builder()
                .patientName("John")
                .address("123 Long Street")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .gender(Gender.MALE)
                .build();

        when(patientService.getPatient(any())).thenReturn(patient);
        ResponseEntity<Patient> actualPatientEntity = patientHubController.getPatientById(1L);
        assertEquals(patient, actualPatientEntity.getBody());
    }
}
