package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.CharacterModel;
import com.riemannroch.wowsetup.model.ItemModel;
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

    public static ResponseEntity<Object> notFound() {
        return new ResponseEntity<>("Character not found!", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Object> homePage() {
        return new ResponseEntity<>(characterService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCharacter(@RequestBody CharacterModel characterModel) {
        return new ResponseEntity<>(characterService.save(characterModel), HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> showCharacter(@PathVariable("name") String name) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        CharacterModel character = characterModelOptional.get();
        List<Object> response = new ArrayList<>();
        response.add(character);

        List<ItemModel> notOwned = new ArrayList<>();
        for (ItemModel item : itemService.findAll()) {
            if (!character.getItemsList().contains(item)) {
                notOwned.add(item);
            }
        }
        response.add(notOwned);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Object> updateCharacter(@PathVariable("name") String oldName, @RequestBody CharacterModel characterModel) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(oldName);
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        characterModel.setIdCharacter(characterModelOptional.get().getIdCharacter());
        return new ResponseEntity<>(characterService.save(characterModel), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deleteCharacter(@PathVariable("name") String name) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        characterService.delete(characterModelOptional.get());
        return new ResponseEntity<>("Character deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/{name}/{idItem}")
    public ResponseEntity<Object> addItemOwned(@PathVariable("name") String name, @PathVariable("idItem") long idItem) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        Optional<ItemModel> itemModelOptional = itemService.findById(idItem);
        if (itemModelOptional.isEmpty()) {
            return ItemController.notFound();
        }

        CharacterModel character = characterModelOptional.get();
        ItemModel item = itemModelOptional.get();

        List itemsList = character.getItemsList();
        if (itemsList.contains(item)) {
            return new ResponseEntity<>("The item " + item.getName() + " is already owned by " +
                    character.getName(), HttpStatus.CONFLICT);
        }
        itemsList.add(item);
        character.setItemsList(itemsList);
        characterService.save(character);
        return new ResponseEntity<>("The item " + item.getName() + " is now owned by " +
                character.getName(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}/{idItem}")
    public ResponseEntity<Object> removeItemOwned(@PathVariable("name") String name, @PathVariable("idItem") long idItem) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        Optional<ItemModel> itemModelOptional = itemService.findById(idItem);
        if (itemModelOptional.isEmpty()) {
            return ItemController.notFound();
        }
        CharacterModel character = characterModelOptional.get();
        ItemModel item = itemModelOptional.get();

        List itemsList = character.getItemsList();
        if (itemsList.contains(item)) {
            itemsList.remove(item);
            character.setItemsList(itemsList);
            characterService.save(character);
            return new ResponseEntity<>("The item " + item.getName() + " is no more owned by " +
                    character.getName(), HttpStatus.OK);
        }
        character.getItemsList().add(item);
        return new ResponseEntity<>("The item " + item.getName() + " is not owned by " +
                character.getName(), HttpStatus.CONFLICT);
    }
}
