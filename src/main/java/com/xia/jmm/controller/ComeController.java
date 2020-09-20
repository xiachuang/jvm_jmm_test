package com.xia.jmm.controller;

import com.xia.jmm.annotation.ResourceCome;
import com.xia.jmm.refect.Come;

public class ComeController {
    @ResourceCome
    private Come come;
    public Come getCome(){
        return come;
    }
}
