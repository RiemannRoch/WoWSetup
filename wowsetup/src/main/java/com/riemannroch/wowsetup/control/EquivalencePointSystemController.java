package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.service.EquivalencePointSystemService;
import com.riemannroch.wowsetup.service.ItemEquivalencePointsService;
import com.riemannroch.wowsetup.service.ItemService;
import com.riemannroch.wowsetup.view.eps.EquivalencePointSystemView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wowsetup/eps")
public class EquivalencePointSystemController {
    final EquivalencePointSystemService equivalencePointSystemService;
    final ItemService itemService;
    final ItemEquivalencePointsService itemEquivalencePointsService;

    public EquivalencePointSystemController(EquivalencePointSystemService equivalencePointSystemService, ItemService itemService, ItemEquivalencePointsService itemEquivalencePointsService) {
        this.equivalencePointSystemService = equivalencePointSystemService;
        this.itemService = itemService;
        this.itemEquivalencePointsService = itemEquivalencePointsService;
    }

    public static NotFoundException notFound(long id) {
        return new NotFoundException("Equivalence Point System not found for ID: " + id);
    }

    //Tested
    @GetMapping
    public List<EquivalencePointSystemView> getAllEquivalencePointSystems() {
        return EquivalencePointSystemView.listOf(equivalencePointSystemService.findAll());
    }

    //To be tested
    @PostMapping
    public EquivalencePointSystemView addEquivalencePointSystem(@RequestBody EquivalencePointSystem equivalencePointSystem) {
        equivalencePointSystemService.save(equivalencePointSystem);
        for (Item item : itemService.findAll()) {
            itemEquivalencePointsService.save(new ItemEquivalencePoints(item, equivalencePointSystem));
        }
        return new EquivalencePointSystemView(equivalencePointSystem);
    }

    //To be tested
    @GetMapping("/{idEquivalencePointSystem}")
    public EquivalencePointSystem showEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        return equivalencePointSystemService.findById(idEquivalencePointSystem)
                .orElseThrow(() -> notFound(idEquivalencePointSystem));
    }

    //To be tested
    @PutMapping("/{idEquivalencePointSystem}")
    public EquivalencePointSystemView updateEquivalencePointSystem(@RequestBody EquivalencePointSystem equivalencePointSystem,
                                                               @PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        equivalencePointSystemService.findById(idEquivalencePointSystem)
                .orElseThrow(() -> notFound(idEquivalencePointSystem));

        equivalencePointSystem.setIdEquivalencePointSystem(idEquivalencePointSystem);
        equivalencePointSystemService.save(equivalencePointSystem);

        for (Item item: itemService.findAll()){
            itemEquivalencePointsService.save(new ItemEquivalencePoints(item, equivalencePointSystem));
        }
        return new EquivalencePointSystemView(equivalencePointSystem);
    }

    //To be tested
    @DeleteMapping("/{idEquivalencePointSystem}")
    public void deleteEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        EquivalencePointSystem eps = equivalencePointSystemService.findById(idEquivalencePointSystem)
                .orElseThrow(() -> notFound(idEquivalencePointSystem));
        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsService.findByEps(eps)){
            itemEquivalencePointsService.delete(itemEquivalencePoints);
        }
        equivalencePointSystemService.delete(eps);
    }
}