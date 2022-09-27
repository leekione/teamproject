package com.team3.great.deal.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class DealDAOImplTest {

    @Autowired
    DealDAO dealDAO;

    @Test
    void add() {
        Deal deal =  new Deal();
        deal.setPNumber(0001l);
        deal.setPCount(1l);
        deal.setVisittime("2022-09-01T14:50");
        deal.setPrice(4000l);
        deal.setBuyType(1l);

        Deal savedDeal = dealDAO.add(deal);
        log.info("savedDeal={}",savedDeal);
    }

    @Test
    void findByMemberNumber() {

        List<Deal> searchDeal = dealDAO.findByMemberNumber(1l);
        log.info("search={}",searchDeal);

    }


    @Test
    void deleteByOrderNumber() {
        int i = dealDAO.deleteByOrderNumber(22L);

        log.info("i={}",i);
    }
}