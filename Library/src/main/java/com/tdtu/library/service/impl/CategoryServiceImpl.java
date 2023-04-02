package com.tdtu.library.service.impl;

import com.tdtu.library.model.Category;
import com.tdtu.library.repository.CategoryRepository;
import com.tdtu.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repo;

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

    @Override
    public Category save(Category category) {
        Category saveCategory = new Category(category.getName());
        return repo.save(saveCategory);
    }

    @Override
    public Category findById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category updateCategory = repo.findById(category.getId()).get();
        updateCategory.setName(category.getName());
        updateCategory.set_activated(category.is_activated());
        updateCategory.set_deleted(category.is_deleted());
        return repo.save(updateCategory);
    }

    @Override
    public void deleteById(Long id) {
        Category deleteCategory = repo.getReferenceById(id);
        deleteCategory.set_deleted(true);
        deleteCategory.set_activated(false);
        repo.save(deleteCategory);
    }

    @Override
    public void enabledById(Long id) {
        Category enabledCategory = repo.getReferenceById(id);
        enabledCategory.set_activated(true);
        enabledCategory.set_deleted(false);
        repo.save(enabledCategory);
    }
}
