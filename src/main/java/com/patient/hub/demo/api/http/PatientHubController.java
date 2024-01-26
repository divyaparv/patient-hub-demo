package com.patient.hub.demo.api.http;

import com.patient.hub.demo.model.Gender;
import com.patient.hub.demo.model.Patient;
import com.patient.hub.demo.service.PatientService;
import org.springframework.data.repository.query.Param;
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
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatient(id);
    }

    @PostMapping("/create")
    @ResponseBody
    public Patient createPatientData(@RequestBody @Validated Patient patient) {
        return patientService.addPatient(patient);
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
