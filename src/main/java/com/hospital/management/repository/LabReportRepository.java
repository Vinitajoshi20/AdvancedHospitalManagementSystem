package com.hospital.management.repository;

import com.hospital.management.entity.LabReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabReportRepository extends JpaRepository<LabReport, Long> {

    List<LabReport> findByPatientId(Long patientId);

    List<LabReport> findByPatientName(String patientName);
}