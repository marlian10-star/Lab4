package com.example.bookstore.products;

import com.example.store.dto.ProductDTO;
import com.example.store.model.Category;
import com.example.store.model.Product;
import com.example.store.repository.CategoryRepository;
import com.example.store.repository.ProductRepository;
import com.example.store.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(product -> {
                    ProductDTO dto = new ProductDTO();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setPrice(product.getPrice());
                    dto.setCategoryId(product.getCategory().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(savedProduct.getId());
        productDTO.setName(savedProduct.getName());
        productDTO.setPrice(savedProduct.getPrice());
        productDTO.setCategoryId(savedProduct.getCategory().getId());

        return productDTO;
    }
}