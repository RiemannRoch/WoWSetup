package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.EquivalencePointSystemModel;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.ItemModel;
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
    public ResponseEntity<Object> addEquivalencePointSystem(@RequestBody EquivalencePointSystemModel equivalencePointSystemModel) {
        equivalencePointSystemService.save(equivalencePointSystemModel);
        for (ItemModel item : itemService.findAll()) {
            itemEquivalencePointsService.save(new ItemEquivalencePoints(item, equivalencePointSystemModel));
        }
        return new ResponseEntity<>(new EquivalencePointSystemView(equivalencePointSystemModel), HttpStatus.OK);
    }
    //Tested
    @GetMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object> showEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        Optional<EquivalencePointSystemModel> equivalencePointSystemModelOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if (equivalencePointSystemModelOptional.isEmpty()) {
            return notFound();
        }
        return new ResponseEntity<>(equivalencePointSystemModelOptional.get(), HttpStatus.OK);
    }
    //Tested
    @PutMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object> updateEquivalencePointSystem(@RequestBody EquivalencePointSystemModel equivalencePointSystemModel,
                                                               @PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        Optional<EquivalencePointSystemModel> equivalencePointSystemModelOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if (equivalencePointSystemModelOptional.isEmpty()) {
            return notFound();
        }
        equivalencePointSystemModel.setIdEquivalencePointSystem(idEquivalencePointSystem);
        equivalencePointSystemService.save(equivalencePointSystemModel);

        for (ItemModel itemModel : itemService.findAll()){
            itemEquivalencePointsService.save(new ItemEquivalencePoints(itemModel,equivalencePointSystemModel));
        }
        return new ResponseEntity<>(new EquivalencePointSystemView(equivalencePointSystemService.save(equivalencePointSystemModel)), HttpStatus.OK);
    }
    //Tested
    @DeleteMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object> deleteEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem) {
        Optional<EquivalencePointSystemModel> equivalencePointSystemModelOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if (equivalencePointSystemModelOptional.isEmpty()) {
            return notFound();
        }
        EquivalencePointSystemModel equivalencePointSystemModel = equivalencePointSystemModelOptional.get();
        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsService.findByEps(equivalencePointSystemModel)){
            itemEquivalencePointsService.delete(itemEquivalencePoints);
        }
        equivalencePointSystemService.delete(equivalencePointSystemModel);
        return new ResponseEntity<>("Equivalence Point System deleted successfully", HttpStatus.OK);
    }
}