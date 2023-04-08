package com.tdtu.customer.controller;

import com.tdtu.library.dto.CategoryDto;
import com.tdtu.library.model.Category;
import com.tdtu.library.model.Product;
import com.tdtu.library.service.CategoryService;
import com.tdtu.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/shop-detail")
    public String products(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Product> listViewProducts = productService.listViewProducts();
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProducts();

        model.addAttribute("title", "Shop detail");
        model.addAttribute("products", products);
        model.addAttribute("listViewProducts", listViewProducts);
        model.addAttribute("categories", categoryDtoList);
        return "shop-detail";
    }

    @GetMapping("/find-product/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        List<Product> relatedProducts = productService.getRelatedProductsById(product.getCategory().getId());
        model.addAttribute("title", "Product Detail");
        model.addAttribute("product", product);
        model.addAttribute("relatedProducts", relatedProducts);
        return "product-detail";
    }

    @GetMapping("/products-in-category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProducts();
        List<Product> products = productService.getProductsInCategory(categoryId);
        model.addAttribute("title", "All " + category.getName() + " cars");
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("categories",categoryDtoList);
        return "products-in-category";
    }

    @GetMapping("/sort-price-asc")
    public String filterProductsByAsc(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProducts();
        List<Category> categories = categoryService.findAllByActivated();
        List<Product> products = productService.filterProductsByPriceAsc();
        model.addAttribute("categoryDtoList", categoryDtoList);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "sort-price";
    }

    @GetMapping("/sort-price-desc")
    public String filterProductsByDesc(Model model) {
        List<CategoryDto> categoryDtoList = categoryService.getCategoryAndProducts();
        List<Category> categories = categoryService.findAllByActivated();
        List<Product> products = productService.filterProductsByPriceDesc();
        model.addAttribute("categoryDtoList", categoryDtoList);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "sort-price";
    }
}
