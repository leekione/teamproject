package com.team3.great.review.dao;


import java.util.List;

public interface ReviewDAO {

    //리뷰등록
    Review save(Review review);

    //리뷰목록
    List<Review> findAll();

    //리뷰조회 - 회원번호
    List<Review> findByMemNumber(Long memNumber);

    //리뷰조회 - 리뷰번호
    Review findByReviewNumber(Long reviewNumber);

    //리뷰변경
    int update(Long reviewNumber,Review review);

    //리뷰삭제
    int deleteByReviewId(Long reviewNumber);
}