package com.team3;


import com.team3.great.deal.svc.DealSVC;
import com.team3.great.review.dao.Review;
import com.team3.great.review.form.InfoForm;
import com.team3.great.review.form.ReviewAddForm;
import com.team3.great.review.svc.ReviewSVC;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewSVC reviewSVC;
    private final DealSVC dealSVC;

    //등록양식
    @GetMapping("/add")
    public String saveForm(Model model){

        ReviewAddForm reviewAddForm = new ReviewAddForm();

        model.addAttribute("form",reviewAddForm);
        return "member/review_popup";
    }

    //리뷰 등록 처리
    @PostMapping("/add")
    public String save(@ModelAttribute("form") ReviewAddForm reviewAddForm, RedirectAttributes redirectAttributes){
        Review review = new Review();
        BeanUtils.copyProperties(reviewAddForm, review);
        review.setBuyerNumber(2l);
        Review save = reviewSVC.save(review);

        Long buyerNumber = save.getBuyerNumber();

        redirectAttributes.addAttribute("id",buyerNumber);

        return "redirect:/mypage/{id}";

    }

    //조회
//    @GetMapping("/{id}/detail")
//    public String findByBuyId(@PathVariable("id") Long id, Model model) {
//
//        Optional<Review> findedReview = reviewSVC.findByReviewId(id);
//        InfoForm infoForm = new InfoForm();
//        if(!findedReview.isEmpty()) {
//            BeanUtils.copyProperties(findedReview.get(),infoForm);
//
//        }
//
//        model.addAttribute("form",infoForm);
//        return "member/myReview";
//    }

    //목록
    @GetMapping("/all")
    public String findAll(Model model) {
        List<Review> reviews = reviewSVC.findAll();

        List<Review> list = new ArrayList<>();
        reviews.stream().forEach(review -> {
            BeanUtils.copyProperties(review,new InfoForm());
            list.add(review);
        });
        log.info("list={}",list);
        model.addAttribute("list",list);
        return "member/myReview";
    }
}