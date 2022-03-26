package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.service.EquivalencePointSystemService;
import com.riemannroch.wowsetup.service.ItemEquivalencePointsService;
import com.riemannroch.wowsetup.service.ItemService;
import com.riemannroch.wowsetup.view.eps.EquivalencePointSystemView;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/wowsetup/eps")
public class EquivalencePointSystemController {
    final EquivalencePointSystemService equivalencePointSystemService;
    final ItemService itemService;
    final ItemEquivalencePointsService itemEquivalencePointsService;

    public static NotFoundException notFound(long id) {
        return new NotFoundException("Equivalence Point System not found for ID: " + id);
    }

    //Tested
    @Operation(summary = "Show all equivalence point systems")
    @GetMapping
    public List<EquivalencePointSystemView> getAllEquivalencePointSystems() {
        return EquivalencePointSystemView.listOf(equivalencePointSystemService.findAll());
    }

    //Tested
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Insert new equivalence point system")
    @PostMapping
    public EquivalencePointSystemView addEquivalencePointSystem(@RequestBody EquivalencePointSystem equivalencePointSystem) {
        equivalencePointSystemService.save(equivalencePointSystem, itemService, itemEquivalencePointsService);
        return new EquivalencePointSystemView(equivalencePointSystem);
    }

    //Tested
    @Operation(summary = "Show equivalence point system's weights")
    @GetMapping("/{idEquivalencePointSystem}")
    public EquivalencePointSystem showEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        return equivalencePointSystemService.findById(idEquivalencePointSystem)
                .orElseThrow(() -> notFound(idEquivalencePointSystem));
    }

    //Tested
    @Operation(summary = "Update equivalence point system's weights")
    @PutMapping("/{idEquivalencePointSystem}")
    public EquivalencePointSystemView updateEquivalencePointSystem(@RequestBody EquivalencePointSystem equivalencePointSystem,
                                                               @PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        equivalencePointSystemService.findById(idEquivalencePointSystem)
                .orElseThrow(() -> notFound(idEquivalencePointSystem));

        equivalencePointSystem.setIdEquivalencePointSystem(idEquivalencePointSystem);
        equivalencePointSystemService.save(equivalencePointSystem, itemService, itemEquivalencePointsService);
        return new EquivalencePointSystemView(equivalencePointSystem);
    }

    //Tested
    @Operation(summary = "Delete equivalence point system")
    @DeleteMapping("/{idEquivalencePointSystem}")
    public void deleteEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        EquivalencePointSystem eps = equivalencePointSystemService.findById(idEquivalencePointSystem)
                .orElseThrow(() -> notFound(idEquivalencePointSystem));
        equivalencePointSystemService.delete(eps, itemEquivalencePointsService);
    }
}