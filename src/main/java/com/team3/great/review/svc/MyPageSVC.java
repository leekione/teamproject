package com.team3.great.review.svc;


import com.team3.great.review.dao.Review;

import java.util.List;
import java.util.Optional;

public interface MyPageSVC {
    Review save(Review review);

    //리뷰조회 - 회원번호로 조회
    List<Review> findByMemNumber(Long memNumber);

    //리뷰조회 - 리뷰번호로 조회
    Optional<Review> findByReviewNumber(Long reviewNumber);

    //리뷰변경
    int update(Long reviewNumber,Review review);

    //리뷰삭제
    int deleteByReviewId(Long reviewNumber);
}