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

    public static ResponseEntity<Object> notFound() {
        return new ResponseEntity<>("Equivalence Point System not found!", HttpStatus.NOT_FOUND);
    }

    //Tested
    @GetMapping
    public ResponseEntity<Object> getAllEquivalencePointSystems() {
        return new ResponseEntity<>(EquivalencePointSystemView.listOf(equivalencePointSystemService.findAll()), HttpStatus.OK);
    }
    //Tested
    @PostMapping
    public ResponseEntity<Object> addEquivalencePointSystem(@RequestBody EquivalencePointSystem equivalencePointSystem) {
        equivalencePointSystemService.save(equivalencePointSystem);
        for (Item item : itemService.findAll()) {
            itemEquivalencePointsService.save(new ItemEquivalencePoints(item, equivalencePointSystem));
        }
        return new ResponseEntity<>(new EquivalencePointSystemView(equivalencePointSystem), HttpStatus.OK);
    }
    //Tested
    @GetMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object> showEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        Optional<EquivalencePointSystem> equivalencePointSystemOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if (equivalencePointSystemOptional.isEmpty()) {
            return notFound();
        }
        return new ResponseEntity<>(equivalencePointSystemOptional.get(), HttpStatus.OK);
    }
    //Tested
    @PutMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object> updateEquivalencePointSystem(@RequestBody EquivalencePointSystem equivalencePointSystem,
                                                               @PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        Optional<EquivalencePointSystem> equivalencePointSystemOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if (equivalencePointSystemOptional.isEmpty()) {
            return notFound();
        }
        equivalencePointSystem.setIdEquivalencePointSystem(idEquivalencePointSystem);
        equivalencePointSystemService.save(equivalencePointSystem);

        for (Item item : itemService.findAll()){
            itemEquivalencePointsService.save(new ItemEquivalencePoints(item, equivalencePointSystem));
        }
        return new ResponseEntity<>(new EquivalencePointSystemView(equivalencePointSystemService.save(equivalencePointSystem)), HttpStatus.OK);
    }
    //Tested
    @DeleteMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object> deleteEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        Optional<EquivalencePointSystem> equivalencePointSystemOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if (equivalencePointSystemOptional.isEmpty()) {
            return notFound();
        }
        EquivalencePointSystem equivalencePointSystem = equivalencePointSystemOptional.get();
        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsService.findByEps(equivalencePointSystem)){
            itemEquivalencePointsService.delete(itemEquivalencePoints);
        }
        equivalencePointSystemService.delete(equivalencePointSystem);
        return new ResponseEntity<>("Equivalence Point System deleted successfully", HttpStatus.OK);
    }
}