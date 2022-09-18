package com.team3;


import com.team3.great.Review;
import com.team3.great.svc.ReviewSVC;
import com.team3.great.web2.form.InfoForm;
import com.team3.great.web2.form.SaveForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewSVC reviewSVC;

    //등록양식
    @GetMapping("/add")
    public String saveForm(Model model){
        model.addAttribute("form",new SaveForm());
        return "member/review_popup";
    }

    //조회
    @GetMapping("/{id}/detail")
    public String findByBuyId(@PathVariable("id") Long id, Model model) {

        Optional<Review> findedReview = reviewSVC.findByReviewId(id);
        InfoForm infoForm = new InfoForm();
        if(!findedReview.isEmpty()) {
            BeanUtils.copyProperties(findedReview.get(),infoForm);

        }

        model.addAttribute("form",infoForm);
        return "member/myReview";
    }

    //목록
    @GetMapping("/all")
    public String findAll(Model model) {
    List<Review> reviews = reviewSVC.findAll();

        List<Review> list = new ArrayList<>();
        reviews.stream().forEach(review -> {
            BeanUtils.copyProperties(review,new InfoForm());
            list.add(review);
        });
        model.addAttribute("list",list);
        return "member/myReview";
    }
}
