package com.hospital.management.service;

import com.hospital.management.entity.LabReport;
import com.hospital.management.repository.LabReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabReportService {

    private final LabReportRepository labReportRepository;

    public LabReportService(LabReportRepository labReportRepository) {
        this.labReportRepository = labReportRepository;
    }

    public LabReport saveLabReport(LabReport labReport) {
        return labReportRepository.save(labReport);
    }

    public List<LabReport> getAllLabReports() {
        return labReportRepository.findAll();
    }

    public List<LabReport> getLabReportsByPatientId(Long patientId) {
        return labReportRepository.findByPatientId(patientId);
    }

    public List<LabReport> getLabReportsByPatientName(String patientName) {
        return labReportRepository.findByPatientName(patientName);
    }

    public LabReport getLabReportById(Long id) {
        return labReportRepository.findById(id).orElse(null);
    }

    public void deleteLabReport(Long id) {
        labReportRepository.deleteById(id);
    }
}