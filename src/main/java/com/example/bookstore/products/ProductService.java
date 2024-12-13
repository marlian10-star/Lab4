package com.example.bookstore.products;

import com.example.store.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getProductsByCategory(Long categoryId);
    ProductDTO saveProduct(ProductDTO productDTO);
}