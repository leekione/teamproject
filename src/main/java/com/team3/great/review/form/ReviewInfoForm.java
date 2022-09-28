package com.team3.great.review.form;


import com.team3.great.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewInfoForm {
    private Long reviewNumber;
    private Long buyerNumber;
    private String content;
    private Long grade;
    private LocalDateTime writeDate;
    private Long profileNumber;
    private Member member;

}
