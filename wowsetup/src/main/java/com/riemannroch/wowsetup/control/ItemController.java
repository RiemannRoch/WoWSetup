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
@RequestMapping("/wowsetup/items")
public class ItemController {
    final ItemService itemService;
    final CharacterService characterService;

    public ItemController(ItemService itemService, CharacterService characterService) {
        this.itemService = itemService;
        this.characterService = characterService;
    }

    public ResponseEntity<Object> notFound(){
        return new ResponseEntity<>("Item not found!",HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Object> getAllItems(){
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addItem(@RequestBody ItemModel itemModel){
        return new ResponseEntity<>(itemService.save(itemModel), HttpStatus.CREATED);
    }

    @PutMapping("/{idItem}")
    public ResponseEntity<Object> updateItem(@RequestBody ItemModel itemModel, @PathVariable(value = "idItem") long idItem){
        Optional<ItemModel> itemModelOptional = itemService.findById(idItem);
        if (itemModelOptional.isEmpty()){
            return notFound();
        }
        itemModel.setIdItem(idItem);
        return new ResponseEntity<>(itemService.save(itemModel),HttpStatus.OK);
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "idItem") long idItem){
        Optional<ItemModel> itemModelOptional = itemService.findById(idItem);
        if (itemModelOptional.isEmpty()){
            return notFound();
        }
        itemService.delete(itemModelOptional.get());
        return new ResponseEntity<>("Item deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<Object> showItem(@PathVariable(value = "idItem") long idItem){
        Optional<ItemModel> itemModelOptional = itemService.findById(idItem);
        if (itemModelOptional.isEmpty()){
            return notFound();
        }
        List<Object> response = new ArrayList<>();
        response.add(itemModelOptional);
        response.add(characterService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
