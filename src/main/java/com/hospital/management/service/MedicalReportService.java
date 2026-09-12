package com.hospital.management.service;

import com.hospital.management.entity.MedicalReport;

import java.util.List;

public interface MedicalReportService {

    MedicalReport saveMedicalReport(MedicalReport medicalReport);

    List<MedicalReport> getAllMedicalReports();

    MedicalReport getMedicalReportById(Long id);

    void deleteMedicalReport(Long id);

    List<MedicalReport> getMedicalReportsByPatientName(String name);
}