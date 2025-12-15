package com.kolosov.learnjava_jc_spring.common;

import com.kolosov.learnjava_jc_spring.common.errors.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public abstract class CrudService<ENTITY, ID> {

    private final JpaRepository<ENTITY, ID> jpaRepository;

    @Transactional(readOnly = true)
    public List<ENTITY> getAll() {
        return jpaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ENTITY> getAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).getContent();
    }

    @Transactional
    public ENTITY save(ENTITY entity) {
        return jpaRepository.save(entity);
    }

    @Transactional
    public ENTITY update(ID id, ENTITY entity) {
        boolean exist = jpaRepository.existsById(id);
        if (!exist) {
            throw new ResourceNotFoundException(String.format("Entity %s with id %s not found", entity.getClass().getSimpleName(), id));
        }

        return jpaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public ENTITY getById(ID id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }

    public boolean existById(ID id) {
        return jpaRepository.existsById(id);
    }
}
