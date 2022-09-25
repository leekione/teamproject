package com.team3.great.deal.svc;

import com.team3.great.Product;
import com.team3.great.deal.dao.Deal;
import com.team3.great.deal.dao.DealDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class DealSVCImpl implements DealSVC{

    private final DealDAO dealDAO;
    // 구매등록
    @Override
    public Deal add(Deal deal) {
        return dealDAO.add(deal);
    }
    // 구매 조회
    @Override
    public List<Deal> findByMemberNumber(Long memNumber) {
        return dealDAO.findByMemberNumber(memNumber);
    }

    @Override
    public int update(Long pNumber,Product product) {
        return dealDAO.update(pNumber,product);
    }

    //상품조회


    @Override
    public Optional<Deal> findByOrderNumber(Long orderNumber) {
        return dealDAO.findByOrderNumber(orderNumber);
    }

    // 구매 삭제
    @Override
    public int deleteByOrderNumber(Long orderNumber) {
        return 0;
    }
}
