package com.github.isakkuhn.farsimanevaluacion.presentation.controllers;

import com.github.isakkuhn.farsimanevaluacion.business.services.BranchService;
import com.github.isakkuhn.farsimanevaluacion.persistence.entities.BranchEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
@AllArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchEntity>> getAllBranches() {
        List<BranchEntity> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchEntity> getBranchById(@PathVariable Long id) {
        BranchEntity branch = branchService.getBranchById(id);
        if (branch == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(branch);
    }

    @PostMapping
    public ResponseEntity<BranchEntity> createBranch(@RequestBody BranchEntity branch) {
        BranchEntity createdBranch = branchService.createBranch(branch);
        if (createdBranch == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdBranch);
    }

    @PutMapping
    public ResponseEntity<BranchEntity> updateBranch(@RequestBody BranchEntity branch) {
        BranchEntity updatedBranch = branchService.updateBranch(branch);
        if (updatedBranch == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updatedBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBranch(@PathVariable Long id) {
        boolean deleted = branchService.deleteBranch(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
