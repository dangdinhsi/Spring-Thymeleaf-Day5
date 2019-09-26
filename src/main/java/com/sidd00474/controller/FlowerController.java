package com.sidd00474.controller;

import com.sidd00474.entity.Flower;
import com.sidd00474.service.FlowerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/flowers")
public class FlowerController {
    @Autowired
    private FlowerService flowerService;
    // list
@GetMapping(value = "/list")
    public String showListFlower(Model model){
    model.addAttribute("list",flowerService.getListByStatus());
    return "list";
}
//tim kiem theo ten.
@GetMapping(value = "/find")
public String findFlowerByName(@RequestParam String name,Model model){
    List<Flower> list = flowerService.getListByName(name);
    model.addAttribute("list",list);
    return "list";
}
    //details flower
    @GetMapping(value = "/detail/{id}")
    public String showDetail(@PathVariable("id") long id,Model model){
        model.addAttribute("flower",flowerService.getDetailFlower(id));
        return "detail-flower";
    }
    //add flower
    //b1. goi den form, khoi tao 1 new flower
    @GetMapping(value = "/add")
    public String createFlower(Model model){
    model.addAttribute("flower",new Flower());
    return "add-flower";
    }
    //b2.kiem tra validate, tra ve list.
    @PostMapping(value = "/add")
    public String addFlower(@Valid Flower flower,BindingResult result){
    if(result.hasErrors()){
        return "add-flower";
    }
    flowerService.addFlower(flower);
    return "redirect:/flowers/list";
    }

    // show thong tin can edit
    @GetMapping(value = "/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id,Model model){
    Flower flower = flowerService.getDetailFlower(id);
    model.addAttribute("flower",flower);
    return "update-flower";
    }
    //update thong tin moi
    @PostMapping(value = "/update/{id}")
    public String updateFlower(@PathVariable("id") long id,@Valid Flower flower,BindingResult result){
    if(result.hasErrors()){
        return "update-flower";
    }
    flowerService.editFlower(id,flower);
    return "redirect:/flowers/list";
    }
    //xoa
    @GetMapping(value = "/delete/{id}")
    public String deleteFlower(@PathVariable("id") long id){
    flowerService.deleteFlower(id);
        return "redirect:/flowers/list";
    }
}
