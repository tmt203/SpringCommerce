package com.tdtu.admin.controller;

import com.tdtu.library.dto.ProductDto;
import com.tdtu.library.model.Category;
import com.tdtu.library.model.Product;
import com.tdtu.library.service.CategoryService;
import com.tdtu.library.service.ProductService;
import com.tdtu.library.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public String products(Model model, Principal principal) {
        // Principal: Check user already logged in or not
        if (principal == null) {
            return "redirect:/login";
        }
        List<ProductDto> productDtoList = productService.findAll();
        model.addAttribute("title", "Product Management");
        model.addAttribute("products", productDtoList);
        model.addAttribute("size", productDtoList.size());
        return "products";
    }

    @GetMapping("/products/{pageNumber}")
    public String productsPage(@PathVariable("pageNumber") int pageNumber, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Page<Product> products = productService.pageProducts(pageNumber);
        model.addAttribute("title", "Product Management");
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", pageNumber);
        return "products";
    }

    @GetMapping("/search-result/{pageNumber}")
    public String searchProducts(@PathVariable("pageNumber") int pageNumber,
                                 @RequestParam("keyword") String keyword,
                                 Model model,
                                 Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Page<Product> products = productService.searchProducts(pageNumber, keyword);
        System.out.println(products.getSize());
        model.addAttribute("title", "Search result");
        model.addAttribute("products", products);
        model.addAttribute("size", products.getSize());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", products.getTotalPages());
        return "result-products";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("title","Add product");
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categories", categories);
        return "add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("productDto") ProductDto productDto,
                              @RequestParam("productImage")MultipartFile productImage,
                              RedirectAttributes ra) {
        try {
            productService.save(productImage, productDto);
            ra.addFlashAttribute("successMessage", "Add product successfully");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Add product failed !");
        }
        return "redirect:/products";
    }

    @GetMapping("/update-product/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        ProductDto productDto = productService.findById(id);
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("title", "Update product");
        model.addAttribute("productDto", productDto);
        model.addAttribute("categories", categories);
        return "update-product";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @ModelAttribute("productDto") ProductDto productDto,
                                @RequestParam("productImage") MultipartFile productImage,
                                RedirectAttributes ra) {
        try {
            productService.update(productImage, productDto);
            ra.addFlashAttribute("successMessage", "Update product successfully");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Update product failed !");
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "/enable-product/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String enableProduct(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            productService.enableById(id);
            ra.addFlashAttribute("successMessage", "Enabled product successfully");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Enabled product failed !");
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "/delete-product/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            productService.deleteById(id);
            ra.addFlashAttribute("successMessage", "Deleted product successfully");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("errorMessage", "Deleted product failed !");
        }
        return "redirect:/products";
    }
}
