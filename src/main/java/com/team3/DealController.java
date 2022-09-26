package com.team3;


import com.team3.great.Product;
import com.team3.great.deal.dao.Deal;
import com.team3.great.deal.form.AddForm;
import com.team3.great.deal.form.InfoForm;
import com.team3.great.deal.svc.DealSVC;
import com.team3.great.product.svc.ProductSVC;
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

        Product findedProduct = productSVC.findByProductNum(pNumber);

        AddForm addForm = new AddForm();

        BeanUtils.copyProperties(addForm,findedProduct);

        model.addAttribute("form", addForm);


        return "buy/buy-online";
    }

    //등록 처리
    @PostMapping("/add/{pNumber}")
    public String add(@PathVariable("pNumber") Long pNumber,
            @ModelAttribute("form") AddForm addForm, RedirectAttributes redirectAttributes){
        Deal deal = new Deal();
        Product product = new Product();
        BeanUtils.copyProperties(addForm, deal);
        dealSVC.add(deal);
        log.info("deal={}", deal);

        Product findedProduct = productSVC.findByProductNum(pNumber);

        BeanUtils.copyProperties(findedProduct.getPNumber(),addForm);

        dealSVC.update(pNumber, deal);
        Optional<Deal> byOrderNumber = dealSVC.findByOrderNumber(deal.getOrderNumber());
        Deal deal1 = byOrderNumber.get();
        Long orderNumber = deal1.getOrderNumber();


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
        log.info("infoForm={}",infoForm);

        if(!byOrderNumber.isEmpty()){
            BeanUtils.copyProperties(byOrderNumber.get(),infoForm);
            BeanUtils.copyProperties(byProductNum,infoForm);
            log.info("infoForm={}",infoForm);

        }

        model.addAttribute("form",infoForm);
        log.info("infoForm={}",infoForm);
        return "buy/buy-complete";

    }
}