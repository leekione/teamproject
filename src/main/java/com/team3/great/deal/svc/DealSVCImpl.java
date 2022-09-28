package com.team3.great.deal.svc;

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

    //구매시 상품갯수 감소
    @Override
    public int update(Long pNumber,Deal deal) {
        return dealDAO.update(pNumber,deal);
    }

    //상품조회

    //주문번호로 조회
    @Override
    public Optional<Deal> findByOrderNumber(Long orderNumber) {
        return dealDAO.findByOrderNumber(orderNumber);
    }

    // 구매 취소시 상품갯수 증가
//    @Override
//    public int delUpdate(Long pNumber, Deal deal) {
//        return dealDAO.delUpdate(pNumber, deal);
//    }

    // 구매 취소
    @Override
    public int deleteByOrderNumber(Long orderNumber) {
        Optional<Deal> foundOrder = dealDAO.findByOrderNumber(orderNumber);
        log.info("foundOrder : {} ",foundOrder);
        dealDAO.delUpdate(foundOrder.get().getPNumber(),foundOrder.get());

        int affectedRow = dealDAO.deleteByOrderNumber(orderNumber);
        return affectedRow;
                
    }
}
