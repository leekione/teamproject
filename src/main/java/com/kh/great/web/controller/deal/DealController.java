package com.kh.great.web.controller.deal;


import com.kh.great.Product;
import com.kh.great.domain.dao.deal.Deal;
import com.kh.great.web.dto.deal.AddForm;
import com.kh.great.web.dto.deal.InfoForm;
import com.kh.great.domain.svc.deal.DealSVC;
import com.kh.great.product.svc.ProductSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/buy")
public class DealController {

    private final DealSVC dealSVC;
    private final ProductSVC productSVC;


    //구매등록 양식
    @GetMapping("/add/{pNumber}")
    public String addForm(@PathVariable("pNumber") Long pNumber,Model model){


        AddForm addForm = new AddForm();
        Product findedProduct = productSVC.findByProductNum(pNumber);
        BeanUtils.copyProperties(findedProduct,addForm);
        addForm.setSellerNumber(findedProduct.getMember().getMemNumber());
        model.addAttribute("form", addForm);
        log.info("addForm={}",addForm);

        return "buy/buy";
    }

    //등록 처리
    @PostMapping("/add/{pNumber}")
    public String add(@PathVariable("pNumber") Long pNumber,
            @ModelAttribute("form") AddForm addForm, RedirectAttributes redirectAttributes){
        Deal deal = new Deal();
        Product findedProduct = productSVC.findByProductNum(pNumber);
        BeanUtils.copyProperties(addForm, deal);
        deal.setSellerNumber(findedProduct.getMember().getMemNumber());
            dealSVC.add(deal);
        BeanUtils.copyProperties(findedProduct.getPNumber(),addForm);
            dealSVC.update(pNumber, deal);

//        dealSVC.update(pNumber, deal);
        Optional<Deal> byOrderNumber = dealSVC.findByOrderNumber(deal.getOrderNumber());
        Deal deal1 = byOrderNumber.get();
        Long orderNumber = deal1.getOrderNumber();

        log.info("addForm={}",addForm);

        redirectAttributes.addAttribute("pNumber",pNumber);
        redirectAttributes.addAttribute("byOrderNumber",orderNumber);

        return "redirect:/buy/add/{pNumber}/end/{byOrderNumber}";
    }

//    등록 완료 조회 양식
    @GetMapping("/add/{pNumber}/end/{orderNumber}")
    public String addEnd(@PathVariable("pNumber") Long pNumber, @PathVariable("orderNumber") Long orderNumber ,  Model model){
        Optional<Deal> byOrderNumber = dealSVC.findByOrderNumber(orderNumber);
        Product byProductNum = productSVC.findByProductNum(pNumber);
        InfoForm infoForm = new InfoForm();


        if(!byOrderNumber.isEmpty()){
            BeanUtils.copyProperties(byOrderNumber.get(),infoForm);
            BeanUtils.copyProperties(byProductNum,infoForm);
        }

        //남은수량 0개 일시
        if (byProductNum.getRemainCount() == 0) {
            dealSVC.updatePstatus(pNumber);

            log.info("byProductNum={}", byProductNum);

        }

        model.addAttribute("form",infoForm);
        log.info("infoForm={}",infoForm);
        return "buy/buy-complete";

    }



}
