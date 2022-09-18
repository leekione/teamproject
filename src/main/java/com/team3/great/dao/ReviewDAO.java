package com.team3.great.dao;


import com.team3.great.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDAO {

    //리뷰등록
    Long save(Review review);

    //리뷰목록
    List<Review> findAll();

    //리뷰조회
    Optional<Review> findByReviewId(Long reviewNumber);

    //리뷰변경
    int update(Long reviewNumber,Review review);

    //리뷰삭제
    int deleteByReviewId(Long reviewNumber);
}
