package com.E_Commerce.Ecommerce.productservices;

import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.Review;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest req , User user , Product product);
    List<Review> getReviewProductId(Long productid);
    Review updateReview(Long reviewId , String reviewTect , double rating , Long userId) throws Exception;
    void deleteReview(Long reviewId , Long userId) throws Exception;
    Review getReviewById(Long reviewId ) throws Exception;
}
