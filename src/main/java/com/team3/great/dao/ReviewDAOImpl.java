package com.team3.great.dao;


import com.team3.great.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewDAOImpl implements ReviewDAO{

    private final JdbcTemplate jt;

    //등록
    @Override
    public Long save(Review review) {
        StringBuffer sql = new StringBuffer();

        sql.append("insert into review(review_number,buyer_number,seller_number,content,grade,profile_number) ");
        sql.append(" values(review_review_number_seq.nextval,1,1,?,?,1) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(),new String[]{"review_number"});
                pstmt.setString(1,review.getContent());
                pstmt.setLong(2,review.getGrade());
                return pstmt;
            }
        },keyHolder);


        return Long.valueOf(keyHolder.getKeys().get("review_number").toString());
    }

    //목록
    @Override
    public List<Review> findAll() {
        StringBuffer sql = new StringBuffer();
        sql.append("select mem_name, content, write_date,grade,buyer_number ");
        sql.append(" from review , member ");
        sql.append(" where buyer_number = mem_number ");
        sql.append(" and buyer_number = 1 ");

        List<Review> reviews = jt.query(sql.toString(),new BeanPropertyRowMapper<>(Review.class));

        return reviews;
    }

    //조회
    @Override
    public Optional<Review> findByReviewId(Long reviewNumber) {


        StringBuffer sql = new StringBuffer();

        sql.append("select mem_name, content, write_date,grade ");
        sql.append(" from review , member  ");
        sql.append(" where buyer_number = mem_number ");
        sql.append("  and buyer_number = 1 ");


        try {

            Review review = jt.queryForObject(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(Review.class),
                    reviewNumber);
            return Optional.of(review);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();

            return Optional.empty();
        }
    }

    //수정
    @Override
    public int update(Long reviewNumber, Review review) {

        StringBuffer sql =new StringBuffer();
        sql.append("update review ");
        sql.append(" content = ?, ");
        sql.append(" grade  = ? ");
        sql.append(" where review_number= ? ");
        sql.append(" and buyer_number= 1 ");

        int affectedRow  = jt.update(sql.toString(),
                review.getContent(),review.getGrade(),reviewNumber);
        return affectedRow;
    }

    //삭제
    @Override
    public int deleteByReviewId(Long reviewNumber) {

        String sql = "delete from product where review_number= ? and buyer_number =1 ";

        int affectedRow= jt.update(sql.toString(),reviewNumber);
        return affectedRow;
    }
}
