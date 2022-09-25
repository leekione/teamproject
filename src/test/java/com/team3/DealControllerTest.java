package com.team3;

import com.team3.great.Product;
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
    @Test
    void addForm() {
        Product byProductNum = productDAO.findByProductNum(1l);



        log.info("byProductNum={}",byProductNum);
    }
}