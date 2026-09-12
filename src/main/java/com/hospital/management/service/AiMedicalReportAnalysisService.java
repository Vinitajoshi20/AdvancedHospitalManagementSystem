package com.hospital.management.service;

import com.hospital.management.entity.MedicalReport;
import org.springframework.stereotype.Service;

@Service
public class AiMedicalReportAnalysisService {

    public String analyzeReport(MedicalReport report) {

        if (report == null) {
            return "No medical report is available for analysis.";
        }

        StringBuilder analysis = new StringBuilder();

        analysis.append("AI Medical Report Analysis\n\n");

        if (report.getDiagnosis() != null &&
                !report.getDiagnosis().trim().isEmpty()) {

            analysis.append("Diagnosis Analysis:\n");
            analysis.append("The recorded diagnosis is: ")
                    .append(report.getDiagnosis())
                    .append(".\n\n");
        }

        if (report.getSymptoms() != null &&
                !report.getSymptoms().trim().isEmpty()) {

            analysis.append("Symptoms Analysis:\n");
            analysis.append("The reported symptoms are: ")
                    .append(report.getSymptoms())
                    .append(".\n\n");
        }

        if (report.getPrescription() != null &&
                !report.getPrescription().trim().isEmpty()) {

            analysis.append("Prescription Information:\n");
            analysis.append("The medical record contains the following prescription information: ")
                    .append(report.getPrescription())
                    .append(".\n\n");
        }

        if (report.getDoctorAdvice() != null &&
                !report.getDoctorAdvice().trim().isEmpty()) {

            analysis.append("Doctor's Advice:\n");
            analysis.append(report.getDoctorAdvice())
                    .append("\n\n");
        }

        analysis.append("Overall Observation:\n");

        if (report.getDiagnosis() != null &&
                !report.getDiagnosis().trim().isEmpty()) {

            analysis.append(
                    "The medical record contains a documented diagnosis along with "
                            + "supporting symptoms and treatment information."
            );

        } else {

            analysis.append(
                    "The available medical record contains limited clinical information."
            );
        }

        analysis.append(
                "\n\nImportant Safety Notice:\n" +
                        "This analysis is for informational and educational purposes only. " +
                        "It does not provide a medical diagnosis and should not replace advice " +
                        "from a qualified healthcare professional."
        );

        return analysis.toString();
    }
}