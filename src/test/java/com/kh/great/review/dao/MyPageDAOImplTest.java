package com.kh.great.review.dao;

import com.kh.great.domain.dao.mypage.Bookmark;
import com.kh.great.domain.dao.mypage.MyPageDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class MyPageDAOImplTest {

    @Autowired
    private MyPageDAO myPageDAO;

    @Test
    void addBookmark() {
        Bookmark bookmark = new Bookmark();

        myPageDAO.addBookmark(bookmark);
    }

    @Test
    void findBookmark() {

        List<Bookmark> bookmarks = myPageDAO.findBookmark(1l);
        bookmarks.stream().forEach(bookmark -> {
            log.info("bookmarks={}",bookmarks);
        });
    }
}