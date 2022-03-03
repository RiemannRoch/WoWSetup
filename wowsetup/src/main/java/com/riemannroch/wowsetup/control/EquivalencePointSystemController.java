package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.EquivalencePointSystemModel;
import com.riemannroch.wowsetup.repository.EquivalencePointSystemRepository;
import com.riemannroch.wowsetup.service.EquivalencePointSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wowsetup/eps")
public class EquivalencePointSystemController {
    final EquivalencePointSystemService equivalencePointSystemService;

    public EquivalencePointSystemController(EquivalencePointSystemService equivalencePointSystemService) {
        this.equivalencePointSystemService = equivalencePointSystemService;
    }

    public static ResponseEntity<Object> notFound(){
        return new ResponseEntity<>("Equivalence Point System not found!", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Object> getAllEquivalencePointSystems(){
        return new ResponseEntity<>(equivalencePointSystemService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addEquivalencePointSystem(@RequestBody EquivalencePointSystemModel equivalencePointSystemModel){
        return new ResponseEntity<>(equivalencePointSystemService.save(equivalencePointSystemModel), HttpStatus.OK);
    }

    @GetMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object>  showEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem){
        Optional<EquivalencePointSystemModel> equivalencePointSystemModelOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if(equivalencePointSystemModelOptional.isEmpty()){
            return notFound();
        }
        return new ResponseEntity<>(equivalencePointSystemModelOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{idEquivalencePointSystem}")
    public ResponseEntity<Object> updateEquivalencePointSystem(@RequestBody EquivalencePointSystemModel equivalencePointSystemModel,
                                                               @PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem){
        Optional<EquivalencePointSystemModel> equivalencePointSystemModelOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if(equivalencePointSystemModelOptional.isEmpty()){
            return notFound();
        }
        equivalencePointSystemModel.setIdEquivalencePointSystem(idEquivalencePointSystem);
        return new ResponseEntity<>(equivalencePointSystemService.save(equivalencePointSystemModel), HttpStatus.OK);
    }

    @DeleteMapping("{idEquivalencePointSystem}")
    public ResponseEntity<Object> deleteEquivalencePointSystem(@PathVariable("idEquivalencePointSystem") long idEquivalencePointSystem){
        Optional<EquivalencePointSystemModel> equivalencePointSystemModelOptional = equivalencePointSystemService.findById(idEquivalencePointSystem);
        if (equivalencePointSystemModelOptional.isEmpty()){
            return notFound();
        }
        equivalencePointSystemService.deleteById(idEquivalencePointSystem);
        return new ResponseEntity<>("Equivalence Point System deleted successfully",HttpStatus.OK);
    }
}