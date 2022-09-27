package com.team3.great.review.form;

import com.team3.great.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewAddForm {
    private Long reviewNumber;            //    REVIEW_NUMBER	NUMBER(1,0)	No		1
    private Long buyerNumber;            //    BUYER_NUMBER	NUMBER(6,0)	Yes		2
    private Long sellerNumber;
    private String content;
    private LocalDateTime writeDate;
    private Long grade;
    private Long profileNumber;
    private Member member;
}
