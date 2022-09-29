package com.team3;

import com.team3.great.Member;
import com.team3.great.common.ApiResponse;
import com.team3.great.deal.dao.Deal;
import com.team3.great.deal.svc.DealSVC;
import com.team3.great.product.svc.ProductSVC;
import com.team3.great.review.dao.Review;
import com.team3.great.review.form.ProfileForm;
import com.team3.great.review.form.ReviewAddForm;
import com.team3.great.review.form.ReviewInfoForm;
import com.team3.great.review.form.ReviewUpdateForm;
import com.team3.great.review.svc.MyPageSVC;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final DealSVC dealSVC;
    private final MyPageSVC myPageSVC;
    private final ProductSVC productSVC;

    //주문 내역
    @GetMapping("/{id}")
    public String myPage(@PathVariable("id") Long memNumber, Model model){
        List<Deal> deals = dealSVC.findByMemberNumber(memNumber);

        List<Deal> list = new ArrayList<>();
        deals.stream().forEach(deal ->{
            BeanUtils.copyProperties(deal, new ReviewInfoForm());
            list.add(deal);
        });
        log.info("list={}",list);
        model.addAttribute("list",list);

        return "member/order-history";
    }

    //리뷰 등록 양식
    @GetMapping("/review/add")
    public String saveForm(Model model){

        ReviewAddForm reviewAddForm = new ReviewAddForm();

        reviewAddForm.setBuyerNumber(1l);
        log.info("reviewAddForm={}",reviewAddForm);
        model.addAttribute("form",reviewAddForm);
        return "member/reviewAdd";
    }

    //리뷰 등록 처리
    @PostMapping("/review/add")
    public String save(@ModelAttribute("form") ReviewAddForm reviewAddForm, RedirectAttributes redirectAttributes){
        Review review = new Review();
        BeanUtils.copyProperties(reviewAddForm, review);
        review.setBuyerNumber(1l);
        Review save = myPageSVC.save(review);
        log.info("reviewAddForm={}",reviewAddForm);

        Long buyerNumber = save.getBuyerNumber();

        redirectAttributes.addAttribute("id",buyerNumber);

        return "redirect:/mypage/review/{id}";

    }

    //리뷰 목록
    @GetMapping("/review/{id}")
    public String myReview(@PathVariable("id") Long memNumber, Model model){
        List<Review> reviews = myPageSVC.findByMemNumber(memNumber);

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
    @GetMapping("/review/edit/{reviewNumber}")
    public String reviewEditForm(@PathVariable("reviewNumber") Long reviewNumber, Model model){
        Optional<Review> foundReview = myPageSVC.findByReviewNumber(reviewNumber);
        ReviewUpdateForm reviewUpdateForm = new ReviewUpdateForm();
        BeanUtils.copyProperties(foundReview.get(),reviewUpdateForm);
        log.info("foundReview={}",foundReview);


        model.addAttribute("form",reviewUpdateForm);

        return "member/reviewEdit";
    }

    //리뷰 수정 처리
    @PostMapping("/review/edit/{reviewNumber}")
    public String reviewEdit(@PathVariable("reviewNumber") Long reviewNumber,
                             @ModelAttribute("form") ReviewUpdateForm reviewUpdateForm,
                             RedirectAttributes redirectAttributes){
//        log.info("reviewUpdateForm={}",reviewUpdateForm);
        Review review = new Review();
        BeanUtils.copyProperties(reviewUpdateForm,review);
        //로그인 세션 필요
        reviewUpdateForm.setBuyerNumber(1l);
        review.setBuyerNumber(reviewUpdateForm.getBuyerNumber());

//        log.info("review={}",review);

        Optional<Review> foundReview = myPageSVC.findByReviewNumber(reviewNumber);
//        BeanUtils.copyProperties(foundReview,review);
        Review review1 = foundReview.get();
        Long buyerNumber = review1.getBuyerNumber();
//        log.info("review={}",review);

        myPageSVC.update(reviewNumber, review);

        redirectAttributes.addAttribute("id",buyerNumber);


        return "redirect:/mypage/review/{id}";

    }

    //리뷰 삭제
    @ResponseBody
    @DeleteMapping("/review/del/{reviewNumber}")
    public ApiResponse<Review> reviewDel(@PathVariable("reviewNumber") Long reviewNumber){
        myPageSVC.deleteByReviewId(reviewNumber);


        return ApiResponse.createApiResMsg("00","삭제성공",null);
    }

    // 프로필 화면
    @GetMapping("/profile/{memNumber}")
    public String profileForm(@PathVariable("memNumber") Long memNumber, Model model){

        ProfileForm profileForm = new ProfileForm();

        Optional<Member> member = myPageSVC.findMember(memNumber);
        Member member1 = member.get();
        BeanUtils.copyProperties(member1,profileForm);

        model.addAttribute("form",profileForm);



        return "member/profile";

    }

}
