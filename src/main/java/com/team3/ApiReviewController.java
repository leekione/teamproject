package com.team3;

import com.team3.great.common.ApiResponse;
import com.team3.great.review.dao.Review;
import com.team3.great.review.form.ReviewAddForm;
import com.team3.great.review.svc.ReviewSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ApiReviewController {

    private final ReviewSVC reviewSVC;


    @PostMapping("/add/reviews")
    public ApiResponse<Object> save(@RequestBody ReviewAddForm reviewAddForm, RedirectAttributes redirectAttributes){
        Review review = new Review();
        BeanUtils.copyProperties(reviewAddForm, review);
        review.setBuyerNumber(1l);
//        Review save =
                reviewSVC.save(review);


//        Long buyerNumber = save.getBuyerNumber();

//        redirectAttributes.addAttribute("id",buyerNumber);


        return ApiResponse.createApiResMsg("00","성공",review);

    }
}
