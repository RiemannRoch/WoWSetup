package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.CharacterModel;
import com.riemannroch.wowsetup.model.ItemModel;
import com.riemannroch.wowsetup.request.CharacterRequest;
import com.riemannroch.wowsetup.service.CharacterService;
import com.riemannroch.wowsetup.service.ItemService;
import com.riemannroch.wowsetup.view.character.CharacterView;
import com.riemannroch.wowsetup.view.item.ItemView;
import io.swagger.v3.oas.annotations.Operation;
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
    // Tested
    @Operation(summary = "Show all characters")
    @GetMapping
    public ResponseEntity<List<CharacterView>> homePage() {
        return new ResponseEntity<>(CharacterView.listOf(characterService.findAll()), HttpStatus.OK);
    }

    //Tested
    @Operation(summary = "Insert new character")
    @PostMapping
    public ResponseEntity<CharacterView> addCharacter(@RequestBody CharacterRequest characterRequest) {
        return new ResponseEntity<>(new CharacterView(characterService.save(new CharacterModel(characterRequest))), HttpStatus.CREATED);
    }

    //Tested; Specify the response object
    @Operation(summary = "Show the list of items owned by character")
    @GetMapping("/{name}")
    public ResponseEntity<Object> showCharacter(@RequestBody CharacterRequest characterRequest) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(characterRequest.getName());
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        CharacterModel character = characterModelOptional.get();


        List<Object> response = new ArrayList<>();

        response.add(new CharacterView(character));

        List<ItemView> owned = ItemView.listOf(character.getItemsList());
        response.add(owned);

        /*List<ItemView> notOwned = new ArrayList<>();
        for (ItemModel item : itemService.findAll()) {
            if (!character.getItemsList().contains(item)) {
                notOwned.add(new ItemView(item));
            }
        }
        response.add(notOwned);*/
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Tested; Specify the response object
    @Operation(summary = "Rename character")
    @PutMapping("/{name}")
    public ResponseEntity<Object> updateCharacter(@PathVariable("name") String oldName, @RequestBody CharacterRequest characterRequest) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(oldName);
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        CharacterModel characterModel = characterModelOptional.get();

        characterModel.setName(characterRequest.getName());
        return new ResponseEntity<>(new CharacterView(characterService.save(characterModel)), HttpStatus.OK);
    }

    //Tested; Specify the response object
    @Operation(summary = "Delete character")
    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deleteCharacter(@PathVariable("name") String name) {
        Optional<CharacterModel> characterModelOptional = characterService.findByName(name);
        if (characterModelOptional.isEmpty()) {
            return notFound();
        }
        CharacterModel character = characterModelOptional.get();

        for (ItemModel item: character.getItemsList()){
            item.getOwnersList().remove(character);
            itemService.save(item);
        }

        characterService.delete(character);
        return new ResponseEntity<>("Character deleted successfully!", HttpStatus.OK);
    }

    //Tested; Specify the response object
    @Operation(summary = "Add an item to a character")
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

        List<ItemModel> itemsList = character.getItemsList();
        if (itemsList.contains(item)) {
            assert item.getOwnersList().contains(character);
            return new ResponseEntity<>("The item " + item.getName() + " is already owned by " +
                    character.getName(), HttpStatus.CONFLICT);
        }
        itemsList.add(item);
        item.getOwnersList().add(character);
        characterService.save(character);
        itemService.save(item);
        return new ResponseEntity<>("The item " + item.getName() + " is now owned by " +
                character.getName(), HttpStatus.CREATED);
    }

    //Tested; Specify the response object
    @Operation(summary = "Remove an item from a character")
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

        List<ItemModel> itemsList = character.getItemsList();
        if (itemsList.contains(item)) {
            assert item.getOwnersList().contains(character);
            itemsList.remove(item);
            item.getOwnersList().remove(character);
            itemService.save(item);
            characterService.save(character);
            return new ResponseEntity<>("The item " + item.getName() + " is no more owned by " +
                    character.getName(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The item " + item.getName() + " is not owned by " +
                character.getName(), HttpStatus.CONFLICT);
    }
}
