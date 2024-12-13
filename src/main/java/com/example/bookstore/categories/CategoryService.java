package com.example.bookstore.categories;

import com.example.store.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
}