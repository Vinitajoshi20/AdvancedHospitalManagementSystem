package com.hospital.management.service;

import com.hospital.management.entity.Pharmacy;

import java.util.List;

public interface PharmacyService {

    Pharmacy savePharmacy(Pharmacy pharmacy);

    List<Pharmacy> getAllPharmacies();
}