package com.kolosov.learnjava_jc_spring.objectMapper.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.common.errors.exceptions.BadRequestException;
import com.kolosov.learnjava_jc_spring.objectMapper.models.ProductOm;
import com.kolosov.learnjava_jc_spring.objectMapper.repository.ProductRepositoryOm;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ProductServiceOm extends CrudService<ProductOm, Long> {

    private final ProductRepositoryOm productRepository;

    public ProductServiceOm(ProductRepositoryOm productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    public List<ProductOm> productsWhereIdIn(Collection<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }

    @Transactional
    public ProductOm increaseQuantityStock(long id, @Min(1) Integer count) {
        return changeQuantity(id, count);
    }

    @Transactional
    public ProductOm decreaseQuantityStock(long id, @Min(1) Integer count) {
        return changeQuantity(id, count * (-1));
    }


    private ProductOm changeQuantity(long id, int count) {
        ProductOm product = getById(id);
        if (product == null) {
            throw new BadRequestException(String.format("Error creating order. Product with ID %d does not exist", id));
        }

        if (count < 0 && product.getQuantityStock() < count) {
            throw new BadRequestException(String.format("Error creating order. Product with ID %d has no enough quantity in stock", id));
        }

        product.setQuantityStock(product.getQuantityStock() + count);

        return save(product);
    }
}
