package com.patient.hub.demo.service;

import com.patient.hub.demo.exception.PatientNotFoundException;
import com.patient.hub.demo.model.Gender;
import com.patient.hub.demo.model.Patient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        if (patient.getPatientName() == null || patient.getPatientName().isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Patient Name cannot be null or empty");
        }
        return patientRepository.save(patient);
    }

    @Cacheable(cacheNames = {"patientCache"}, key = "#id")
    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @CachePut(cacheNames = {"patientCache"}, key = "#id")
    public Patient updatePatient(Long id, String address, Gender gender) {
        return patientRepository.findById(id)
                .map(p -> {
                    if(gender != null) p.setGender(gender);
                    if(address != null) p.setAddress(address);
                    return patientRepository.save(p);
                })
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    @CacheEvict(cacheNames = {"patientCache"}, key = "#id")
    public void deletePatient(Long id) {
        if (patientRepository.findById(id).isEmpty()) {
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
    }
}
