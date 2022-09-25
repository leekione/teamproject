package com.team3.great.deal.dao;

import com.team3.great.Product;

import java.util.List;
import java.util.Optional;

public interface DealDAO {

    //구매등록
    Deal add(Deal deal);

    //구매조회
    List<Deal> findByMemberNumber(Long memNumber);

    //주문 번호로 조회
    Optional<Deal> findByOrderNumber(Long orderNumber);

    int update(Long pNumber,Product product);
    //구매 취소
    int deleteByOrderNumber(Long orderNumber);

    //구매 목록


}
