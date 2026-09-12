package com.hospital.management.service;

import com.hospital.management.entity.Billing;
import com.hospital.management.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;

    public BillingServiceImpl(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Override
    public Billing saveBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public List<Billing> getAllBillings() {
        return billingRepository.findAll();
    }

    @Override
    public Billing getBillingById(Long id) {
        return billingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBilling(Long id) {
        billingRepository.deleteById(id);
    }

    @Override
    public List<Billing> getBillingsByPatientName(String patientName) {
        return billingRepository.findByPatientName(patientName);
    }
}