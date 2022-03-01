package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.CharacterModel;
import com.riemannroch.wowsetup.service.CharacterService;
import com.riemannroch.wowsetup.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wowsetup")
public class CharacterController {
    final CharacterService characterService;
    final ItemService itemService;

    public CharacterController(CharacterService characterService, ItemService itemService) {
        this.characterService = characterService;
        this.itemService = itemService;
    }
    public ResponseEntity<Object> notFound(){
        return new ResponseEntity<>("Character not found!", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Object> homePage(){
        return new ResponseEntity<>(characterService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCharacter(@RequestBody CharacterModel characterModel){
        return new ResponseEntity<>(characterService.save(characterModel), HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> showCharacter(@PathVariable("name") String name){
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()){
            return notFound();
        }
        List<Object> response = new ArrayList<>();
        response.add(characterModelOptional);
        response.add(itemService.findAll());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Object> updateCharacter(@PathVariable("name") String oldName, @RequestBody CharacterModel characterModel){
        Optional<CharacterModel> characterModelOptional = characterService.findByName(oldName);
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        characterModel.setIdCharacter(characterModelOptional.get().getIdCharacter());
        return new ResponseEntity<>(characterService.save(characterModel), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deleteCharacter(@PathVariable("name") String name){
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()){
            return notFound();
        }
        characterService.delete(characterModelOptional.get());
        return new ResponseEntity<>("Character deleted successfully!", HttpStatus.OK);
    }
}
