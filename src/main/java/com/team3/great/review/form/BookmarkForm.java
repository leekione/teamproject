package com.team3.great.review.form;

import com.team3.great.Member;
import lombok.Data;

@Data
public class BookmarkForm {
    private Long bookmarkNumber;
    private Long buyerNumber;
    private Long sellerNumber;
    private Member member;
}
