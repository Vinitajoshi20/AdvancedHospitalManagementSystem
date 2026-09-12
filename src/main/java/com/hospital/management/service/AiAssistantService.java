package com.hospital.management.service;

import org.springframework.stereotype.Service;

@Service
public class AiAssistantService {

    public String getResponse(String question) {

        if (question == null || question.trim().isEmpty()) {
            return "Please enter a question so I can help you.";
        }

        String query = question.toLowerCase().trim();


        if (query.contains("appointment")
                || query.contains("book")
                || query.contains("doctor")) {

            return "You can book an appointment from the 'Book Appointment' section. "
                    + "Select a doctor, choose your preferred date and time, "
                    + "and enter the reason for your visit.";
        }


        if (query.contains("department")
                || query.contains("specialist")
                || query.contains("specialization")) {

            return "Our hospital provides different departments and medical "
                    + "specializations. Please check the hospital department "
                    + "section or contact reception for the appropriate department.";
        }


        if (query.contains("medical report")
                || query.contains("report")) {

            return "You can view your medical reports from the "
                    + "'My Medical Reports' section of your patient dashboard.";
        }


        if (query.contains("bill")
                || query.contains("payment")
                || query.contains("billing")) {

            return "You can view your hospital bills and payment details "
                    + "from the 'My Bills' section of your patient dashboard.";
        }


        if (query.contains("prescription")
                || query.contains("medicine")) {

            return "You can view your prescriptions from the "
                    + "'My Prescriptions' section. For any medicine-related "
                    + "concern, please consult your doctor or pharmacist.";
        }


        if (query.contains("lab")
                || query.contains("test")) {

            return "You can view your laboratory reports from the "
                    + "'My Lab Reports' section of your patient dashboard.";
        }


        if (query.contains("hello")
                || query.contains("hi")
                || query.contains("hey")) {

            return "Hello! 😊 I am your AI Hospital Assistant. "
                    + "How can I help you today?";
        }


        return "I can help you with hospital services, appointments, "
                + "departments, medical reports, prescriptions, laboratory "
                + "reports and bills. Please ask me a specific question.";
    }
}