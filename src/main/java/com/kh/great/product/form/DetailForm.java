package com.kh.great.product.form;

import com.kh.great.Member;
import lombok.Data;

@Data
public class DetailForm {
    private Long pNumber;           //상품번호
    //    private Long ownerNumber;       //점주고객번호    OWNER_NUMBER	NUMBER(6,0)
    private String memStoreName;       //가게명
    private String pTitle;          //상품 제목    P_TITLE	VARCHAR2(90 BYTE)
    private String pName;           //상품명    P_NAME	VARCHAR2(60 BYTE)
    //    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private String deadlineTime;    //마감일자    DEADLINE_TIME	DATE
    //    private String pCategory;      //업종카테고리    CATEGORY	VARCHAR2(17 BYTE)
//    private Integer totalCount;     //총수량    TOTAL_COUNT	NUMBER(5,0)
    private Integer remainCount;    //남은 수량    REMAIN_COUNT	NUMBER(5,0)
    private Integer normalPrice;    //정상가    NORMAL_PRICE	NUMBER(8,0)
    private Integer salePrice;      //할인가    SALE_PRICE	NUMBER(8,0)
    private Integer discountRate;   //할인율    DISCOUNT_RATE	NUMBER(2,0)
    private String paymentOption;  //결제방식    PAYMENT_OPTION	VARCHAR2 (32 BYTE)
    private String detailInfo;     //상품설명    DETAIL_INFO	VARCHAR2 (4000 BYTE)
//    private LocalDateTime rDate;    //등록일    R_DATE DATE DEFAULT SYSDATE
//    private LocalDateTime uDate;    //수정일    U_DATE	DATE DEFAULT SYSDATE
//    private Integer pStatus;        //판매상태    P_STATUS	NUMBER(1,0)
    private Member member;


}