package com.hospital.management.service;

import com.hospital.management.entity.Laboratory;
import com.hospital.management.repository.LaboratoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryService {

    private final LaboratoryRepository laboratoryRepository;

    public LaboratoryService(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    public Laboratory saveLaboratory(Laboratory laboratory) {
        return laboratoryRepository.save(laboratory);
    }

    public List<Laboratory> getAllLaboratories() {
        return laboratoryRepository.findAll();
    }
}
