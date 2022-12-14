package com.kh.great.domain.dao.mypage;


import com.kh.great.Member;
import com.kh.great.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyPageDAOImpl implements MyPageDAO {

    private final JdbcTemplate jt;

    //리뷰 등록
    @Override
    public Review save(Review review) {
        StringBuffer sql = new StringBuffer();

        sql.append("insert into review(review_number,buyer_number,seller_number,content,grade) ");
        sql.append(" values(review_review_number_seq.nextval,?,5,?,?) ");

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

    //리뷰 조회 - 회원번호(내리뷰에서 보이는 리뷰)
    @Override
    public List<Review> findByMemNumber(Long memNumber) {

        StringBuffer sql = new StringBuffer();


        sql.append("select * ");
        sql.append("      from (select * ");
        sql.append("              from product_info p, member m ");
        sql.append("             where p.owner_number = m.mem_number) t1, review r ");
        sql.append("where r.seller_number = t1.mem_number ");
        sql.append("and r.buyer_number = ? ");

        List<Review> reviews = null;

        try {
            reviews = jt.query(sql.toString(), new RowMapper<Review>() {
                @Override
                public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Review review = (new BeanPropertyRowMapper<>(Review.class)).mapRow(rs, rowNum);
                    Product product =(new BeanPropertyRowMapper<>(Product.class)).mapRow(rs,rowNum);
                    Member member = (new BeanPropertyRowMapper<>(Member.class)).mapRow(rs, rowNum);
                    member.setProduct(product);
                    review.setMember(member);
                    return review;
                }
            }, memNumber);
        } catch (DataAccessException e) {
            log.info("회원번호가 없습니다");
        }

        return reviews;

    }

    //리뷰 조회 - 판매자 프로필에서 보이는 리뷰

    @Override
    public List<Review> findByBuyerNumber(Long memNumber) {

        StringBuffer sql = new StringBuffer();

        sql.append("select * ");
        sql.append("from review r , member m,  product_info p ");
        sql.append("where r.buyer_number = m.mem_number ");
        sql.append("and r.seller_number = p.owner_number ");
        sql.append("and r.seller_number = ? ");

        List<Review> reviews = null;

        try {
            reviews = jt.query(sql.toString(), new RowMapper<Review>() {
                @Override
                public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Review review = (new BeanPropertyRowMapper<>(Review.class)).mapRow(rs, rowNum);
                    Product product =(new BeanPropertyRowMapper<>(Product.class)).mapRow(rs,rowNum);
                    Member member = (new BeanPropertyRowMapper<>(Member.class)).mapRow(rs, rowNum);
                    member.setProduct(product);
                    review.setMember(member);
                    return review;
                }
            }, memNumber);
        } catch (DataAccessException e) {
            log.info("회원번호가 없습니다");
        }

        return reviews;

    }



    //리뷰조회 - 리뷰번호
    @Override
    public Optional<Review> findByReviewNumber(Long reviewNumber) {
        StringBuffer sql = new StringBuffer();

        sql.append("select * ");
        sql.append("  from review r, member m ");
        sql.append(" where r.buyer_number = m.mem_number ");
        sql.append("   and r.review_number = ? ");

        try{
            Review review = jt.queryForObject(sql.toString(), new RowMapper<Review>() {
                @Override
                public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Review review = (new BeanPropertyRowMapper<>(Review.class)).mapRow(rs, rowNum);
                    Member member = (new BeanPropertyRowMapper<>(Member.class)).mapRow(rs, rowNum);
                    review.setMember(member);

                    return review;
                }
            },reviewNumber);
                return Optional.of(review);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // 리뷰 수정
    @Override
    public int update(Long reviewNumber, Review review) {

        StringBuffer sql =new StringBuffer();
        sql.append("update review ");
        sql.append("   set content = ?, ");
        sql.append("       grade  = ? ");
        sql.append(" where review_number= ? ");
        sql.append("   and buyer_number= ? ");

        int affectedRow  = jt.update(sql.toString(),
                review.getContent(),review.getGrade(),review.getReviewNumber(),review.getBuyerNumber());
        return affectedRow;
    }

    // 리뷰 삭제
    @Override
    public int deleteByReviewId(Long reviewNumber) {

        String sql = "delete from review where review_number = ? ";

        int affectedRow= jt.update(sql.toString(),reviewNumber);
        return affectedRow;
    }

    //회원 조회
    @Override
    public Optional<Member> findMember(Long memNumber) {
        String sql = "select * from member where mem_number = ? ";

        try {
          Member member = jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Member.class),memNumber);
          return Optional.of(member);
        }catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    //즐겨찾기 추가
    @Override
    public Bookmark addBookmark(Bookmark bookmark) {
        StringBuffer sql = new StringBuffer();
        sql.append(" insert into bookmark (bookmark_number, buyer_number, seller_number) ");
        sql.append(" values(bookmark_bookmark_number_seq.nextval, ?, ? ) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jt.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql.toString(),new String[]{"bookmark_number"});
                pstmt.setLong(1,bookmark.getBuyerNumber());
                pstmt.setLong(2,bookmark.getSellerNumber());

                return pstmt;
            }
        },keyHolder);

        Long bookmark_number = Long.valueOf(keyHolder.getKeys().get("bookmark_number").toString());

        bookmark.setBookmarkNumber(bookmark_number);

        return bookmark;
    }

    //즐겨찾기 회원 조회
    @Override
    public List<Bookmark> findBookmark(Long memNumber) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from bookmark b, member m ");
        sql.append(" where b.seller_number = m.mem_number and b.buyer_number = ? ");

        List<Bookmark> bookmarks = null;
        try{
            bookmarks = jt.query(sql.toString(), new RowMapper<Bookmark>() {
                @Override
                public Bookmark mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Bookmark bookmark = (new BeanPropertyRowMapper<>(Bookmark.class)).mapRow(rs,rowNum);
                    Member member = (new BeanPropertyRowMapper<>(Member.class)).mapRow(rs,rowNum);

                    bookmark.setMember(member);
                    return bookmark;
                }
            },memNumber);

        }catch (DataAccessException e) {
            log.info("찾을수 없습니다");
        }
    return  bookmarks;

    }

    //즐겨찾기 조회
    @Override
    public Optional<Bookmark> findBookmarkNumber(Long bookmarkNumber) {

        String sql = " select * from bookmark where bookmark_number = ? ";

        try{
            Bookmark bookmark = jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Bookmark.class),bookmarkNumber);
                return Optional.of(bookmark);
            }catch(EmptyResultDataAccessException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    //즐겨찾기 삭제 - 프로필에서
    @Override
    public int delBookmark(Long memNumber) {

        String sql = "delete from bookmark where seller_number = ? ";

        int affectedRow = jt.update(sql.toString(),memNumber);
        return affectedRow;
    }


    //즐겨찾기 삭제 - 내 즐겨찾기에서

    @Override
    public int delBookmarkInMyPage(Long bookmarkNumber) {
        String sql = "delete from bookmark where bookmark_number = ? ";

        int affectedRow = jt.update(sql.toString(),bookmarkNumber);
        return affectedRow;
    }
}