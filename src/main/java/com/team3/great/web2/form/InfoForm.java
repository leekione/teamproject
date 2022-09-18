package com.team3.great.web2.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InfoForm {
    private Long reviewNumber;
    private Long buyerNumber;
    private String content;
    private Long grade;
    private LocalDateTime writeDate;

}
