package com.example.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Model> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getItemById(@PathVariable Long id) {
        Optional<Model> item = itemService.getItemById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Model createItem(@RequestBody Model item) {
        return itemService.saveItem(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateItem(@PathVariable Long id, @RequestBody Model itemDetails) {
        Optional<Model> item = itemService.getItemById(id);
        if (item.isPresent()) {
            Model updatedItem = item.get();
            updatedItem.setName(itemDetails.getName());
            updatedItem.setDescription(itemDetails.getDescription());
            return ResponseEntity.ok(itemService.saveItem(updatedItem));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
