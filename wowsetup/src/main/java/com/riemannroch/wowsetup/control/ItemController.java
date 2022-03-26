package com.riemannroch.wowsetup.control;

import com.riemannroch.wowsetup.model.*;
import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.request.ItemRequest;
import com.riemannroch.wowsetup.service.CharacterService;
import com.riemannroch.wowsetup.service.EquivalencePointSystemService;
import com.riemannroch.wowsetup.service.ItemEquivalencePointsService;
import com.riemannroch.wowsetup.service.ItemService;
import com.riemannroch.wowsetup.view.character.CharacterView;
import com.riemannroch.wowsetup.view.item.ItemCompleteView;
import com.riemannroch.wowsetup.view.item.ItemView;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/wowsetup/items")
public class ItemController {
    final ItemService itemService;
    final CharacterService characterService;
    final EquivalencePointSystemService equivalencePointSystemService;
    final ItemEquivalencePointsService itemEquivalencePointsService;

    public static NotFoundException notFound(long id){
        return new NotFoundException("Item not found for ID: " + id);
    }

    //Tested
    @Operation(summary = "Show all items")
    @GetMapping
    public List<ItemView> getAllItems() {
        return ItemView.listOf(itemService.findAll());
    }

    //Tested
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Insert new item")
    @PostMapping
    public ItemCompleteView addItem(@RequestBody ItemRequest itemRequest) {
        Item item = new Item();
        BeanUtils.copyProperties(itemRequest,item);
        item.setOwnersList(new ArrayList<>());
        itemService.save(item);
        return new ItemCompleteView(item);
    }

    //Tested
    @Operation(summary = "Update item's attributes")
    @PutMapping("/{idItem}")
    public ItemCompleteView updateItem(@RequestBody Item newItem, @PathVariable(value = "idItem") long idItem) {
        Item item = itemService.findById(idItem)
                .orElseThrow(() -> notFound(idItem));
        newItem.setIdItem(idItem);
        newItem.setOwnersList(item.getOwnersList());
        itemService.save(newItem);
        return new ItemCompleteView(newItem);
    }

    //Tested
    @Operation(summary = "Delete item")
    @DeleteMapping("/{idItem}")
    public void deleteItem(@PathVariable(value = "idItem") long idItem) {
        Item item = itemService.findById(idItem)
                .orElseThrow(() -> notFound(idItem));
        itemService.delete(item);
    }

    //Tested
    @Operation(summary = "Show item's attributes and owners list")
    @GetMapping("/{idItem}")
    public ItemCompleteView showItem(@PathVariable(value = "idItem") long idItem) {
        Item item = itemService.findById(idItem)
                .orElseThrow(() -> notFound(idItem));
        return new ItemCompleteView(item);
    }

}
