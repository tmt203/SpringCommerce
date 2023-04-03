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
        return repo.save(updateCategory);
    }

    @Override
    public void deleteById(Long id) {
        Category deleteCategory = repo.getReferenceById(id);
        deleteCategory.setDeleted(true);
        deleteCategory.setActivated(false);
        repo.save(deleteCategory);
    }

    @Override
    public void enabledById(Long id) {
        Category enabledCategory = repo.getReferenceById(id);
        enabledCategory.setActivated(true);
        enabledCategory.setDeleted(false);
        repo.save(enabledCategory);
    }

    @Override
    public List<Category> findAllByActivated() {
        return repo.findAllByActivated();
    }
}
