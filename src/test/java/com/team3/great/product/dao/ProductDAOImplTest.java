package com.team3.great.product.dao;

import com.team3.great.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

    @Autowired
    ProductDAO productDAO;

    @Test
    void findByProductNum() {
        Product byProductNum = productDAO.findByProductNum(1l);
        log.info("byProductNum={}",byProductNum);
    }
}