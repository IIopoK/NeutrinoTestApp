package com.example.kirill.neutrinotestapp.objects;

import java.io.BufferedInputStream;

/**
 * Created by iiopok on 11.04.2015.
 */
public class PositionAndBIS {

    private int posotion;
    private BufferedInputStream bis;

    public PositionAndBIS(int posotion, BufferedInputStream bis){
        this.bis = bis;
        this.posotion = posotion;
    }

    public int getPosotion(){
        return posotion;
    }

    public BufferedInputStream getBis(){
        return bis;
    }
}
