package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.CharacterModel;
import com.riemannroch.wowsetup.service.CharacterService;
import com.riemannroch.wowsetup.service.EquivalencePointSystemService;
import com.riemannroch.wowsetup.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wowsetup/{name}/bis")
public class BestInSlotController {
    final CharacterService characterService;
    final ItemService itemService;
    final EquivalencePointSystemService equivalencePointSystemService;

    public BestInSlotController(CharacterService characterService, ItemService itemService, EquivalencePointSystemService equivalencePointSystemService) {
        this.characterService = characterService;
        this.itemService = itemService;
        this.equivalencePointSystemService = equivalencePointSystemService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllEquivalencePointSystemsForCharacter(@PathVariable("name") String name){
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if(characterModelOptional.isEmpty()){
            return CharacterController.notFound();
        }
        List<Object> response = new ArrayList<>();
        response.add(characterModelOptional.get());
        response.add(equivalencePointSystemService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}