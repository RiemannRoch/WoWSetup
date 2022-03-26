package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.request.CharacterRequest;
import com.riemannroch.wowsetup.service.CharacterService;
import com.riemannroch.wowsetup.service.ItemService;
import com.riemannroch.wowsetup.view.character.CharacterView;
import com.riemannroch.wowsetup.view.character.CharacterWithItemsView;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/wowsetup")
public class CharacterController {
    final CharacterService characterService;
    final ItemService itemService;

    public CharacterController(CharacterService characterService, ItemService itemService) {
        this.characterService = characterService;
        this.itemService = itemService;
    }

    public static NotFoundException notFound(String name) {
        return new NotFoundException("Character not found for name: " + name);
    }

    //Tested
    @Operation(summary = "Show all characters")
    @GetMapping
    public List<CharacterView> homePage() {
        return CharacterView.listOf(characterService.findAll());
    }

    //Tested
    @Operation(summary = "Insert new character")
    @PostMapping
    public CharacterView addCharacter(@RequestBody CharacterRequest characterRequest) {
        return new CharacterView(characterService.save(new Character(characterRequest)));
    }

    //Tested
    @Operation(summary = "Show the list of items owned by character")
    @GetMapping("/{name}")
    public CharacterWithItemsView showCharacter(@PathVariable("name") String name) {
        Character character = characterService.findByName(name)
                .orElseThrow(() -> notFound(name));
        return new CharacterWithItemsView(character);
    }

    //Tested
    @Operation(summary = "Rename character")
    @PutMapping("/{name}")
    public CharacterView renameCharacter(@PathVariable("name") String oldName, @RequestBody CharacterRequest characterRequest) {
        Character character = characterService.findByName(oldName)
                .orElseThrow(() -> notFound(oldName));
        character.setName(characterRequest.getName());
        return new CharacterView(characterService.save(character));
    }

    //Tested
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete character")
    @DeleteMapping("/{name}")
    public void deleteCharacter(@PathVariable("name") String name) {
        Character character = characterService.findByName(name)
                .orElseThrow(() -> notFound(name));
        for (Item item : character.getItemsList()) {
            item.getOwnersList().remove(character);
            itemService.save(item);
        }
        characterService.delete(character);
    }

    //Tested
    @Operation(summary = "Add an item to a character")
    @PostMapping("/{name}/{idItem}")
    public void addItemOwned(@PathVariable("name") String name, @PathVariable("idItem") long idItem) {
        Character character = characterService.findByName(name)
                .orElseThrow(() -> notFound(name));

        Item item = itemService.findById(idItem)
                .orElseThrow(() -> ItemController.notFound(idItem));

        List<Item> itemList = character.getItemsList();
        if (!itemList.contains(item)) {
            assert !item.getOwnersList().contains(character);
            itemList.add(item);
            item.getOwnersList().add(character);
            characterService.save(character);
            itemService.save(item);
        }
    }

    //Tested
    @Operation(summary = "Remove an item from a character")
    @DeleteMapping("/{name}/{idItem}")
    public void removeItemOwned(@PathVariable("name") String name, @PathVariable("idItem") long idItem) {
        Character character = characterService.findByName(name)
                .orElseThrow(() -> notFound(name));

        Item item = itemService.findById(idItem)
                .orElseThrow(() -> ItemController.notFound(idItem));

        List<Item> itemsList = character.getItemsList();
        if (itemsList.contains(item)) {
            assert item.getOwnersList().contains(character);
            itemsList.remove(item);
            item.getOwnersList().remove(character);
            itemService.save(item);
            characterService.save(character);
        }
    }
}
