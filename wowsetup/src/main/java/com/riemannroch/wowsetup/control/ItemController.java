package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.*;
import com.riemannroch.wowsetup.service.CharacterService;
import com.riemannroch.wowsetup.service.EquivalencePointSystemService;
import com.riemannroch.wowsetup.service.ItemEquivalencePointsService;
import com.riemannroch.wowsetup.service.ItemService;
import com.riemannroch.wowsetup.view.character.CharacterView;
import com.riemannroch.wowsetup.view.item.ItemCompleteView;
import com.riemannroch.wowsetup.view.item.ItemView;
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
    final EquivalencePointSystemService equivalencePointSystemService;
    final ItemEquivalencePointsService itemEquivalencePointsService;

    public ItemController(ItemService itemService, CharacterService characterService, EquivalencePointSystemService equivalencePointSystemService, ItemEquivalencePointsService itemEquivalencePointsService) {
        this.itemService = itemService;
        this.characterService = characterService;
        this.equivalencePointSystemService = equivalencePointSystemService;
        this.itemEquivalencePointsService = itemEquivalencePointsService;
    }

    public static ResponseEntity<Object> notFound() {
        return new ResponseEntity<>("Item not found!", HttpStatus.NOT_FOUND);
    }

    //Tested
    @GetMapping
    public ResponseEntity<Object> getAllItems() {
        List<ItemView> itemViewList = new ArrayList<>();
        for (ItemModel itemModel : itemService.findAll()) {
            itemViewList.add(new ItemView(itemModel));
        }
        return new ResponseEntity<>(itemViewList, HttpStatus.OK);
    }
    //Tested
    @PostMapping
    public ResponseEntity<Object> addItem(@RequestBody ItemModel itemModel) {
        itemService.save(itemModel);
        for (EquivalencePointSystemModel eps : equivalencePointSystemService.findAll()) {
            itemEquivalencePointsService.save(new ItemEquivalencePoints(itemModel, eps));
        }
        return new ResponseEntity<>(new ItemView(itemModel), HttpStatus.CREATED);
    }
    //Tested
    @PutMapping("/{idItem}")
    public ResponseEntity<Object> updateItem(@RequestBody ItemModel newItem, @PathVariable(value = "idItem") long idItem) {
        Optional<ItemModel> itemModelOptional = itemService.findById(idItem);
        if (itemModelOptional.isEmpty()) {
            return notFound();
        }
        newItem.setIdItem(idItem);
        itemService.save(newItem);

        for (EquivalencePointSystemModel eps : equivalencePointSystemService.findAll()) {
            ItemEquivalencePoints itemEquivalencePoints = new ItemEquivalencePoints(newItem, eps);
            itemEquivalencePointsService.save(itemEquivalencePoints);
        }
        return new ResponseEntity<>(new ItemView(newItem), HttpStatus.OK);
    }
    //Tested
    @DeleteMapping("/{idItem}")
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "idItem") long idItem) {
        Optional<ItemModel> itemModelOptional = itemService.findById(idItem);
        if (itemModelOptional.isEmpty()) {
            return notFound();
        }
        ItemModel item = itemModelOptional.get();
        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsService.findByItem(item)) {
            itemEquivalencePointsService.delete(itemEquivalencePoints);
        }
        itemService.delete(item);
        return new ResponseEntity<>("Item deleted successfully!", HttpStatus.OK);
    }
    //Tested
    @GetMapping("/{idItem}")
    public ResponseEntity<Object> showItem(@PathVariable(value = "idItem") long idItem) {
        Optional<ItemModel> itemModelOptional = itemService.findById(idItem);
        if (itemModelOptional.isEmpty()) {
            return notFound();
        }
        ItemModel item = itemModelOptional.get();

        List<Object> response = new ArrayList<>();
        response.add(new ItemCompleteView(item));
        response.add(CharacterView.listOf(item.getOwnersList()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
