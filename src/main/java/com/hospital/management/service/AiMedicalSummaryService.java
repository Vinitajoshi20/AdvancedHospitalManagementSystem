package com.hospital.management.service;

import com.hospital.management.entity.MedicalReport;
import org.springframework.stereotype.Service;

@Service
public class AiMedicalSummaryService {

    public String generateSummary(MedicalReport report) {

        if (report == null) {
            return "No medical record is available.";
        }

        StringBuilder summary = new StringBuilder();

        summary.append("Medical Record Summary\n\n");

        if (report.getPatientName() != null &&
                !report.getPatientName().trim().isEmpty()) {

            summary.append("Patient: ")
                    .append(report.getPatientName())
                    .append("\n");
        }

        if (report.getReportDate() != null) {

            summary.append("Report Date: ")
                    .append(report.getReportDate())
                    .append("\n");
        }

        if (report.getDiagnosis() != null &&
                !report.getDiagnosis().trim().isEmpty()) {

            summary.append("Diagnosis: ")
                    .append(report.getDiagnosis())
                    .append("\n");
        }

        if (report.getSymptoms() != null &&
                !report.getSymptoms().trim().isEmpty()) {

            summary.append("Symptoms: ")
                    .append(report.getSymptoms())
                    .append("\n");
        }

        if (report.getPrescription() != null &&
                !report.getPrescription().trim().isEmpty()) {

            summary.append("Prescription: ")
                    .append(report.getPrescription())
                    .append("\n");
        }

        if (report.getDoctorAdvice() != null &&
                !report.getDoctorAdvice().trim().isEmpty()) {

            summary.append("Doctor Advice: ")
                    .append(report.getDoctorAdvice())
                    .append("\n");
        }

        summary.append("\nOverall Summary: ");

        if (report.getDiagnosis() != null &&
                !report.getDiagnosis().trim().isEmpty()) {

            summary.append("The patient has been evaluated for ")
                    .append(report.getDiagnosis())
                    .append(".");
        } else {

            summary.append("Medical information is available in the patient's record.");
        }

        summary.append(
                "\n\nNote: This summary is for information purposes only " +
                        "and should not replace professional medical advice."
        );

        return summary.toString();
    }
}