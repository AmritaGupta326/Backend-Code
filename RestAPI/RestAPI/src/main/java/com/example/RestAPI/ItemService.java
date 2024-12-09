package com.example.RestAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Model> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Model> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Model saveItem(Model item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
