package com.team3;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/buy")
public class BuyController {

    @GetMapping
    public String saveForm(){

        return "buy/buy-online";
    }
}
