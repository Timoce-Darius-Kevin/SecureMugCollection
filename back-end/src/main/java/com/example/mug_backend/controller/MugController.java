package com.example.mug_backend.controller;

import com.example.mug_backend.service.MugService;
import com.example.mug_backend.model.Mug;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

@RestController
@RequestMapping("/mugs")
public class MugController {
    private final MugService mugService;

    public MugController(MugService service){
        this.mugService = service;
    }

    // Get all mugs (async)
    @Async
    @GetMapping
    public CompletableFuture<List<Mug>> getAll(){
        return CompletableFuture.completedFuture(mugService.findAll());
    }

    // Get mug by ID (async, with 404 handling)
    @Async
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Mug>> getById(@PathVariable Long id) {
        Mug mug = mugService.findById(id);
        if (mug != null) {
            return CompletableFuture.completedFuture(ResponseEntity.ok(mug));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    // Create a new mug
    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Mug>> create(@RequestBody Mug mug) {
        Mug created = mugService.save(mug);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(created));
    }

    // Update an existing mug
    @Async
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Mug>> update(@PathVariable Long id, @RequestBody Mug mug) {
        Mug updated = mugService.update(id, mug);
        if (updated != null) {
            return CompletableFuture.completedFuture(ResponseEntity.ok(updated));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    // Delete a mug
    @Async
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> delete(@PathVariable Long id) {
        boolean deleted = mugService.delete(id);
        if (deleted) {
            return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }
}
