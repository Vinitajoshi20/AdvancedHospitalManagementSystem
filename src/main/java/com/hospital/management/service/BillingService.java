package com.hospital.management.service;

import com.hospital.management.entity.Billing;

import java.util.List;

public interface BillingService {

    Billing saveBilling(Billing billing);

    List<Billing> getAllBillings();

    Billing getBillingById(Long id);

    void deleteBilling(Long id);

    List<Billing> getBillingsByPatientName(String patientName);
}