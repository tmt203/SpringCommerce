package com.tdtu.admin.controller;

import com.tdtu.library.model.Category;
import com.tdtu.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(Model model, Principal principal) {
        // Principal: Check user already login or not
        if (principal == null) {
            return "redirect:/login";
        }
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("size", categories.size());
        model.addAttribute("title", "Category");
        model.addAttribute("newCategory", new Category());
        return "categories";
    }

    @PostMapping("/categories")
    public String addCategory(@ModelAttribute("newCategory") Category category, RedirectAttributes ra) {
        try {
            categoryService.save(category);
            ra.addFlashAttribute("successMessage", "Add category successfully");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("errorMessage", "This category name already existed");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Server error. Something went wrong");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.PUT})
    @ResponseBody
    public Category findById(Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/update-category")
    public String updateCategory(Category category, RedirectAttributes ra) {
        try {
            categoryService.update(category);
            ra.addFlashAttribute("successMessage", "Update category successfully");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("errorMessage", "This category name already existed");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Server error. Something went wrong");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete-category", method = {RequestMethod.GET, RequestMethod.PUT})
    public String deleteCategory(Long id, RedirectAttributes ra) {
        try {
            categoryService.deleteById(id);
            ra.addFlashAttribute("successMessage", "Delete category successfully");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Server error. Fail to delete");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/enable-category", method = {RequestMethod.GET, RequestMethod.PUT})
    public String enableCategory(Long id, RedirectAttributes ra) {
        try {
            categoryService.enabledById(id);
            ra.addFlashAttribute("successMessage", "Enable category successfully");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Server error. Fail to enable");
        }
        return "redirect:/categories";
    }

}
