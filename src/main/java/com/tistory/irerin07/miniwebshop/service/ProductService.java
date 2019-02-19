package com.tistory.irerin07.miniwebshop.service;

import com.tistory.irerin07.miniwebshop.domain.Category;
import com.tistory.irerin07.miniwebshop.domain.Product;
import com.tistory.irerin07.miniwebshop.repository.CategoryRepository;
import com.tistory.irerin07.miniwebshop.repository.ProductRepository;
import com.tistory.irerin07.miniwebshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void addProduct(Product product, String name, String description, int price, int stock, Long categoryId) {
        Optional<Category> optionalCategory
                = categoryRepository.findById(categoryId);
        Optional<Account> optionalAccount
                = accountRepository.findById(accountId);
        product.setAccount(optionalAccount.get());
        product.setCategory(optionalCategory.get());

        return productRepository.save(product);
    }
}
