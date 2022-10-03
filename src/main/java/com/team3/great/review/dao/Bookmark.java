package com.team3.great.review.dao;

import com.team3.great.Member;
import lombok.Data;

@Data
public class Bookmark {
    private Long bookmarkNumber;
    private Long buyerNumber;
    private Long sellerNumber;
    private Member member;
}
