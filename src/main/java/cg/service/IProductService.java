package cg.service;

import cg.model.Category;
import cg.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    Page<Product> getAllProductByName(String name, Pageable pageable);

    Page<Product> getAllProductByCategory(Category category, Pageable pageable);
}
