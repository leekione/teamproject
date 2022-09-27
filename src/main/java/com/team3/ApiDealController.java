package com.team3;


import com.team3.great.common.ApiResponse;
import com.team3.great.deal.dao.Deal;
import com.team3.great.deal.form.EditReq;
import com.team3.great.deal.svc.DealSVC;
import com.team3.great.product.svc.ProductSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/buy")
public class ApiDealController {

    private final DealSVC dealSVC;
    private final ProductSVC productSVC;


    //구매 취소
    @DeleteMapping("/del/{orderNumber}")
    public ApiResponse<Deal> delBuy(@PathVariable("orderNumber") Long orderNumber){

        dealSVC.deleteByOrderNumber(orderNumber);
        return ApiResponse.createApiResMsg("00","성공",null);
    }

    @PatchMapping("/back/{pNumber}")
    public ApiResponse<Object> backBuy(@PathVariable("pNumber") Long pNumber, @RequestBody EditReq editReq){
        Deal deal = new Deal();
        BeanUtils.copyProperties(editReq,deal);

        dealSVC.delUpdate(pNumber,deal);

        return ApiResponse.createApiResMsg("00","성공",productSVC.findByProductNum(pNumber).getRemainCount());
    }

}
