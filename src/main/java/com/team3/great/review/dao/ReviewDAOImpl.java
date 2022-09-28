package com.team3.great.review.dao;


import com.team3.great.Member;
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
public class ReviewDAOImpl implements ReviewDAO {

    private final JdbcTemplate jt;

    //등록
    @Override
    public Review save(Review review) {
        StringBuffer sql = new StringBuffer();

        sql.append("insert into review(review_number,buyer_number,seller_number,content,grade,profile_number) ");
        sql.append(" values(review_review_number_seq.nextval,?,3,?,?,1) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(),new String[]{"review_number"});
                pstmt.setLong(1,review.getBuyerNumber());
                pstmt.setString(2,review.getContent());
                pstmt.setLong(3,review.getGrade());
                return pstmt;
            }
        },keyHolder);

        Long review_number = Long.valueOf(keyHolder.getKeys().get("review_number").toString());
        review.setReviewNumber(review_number);

        return review;
    }

    //목록
    @Override
    public List<Review> findAll() {
        StringBuffer sql = new StringBuffer();
        sql.append("select mem_name, content,write_date,grade,buyer_number ");
        sql.append(" from review , member ");
        sql.append(" where buyer_number = mem_number ");
        sql.append(" and buyer_number = 2 ");

//        List<Review> reviews = jt.query(sql.toString(),new BeanPropertyRowMapper<>(Review.class));
//        List<Review>  reviews = jt.query(sql.toString(), new RowMapper<Review>() {
//            @Override
//            public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Review review = new Review();
//                review.getMember().setMemName(rs.getString("mem_name"));
//                review.setContent(rs.getString("content"));
//                review.setWriteDate(rs.getTimestamp("write_date").toLocalDateTime());
//                review.setGrade(rs.getLong("grade"));
//                review.setBuyerNumber(rs.getLong("buyer_number"));
//                return review;
//            }
//        });
        List<Review>  reviews = jt.query(sql.toString(), new RowMapper<Review>() {
            @Override
            public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = (new BeanPropertyRowMapper<>(Member.class)).mapRow(rs,rowNum);
                Review review = (new BeanPropertyRowMapper<>(Review.class)).mapRow(rs,rowNum);
                review.setMember(member);
                return review;
            }
        });
        return reviews;
    }

    //조회
    @Override
    public List<Review> findByMemNumber(Long memNumber) {


        StringBuffer sql = new StringBuffer();

        sql.append("select * ");
        sql.append(" from review r , member m  ");
        sql.append(" where r.buyer_number = m.mem_number ");
        sql.append("  and r.buyer_number = ? ");

        List<Review> reviews = null;

        try {
            reviews = jt.query(sql.toString(), new RowMapper<Review>() {
                @Override
                public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Review review = (new BeanPropertyRowMapper<>(Review.class)).mapRow(rs, rowNum);
                    Member member = (new BeanPropertyRowMapper<>(Member.class)).mapRow(rs, rowNum);

                    review.setMember(member);
                    return review;
                }
            }, memNumber);
        } catch (DataAccessException e) {
            log.info("회원번호가 없습니다");
        }

        return reviews;


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