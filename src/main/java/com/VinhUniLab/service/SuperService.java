package com.VinhUniLab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SuperService<E, T> {

    E create(E t);

    void delete(T id) throws Exception;

    List<E> getAll();


    E getById(T id) throws Exception;

    E update(T id, E t) throws Exception;

    List<E> getByIds(List<T> ids);

    List<E> save(List<E> es);

    Page<E> search(Pageable pageable);
}