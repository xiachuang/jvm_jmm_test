package com.xia.jmm.lock;

import lombok.Data;
import lombok.Getter;


public enum CountryEnum {
    ONE(1,"春"),TWO(2,"夏"),Three(3,"秋"),FOUR(4,"冬");
    @Getter
    private Integer idCard;
    @Getter
    private String name;

    CountryEnum(Integer idCard, String name) {
        this.idCard = idCard;
        this.name = name;
    }
    public  static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] countryEnums=CountryEnum.values();
        for(CountryEnum countryEnum:countryEnums){
            if(index==countryEnum.getIdCard()){
                return countryEnum;
            }
        }
        return null;
    }
}
