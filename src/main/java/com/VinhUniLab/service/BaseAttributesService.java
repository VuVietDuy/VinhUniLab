package com.VinhUniLab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class BaseAttributesService<R extends CrudRepository<E, T>, E, T> {
    @Autowired
    protected R repository;
}
