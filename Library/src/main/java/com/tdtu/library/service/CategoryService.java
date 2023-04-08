package com.tdtu.library.service;

import com.tdtu.library.dto.CategoryDto;
import com.tdtu.library.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    /* Admin */
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();

    /* Customer */
    List<CategoryDto> getCategoryAndProducts();
}
