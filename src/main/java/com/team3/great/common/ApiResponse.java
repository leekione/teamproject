package com.team3.great.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private Header header;
    private T data;

    @Data
    @AllArgsConstructor
    public static class Header {
        private String rtcd;    //���� �ڵ�  "00"-���� ,"99"-����
        private String rtmsg;   //���� �޼���
    }

    /**
     * api���� �޼��� ����
     * @param rtcd �����ڵ�
     * @param rtmsg ����޼���
     * @param data  ������
     * @return
     * @param <T> ������
     */
    public static <T> ApiResponse<T> createApiResMsg(String rtcd, String rtmsg, T data){
//    ApiResponse<T> response = null;
//    Header header = new Header(rtcd, rtmsg);
//    response = new ApiResponse<>(header, data);
//    return response;
        return new ApiResponse<>(new Header(rtcd, rtmsg), data);
    }

}
