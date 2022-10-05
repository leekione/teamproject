package com.kh.great.review.dao;

import com.kh.great.domain.dao.mypage.MyPageDAO;
import com.kh.great.domain.dao.mypage.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
class ReviewDAOImplTest {

    @Autowired
    private MyPageDAO reviewDAO;

    @Test
    void findByReviewNumber() {
        Optional<Review> byReviewNumber = reviewDAO.findByReviewNumber(2l);
        Review review = byReviewNumber.get();
        log.info("byReviewNumber={}",byReviewNumber);
        log.info("review={}",review);
    }

    @Test
    void update() {
        Review review = new Review();
        review.setReviewNumber(54l);
        review.setBuyerNumber(1l);
        review.setContent("Å×½ºÆ®");
        review.setGrade(1l);
        reviewDAO.update(54l,review);
        log.info("review={}",review);
    }

    @Test
    void deleteByReviewId() {
//        Review review = new Review();
//        review.setReviewNumber(25l);
        reviewDAO.deleteByReviewId(25l);
    }
}