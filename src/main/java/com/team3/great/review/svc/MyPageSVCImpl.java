package com.team3.great.review.svc;

import com.team3.great.Member;
import com.team3.great.review.dao.Review;
import com.team3.great.review.dao.MyPageDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageSVCImpl implements MyPageSVC {

    private final MyPageDAO myPageDAO;

    //리뷰 등록
    @Override
    public Review save(Review review) {
        return myPageDAO.save(review);
    }

    //리뷰조회 - 회원번호로 조회
    @Override
    public List<Review> findByMemNumber(Long memNumber) {
        return myPageDAO.findByMemNumber(memNumber);
    }

    //리뷰조회 - 리뷰번호로 조회

    @Override
    public Optional<Review> findByReviewNumber(Long reviewNumber) {
        return myPageDAO.findByReviewNumber(reviewNumber);
    }

    //리뷰 수정
    @Override
    public int update(Long reviewNumber, Review review) {
        return myPageDAO.update(reviewNumber,review);
    }

    //리뷰 삭제
    @Override
    public int deleteByReviewId(Long reviewNumber) {
        return myPageDAO.deleteByReviewId(reviewNumber);
    }

    //회원 조회

    @Override
    public Optional<Member> findMember(Long memNumber) {
        return myPageDAO.findMember(memNumber);
    }
}