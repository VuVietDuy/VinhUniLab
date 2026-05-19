package com.VinhUniLab.service;

import com.VinhUniLab.model.request.SearchReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface SuperService<E, T> {
    default Class<E> getEntityClass() {
        // Bỏ qua cảnh báo unchecked cast vì việc ép kiểu từ Type sang Class<E> là an toàn trong context này
        //noinspection unchecked
        return (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    E create(E t);

    void delete(T id) throws Exception;

    List<E> getAll();


    E getById(T id) throws Exception;

    E update(T id, E t) throws Exception;

    List<E> getByIds(List<T> ids);

    List<E> save(List<E> es);

    Page<E> search(SearchReq req);
}