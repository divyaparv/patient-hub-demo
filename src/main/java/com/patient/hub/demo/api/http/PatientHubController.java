package com.patient.hub.demo.api.http;

import com.patient.hub.demo.exception.InvalidInputArgumentsException;
import com.patient.hub.demo.model.Gender;
import com.patient.hub.demo.model.Patient;
import com.patient.hub.demo.service.PatientService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patient-hub/api/v1")
public class PatientHubController {
    private final PatientService patientService;

    public PatientHubController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.getPatient(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Patient> createPatientData(@RequestBody @Validated Patient patient, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new InvalidInputArgumentsException(errors);
        }
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Patient updatePatient(@PathVariable Long id, @Param(value="address") String address, @Param(value = "gender")Gender gender) {
        return patientService.updatePatient(id, address, gender);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}
