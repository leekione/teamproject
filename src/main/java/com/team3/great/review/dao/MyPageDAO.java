package com.team3.great.review.dao;


import com.team3.great.Member;

import java.util.List;
import java.util.Optional;

public interface MyPageDAO {

    //리뷰등록
    Review save(Review review);

    //리뷰조회 - 회원번호
    List<Review> findByMemNumber(Long memNumber);

    //리뷰조회 - 프로필에서 조회
    List<Review> findByBuyerNumber(Long memNumber);

    //리뷰조회 - 리뷰번호
    Optional<Review> findByReviewNumber(Long reviewNumber);

    //리뷰변경
    int update(Long reviewNumber,Review review);

    //리뷰삭제
    int deleteByReviewId(Long reviewNumber);

    //회원 조회 - 회원 번호
    Optional<Member> findMember(Long memNumber);

    //즐겨찾기 추가
    Bookmark addBookmark(Bookmark bookmark);

    //즐겨찾기 회원 조회
    List<Bookmark> findBookmark(Long memNumber);
}