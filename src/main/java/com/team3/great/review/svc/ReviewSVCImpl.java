package com.team3.great.review.svc;

import com.team3.great.review.dao.Review;
import com.team3.great.review.dao.ReviewDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewSVCImpl implements ReviewSVC {

    private final ReviewDAO reviewDAO;

    //등록
    @Override
    public Long save(Review review) {
        return reviewDAO.save(review);
    }

    //목록
    @Override
    public List<Review> findAll() {
        return reviewDAO.findAll();
    }

    //조회
    @Override
    public Optional<Review> findByReviewId(Long reviewNumber) {
        return reviewDAO.findByReviewId(reviewNumber);
    }

    //수정
    @Override
    public int update(Long reviewNumber, Review review) {
        return reviewDAO.update(reviewNumber,review);
    }

    //삭제
    @Override
    public int deleteByReviewId(Long reviewNumber) {
        return reviewDAO.deleteByReviewId(reviewNumber);
    }
}