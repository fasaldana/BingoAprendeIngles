package com.example.bingogameeng;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class TableContent {

    @GetMapping("/")
    public int[] randomNumGen(){
        int[] lista = new int[24];
        int i = 0;
        while (i < 24){
            int random_num = (int) (Math.random() * (24 - 1) + 1);
            lista[i] = random_num;
            i++;
        }
        return lista;
    }
}
