package com.kh;

import com.kh.great.Product;
import com.kh.great.domain.dao.deal.DealDAO;
import com.kh.great.domain.svc.deal.DealSVC;
import com.kh.great.product.dao.ProductDAO;
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