package com.hospital.management.service;

import com.hospital.management.entity.MedicalReport;
import com.hospital.management.repository.MedicalReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalReportServiceImpl implements MedicalReportService {

    private final MedicalReportRepository medicalReportRepository;

    public MedicalReportServiceImpl(MedicalReportRepository medicalReportRepository) {
        this.medicalReportRepository = medicalReportRepository;
    }

    @Override
    public MedicalReport saveMedicalReport(MedicalReport medicalReport) {
        return medicalReportRepository.save(medicalReport);
    }

    @Override
    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    @Override
    public MedicalReport getMedicalReportById(Long id) {
        return medicalReportRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMedicalReport(Long id) {
        medicalReportRepository.deleteById(id);
    }

    @Override
    public List<MedicalReport> getMedicalReportsByPatientName(String name) {
        return medicalReportRepository.findByPatientName(name);
    }
}