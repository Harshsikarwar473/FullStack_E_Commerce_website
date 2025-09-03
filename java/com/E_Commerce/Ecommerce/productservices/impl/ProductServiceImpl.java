package com.E_Commerce.Ecommerce.productservices.impl;

import com.E_Commerce.Ecommerce.exception.ProductException;
import com.E_Commerce.Ecommerce.model.Category;
import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.Seller;
import com.E_Commerce.Ecommerce.productservices.ProductService;
import com.E_Commerce.Ecommerce.repo.Categoryrepo;
import com.E_Commerce.Ecommerce.repo.Productrepo;
import com.E_Commerce.Ecommerce.request.CreateProductRequest;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final Productrepo productrepo;

    private final Categoryrepo categoryrepo;
    @Override
    public Product createProduct(CreateProductRequest req, Seller seller) {
        Category category1 = categoryrepo.findByCatagoryId(req.getCategory());

        if(category1==null){
            Category category= new Category();
            category.setCatagoryId(req.getCategory());
            category.setLevel(1);
            category1=categoryrepo.save(category);
        }
        Category category2 = categoryrepo.findByCatagoryId(req.getCategory2());

        if(category2==null){
            Category category=new Category();
            category.setCatagoryId(req.getCategory2());
            category.setLevel(2);
            category.setParent(category1);
            category2=categoryrepo.save(category);
        }

        Category category3= categoryrepo.findByCatagoryId(req.getCategory3());

        if(category3==null){
            Category category=new Category();
            category.setCatagoryId(req.getCategory3());
            category.setLevel(3);
            category.setParent(category2);
            category3=categoryrepo.save(category);
        }

        int dis = calculateDiscountPercentage(req.getMrp(), req.getSellingprice());

        Product product= new Product();
        product.setSeller(seller);
        product.setCategory(category3);
        product.setDiscription(req.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setTittle(req.getTittle());
        product.setColor(req.getColor());
        product.setSellingPrice(req.getSellingprice());
        product.setImages(req.getImages());
        product.setMrpprice(req.getMrp());
        product.setSizes(req.getSize());
        product.setDiscount(dis);
        product.setNumrating(req.getNumrating());
        product.setQuantity(req.getQuantity());



        return productrepo.save(product);
    }

    private int calculateDiscountPercentage(int mrp, int sellingprice) {

        if(mrp == 0 ){
            throw new IllegalArgumentException("mrp should be gretter than zero ");
        }
        double dis = mrp-sellingprice;
        double percent = (dis / mrp)*100;
        return  (int) percent;
    }

    @Override
    public void DeleteProducts(Long id) throws ProductException {
        Product product = FindProductById(id);
        productrepo.delete(product);
    }

    @Override
    public Product UpdateProduct(Long id, Product product) throws ProductException {

       FindProductById(id);
       product.setId(id);
        return productrepo.save(product);
    }

    @Override
    public Product FindProductById(Long id) throws ProductException {
        return productrepo.findById(id).orElseThrow(()->
                new ProductException("product is not found with this id ...." +id));
    }

    @Override
    public List<Product> SearchProduct(String query) {
        return productrepo.searchProduct(query);
    }

    @Override
    public Page<Product> getAllProducts(String category, String brand, String color, String size, Integer minprice, Integer maxprice, Integer mindescount, String sort, String stock, Integer pagenumber) {

        Specification<Product> specs = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if(category!=null){
                Join<Product,Category>  categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"),category));
            }

            if(color!=null && !color.isEmpty()){
//                System.out.println("color : "+color);
                predicates.add(criteriaBuilder.equal(root.get("color"),color));
            }

            if(size!=null && !size.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("size"),size));

            }

            if(minprice!=null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"),minprice));
            }

            if(maxprice!=null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"),maxprice));

            }

            if(mindescount!=null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discount"),mindescount));
            }

            if(stock!=null){
                predicates.add(criteriaBuilder.equal(root.get("stock"),stock));
            }

            return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));






        };
        Pageable pageable;
        if(sort!=null && !sort.isEmpty()){
            pageable = switch (sort) {
                case "price_low" ->
                        PageRequest.of(pagenumber != null ? pagenumber : 0, 10, Sort.by("sellingPrice").ascending());
                case "price_high" ->
                        PageRequest.of(pagenumber != null ? pagenumber : 0, 10, Sort.by("sellingPrice").descending());
                default -> PageRequest.of(pagenumber != null ? pagenumber : 0, 10, Sort.unsorted());
            };
        }
        else{
            pageable=PageRequest.of(pagenumber!=null? pagenumber:0 , 10 , Sort.unsorted());
        }
        return productrepo.findAll(specs, pageable);
    }

    @Override
    public List<Product> getProductBySellerId(Long id) {

        return productrepo.findBySellerId(id);
    }
}
