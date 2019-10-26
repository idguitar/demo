package com.guaranteed.demo.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class RecordController {

   private String message = "hello world";

   @GetMapping("/")
   public String geIndex(Model model){

    return this.message;
   }

}
