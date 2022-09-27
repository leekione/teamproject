package com.team3;

import com.team3.great.Product;
import com.team3.great.deal.dao.DealDAO;
import com.team3.great.deal.svc.DealSVC;
import com.team3.great.product.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DealControllerTest {
    @Autowired
    ProductDAO productDAO;
    DealDAO dealDAO;
    DealSVC dealSVC;
    @Test
    void addForm() {
        Product byProductNum = productDAO.findByProductNum(1l);



        log.info("byProductNum={}",byProductNum);
    }

    @Test
    void delBuy() {
        int i = dealDAO.deleteByOrderNumber(2l);

        log.info("delNum={}",i);
    }


}