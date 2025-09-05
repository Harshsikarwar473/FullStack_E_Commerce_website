package com.E_Commerce.Ecommerce.productservices.impl;


import com.E_Commerce.Ecommerce.model.Product;
import com.E_Commerce.Ecommerce.model.Review;
import com.E_Commerce.Ecommerce.model.User;
import com.E_Commerce.Ecommerce.productservices.ReviewService;
import com.E_Commerce.Ecommerce.repo.ReviewRepo;
import com.E_Commerce.Ecommerce.request.CreateReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo  reviewRepo;
    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReviewText(req.getReviewText());
        review.setRatting(req.getReviewRating());
        review.setProductimage(req.getProductImages());

        product.getReviews().add(review);
        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviewProductId(Long productid) {

        return reviewRepo.findByProductId(productid);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {

        Review review = getReviewById(reviewId);

        if(review.getUser().getId()==userId){
            review.setReviewText(reviewText);
            review.setRatting(rating);
            return reviewRepo.save(review);
        }
        throw new Exception("you cant update other person review ...");

    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if( review.getUser().getId()!=userId){
            throw  new Exception("you cant delete this review...");
        }
        reviewRepo.delete(review);
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepo.findById(reviewId).orElseThrow(()->
                new Exception("Review not found ..."));
    }
}
