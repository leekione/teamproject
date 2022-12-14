package com.kh.great.product.dao;

import com.kh.great.Member;
import com.kh.great.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{
    private final JdbcTemplate jt;

    //상품번호 생성
    @Override
    public Long generatePnum() {
        String sql = "select product_p_number_seq.nextval from dual";
        Long productNum = jt.queryForObject(sql, Long.class);
        return productNum;
    }

    //상품등록
    @Override
    public Product save(Product product) {
        String sql = "insert into product_info(p_number, p_title, p_name, DEADLINE_TIME, CATEGORY, TOTAL_COUNT, REMAIN_COUNT ,NORMAL_PRICE, SALE_PRICE, DISCOUNT_RATE, PAYMENT_OPTION, DETAIL_INFO ) values(?, ?, ?, TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI'), ?, ?, ?, ?, ?, ?, ?, ?) ";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"p_number"});
                pstmt.setLong(1, product.getPNumber());
                pstmt.setString(2, product.getPTitle());
                pstmt.setString(3, product.getPName());
                pstmt.setString(4, product.getDeadlineTime());
                log.info("product.getDeadline_time()=>{}", product.getDeadlineTime());
                pstmt.setString(5, product.getPCategory());
                pstmt.setInt(6, product.getTotalCount());
                pstmt.setInt(7, product.getTotalCount());
                pstmt.setInt(8, product.getNormalPrice());
                pstmt.setInt(9, product.getSalePrice());
                pstmt.setInt(10, (product.getNormalPrice()-product.getSalePrice())*100/product.getNormalPrice());
                pstmt.setString(11, product.getPaymentOption());
                pstmt.setString(12, product.getDetailInfo());

                return pstmt;
            }
        }, keyHolder);

        Long pNum = Long.valueOf(keyHolder.getKeys().get("p_number").toString());
        product.setPNumber(pNum);
        return product;
    }
    //상품조회
    @Override
    public Product findByProductNum(Long pNum) {
        StringBuffer sql = new StringBuffer();
        sql.append("select  *  ");
        sql.append("from product_info P, member M ");
        sql.append("where p.owner_number = m.mem_number and p.p_number= ? ");

        Product product = null;

        try {
            product= jt.queryForObject(sql.toString(),new RowMapper<Product>(){
                @Override
                public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = (new BeanPropertyRowMapper<>(Product.class)).mapRow(rs, rowNum);
                    Member member = (new BeanPropertyRowMapper<>(Member.class)).mapRow(rs,rowNum);
                    product.setMember(member);
                    return product;
                }
            }, pNum);
        }catch (DataAccessException e) {
            log.info("조회할 상품이 없습니다. 상품번호={}", pNum);
        }
        log.info("product={}", product);
        return product;
    }
    //상품수정
    @Override
    public int update(Long pNum, Product product) {
        int result = 0;
        StringBuffer sql = new StringBuffer();

        sql.append("update product_info ");
        sql.append("SET p_title = ?, P_NAME=?, DEADLINE_TIME = TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI'), CATEGORY=?, REMAIN_COUNT=?, NORMAL_PRICE = ?, SALE_PRICE = ?, DISCOUNT_RATE=?, PAYMENT_OPTION=?, detail_info=? ");
        sql.append("WHERE p_number = ? ");

        result=jt.update(sql.toString(), product.getPTitle(), product.getPName(), product.getDeadlineTime(), product.getPCategory(), product.getRemainCount(), product.getNormalPrice(), product.getSalePrice(), (product.getNormalPrice()-product.getSalePrice())*100/product.getNormalPrice(), product.getPaymentOption(), product.getDetailInfo(), pNum );

        return result;
    }

    //상품목록
    @Override
    public List<Product> findAll() {
        StringBuffer sql = new StringBuffer();

        sql.append("select p_number, p_name, DISCOUNT_RATE, SALE_PRICE, NORMAL_PRICE,DEADLINE_TIME ");
        sql.append(" from product_info");
        sql.append(" order by P_NUMBER desc");

        List<Product> result = jt.query(sql.toString(), new BeanPropertyRowMapper<>(Product.class));

        return result;
    }

    //상품 삭제
    @Override
    public int deleteByProductNum(Long pNum) {
        String sql = "delete from product_info where P_NUMBER=? ";

        return jt.update(sql, pNum);
    }

    //상품 관리
    public List<Product> pManage(Long ownerNumber) {
        StringBuffer sql = new StringBuffer();

        sql.append("select p.P_NUMBER, p.P_STATUS, p.P_NAME, p.SALE_PRICE, p.REMAIN_COUNT, p.TOTAL_COUNT ");
        sql.append("from product_info P, member M ");
        sql.append("where p.owner_number = m.mem_number and m.mem_type='owner' and p.owner_number=10 ");

        List<Product> result =null;
        try {
            result= jt.query(sql.toString(),new RowMapper<Product>(){
                @Override
                public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = (new BeanPropertyRowMapper<>(Product.class)).mapRow(rs, rowNum);
                    Member member = (new BeanPropertyRowMapper<>(Member.class)).mapRow(rs,rowNum);
                    product.setMember(member);
                    return product;
                }
            });
        } catch (DataAccessException e) {
            log.info("조회할 회원이 없습니다. 회원번호={}", ownerNumber);
        }

        return result;
    }
}
