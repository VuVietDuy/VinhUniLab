package com.VinhUniLab.service;

import java.util.List;

public interface SuperService<E, T> {

    E create(E t);

    void delete(T id) throws Exception;

    List<E> getAll();


    E getById(T id) throws Exception;

    E update(T id, E t) throws Exception;

    List<E> getByIds(List<T> ids);

    List<E> save(List<E> es);
}