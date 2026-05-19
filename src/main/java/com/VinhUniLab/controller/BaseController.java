package com.VinhUniLab.controller;

import com.VinhUniLab.entity.BaseEntity;
import com.VinhUniLab.model.request.SearchReq;
import com.VinhUniLab.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class BaseController<E extends BaseEntity, S extends BaseService<E>> {

    @Autowired
    protected S service;


    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody E dto) {
        dto = this.service.create(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id) throws Exception {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/details")
    public ResponseEntity<?> getById(@RequestParam(value = "id") Long id) throws Exception {
        E dto = this.service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id") Long id, @Valid @RequestBody E d) throws Exception {
        this.service.update(id, d);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@Valid SearchReq req) {
        Page<E> pageD = this.service.search(req);
        return ResponseEntity.ok(pageD);
    }

}

