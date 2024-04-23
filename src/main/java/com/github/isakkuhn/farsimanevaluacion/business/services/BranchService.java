package com.github.isakkuhn.farsimanevaluacion.business.services;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.BranchEntity;
import com.github.isakkuhn.farsimanevaluacion.persistence.repositories.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public List<BranchEntity> getAllBranches() {
        return this.branchRepository.findAll();
    }

    public BranchEntity getBranchById(Long id) {
        return this.branchRepository.findById(id).orElse(null);
    }

    public BranchEntity createBranch(BranchEntity branch) {
        if(branch.getId() == null){
            return this.branchRepository.save(branch);
        }
        return null;
    }

    public BranchEntity updateBranch( BranchEntity branch) {
        BranchEntity branchEntity = this.branchRepository.findById(branch.getId()).orElse(null);
        if (branchEntity == null) {
            return null;
        }
        return this.branchRepository.save(branch);
    }

    public boolean deleteBranch(Long id) {
        BranchEntity branchEntity = this.branchRepository.findById(id).orElse(null);
        if (branchEntity == null) {
            return false;
        }
        this.branchRepository.delete(branchEntity);
        return true;
    }
}
