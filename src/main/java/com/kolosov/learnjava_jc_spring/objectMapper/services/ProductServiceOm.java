package com.kolosov.learnjava_jc_spring.objectMapper.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.objectMapper.models.ProductOm;
import com.kolosov.learnjava_jc_spring.objectMapper.repository.ProductRepositoryOm;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceOm extends CrudService<ProductOm, Long> {
    public ProductServiceOm(ProductRepositoryOm productRepository) {
        super(productRepository);
    }
}
