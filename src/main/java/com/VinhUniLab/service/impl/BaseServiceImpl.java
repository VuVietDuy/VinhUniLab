package com.VinhUniLab.service.impl;


import com.VinhUniLab.entity.BaseEntity;
import com.VinhUniLab.repository.BaseRepository;
import com.VinhUniLab.service.BaseAttributesService;
import com.VinhUniLab.service.BaseService;
import com.VinhUniLab.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Slf4j
public abstract class BaseServiceImpl<E extends BaseEntity, R extends BaseRepository<E>>
        extends BaseAttributesService<R, E, Long>
        implements BaseService<E> {
    protected static final String DELETED_FILTER = ";status!=-1";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public E create(E t) {
        return this.repository.save(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public E update(Long id, E t) {
        E entity = getById(id);
        ObjectMapperUtils.map(t, entity);
        return this.repository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        this.repository.deleteById(id);
    }



    @Override
    public E getById(Long id) throws RuntimeException {
        return this.repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<E> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<E> getByIds(List<Long> ids) {
        return this.repository.findAllById(ids);
    }

    @Override
    public List<E> save(List<E> es) {
        return this.repository.saveAll(es);
    }

}