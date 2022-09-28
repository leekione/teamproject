package com.team3;


import com.team3.great.deal.svc.DealSVC;
import com.team3.great.review.dao.Review;
import com.team3.great.review.form.ReviewAddForm;
import com.team3.great.review.form.ReviewInfoForm;
import com.team3.great.review.svc.ReviewSVC;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        review.setBuyerNumber(1l);
        Review save = reviewSVC.save(review);

        Long buyerNumber = save.getBuyerNumber();

        redirectAttributes.addAttribute("id",buyerNumber);



        return "redirect:/reviews/{id}";

    }

    //리뷰 목록
    @GetMapping("/{id}")
    public String myReview(@PathVariable("id") Long memNumber, Model model){
        List<Review> reviews = reviewSVC.findByMemNumber(memNumber);

        List<Review> list = new ArrayList<>();
        reviews.stream().forEach(review->{
            BeanUtils.copyProperties(review, new ReviewInfoForm());
            list.add(review);
        });
        log.info("list={}",list);
        model.addAttribute("list",list);

        return "member/myReview";
    }

    //리뷰 수정 화면
//    @GetMapping("/edit/{reviewNumber}")
//    public String reviewEdit(@PathVariable("reviewNumber") Long reviewNumber, Model model){
////        reviewSVC.
//
//    }
}