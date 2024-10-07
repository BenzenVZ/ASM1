package com.example.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice //khai báo xử lý ngoại lệ
public class GlobalException {

    //Lỗi chung
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Model model, Exception e){
        //Tiêu đề
        model.addAttribute("title","Error 500");
        //Thông tin lỗi
        model.addAttribute("msg", "INTERNAL_SERVER_ERROR");
        return "error";
    }

    //Không tìm thấy đường dẫn
    @ExceptionHandler(value = NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionDataNotFoundException(Model model, NoResourceFoundException e){
        //Tiêu đề
        model.addAttribute("title","Error 404");
        //Thông tin lỗi
        model.addAttribute("msg", "NOT_FOUND");
        return "error";
    }

    //Không tìm thấy dữ liệu
    @ExceptionHandler(value = DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exceptionDataNotFoundException(Model model, DataNotFoundException e){
        //Tiêu đề
        model.addAttribute("title","Error 404");
        //Thông tin lỗi
        model.addAttribute("msg", e.getMessage());
        return "error";
    }
}
