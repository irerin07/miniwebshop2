package com.tistory.irerin07.miniwebshop.controller;

import com.mysema.commons.lang.Assert;
import com.tistory.irerin07.miniwebshop.domain.Category;
import com.tistory.irerin07.miniwebshop.domain.Product;
import com.tistory.irerin07.miniwebshop.security.ShopSecurityUser;
import com.tistory.irerin07.miniwebshop.service.CategoryService;
import com.tistory.irerin07.miniwebshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/wrtie")
    public String writeForm(Model model){
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "products/writeform";
    }

    @PostMapping("/write")
    public String write(@RequestParam(name = "name")String name,
                        @RequestParam(name = "description")String description,
                        @RequestParam(name = "price")int price,
                        @RequestParam(name = "stock")int stock,
                        @RequestParam(name = "categoryId")Long categoryId){
        Assert.hasText(name, "상품 이름을 입력하세요.");
        Assert.hasText(description, "상품 설명을 입력하세요.");

        ShopSecurityUser securityUser =
                (ShopSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);

        productService.addProduct(product, name, description, price, stock, categoryId);

        return "redirect:/welcome";
    }

}
