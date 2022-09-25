package com.team3;

import com.team3.great.deal.dao.Deal;
import com.team3.great.deal.svc.DealSVC;
import com.team3.great.review.form.InfoForm;
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

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    private final DealSVC dealSVC;

    @GetMapping("/{id}")
    public String myPage(@PathVariable("id") Long memNumber, Model model){
        List<Deal> deals = dealSVC.findByMemberNumber(memNumber);

        List<Deal> list = new ArrayList<>();
        deals.stream().forEach(deal ->{
            BeanUtils.copyProperties(deal, new InfoForm());
            list.add(deal);
        });
        model.addAttribute("list",list);
        return "member/order-history";

    }

}
