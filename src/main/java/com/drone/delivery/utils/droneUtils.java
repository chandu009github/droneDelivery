package com.drone.delivery.utils;

import java.util.*;

public class droneUtils {
    /**
    This Method Calculates NPS based on order delivery time
     */
    public int findNPS(List<Integer> diffL) {

        List<Integer> promo = new ArrayList<Integer>();
        List<Integer> detract = new ArrayList<Integer>();
        int sumpromo = 0;
        int sumdetract = 0;
        for(int i : diffL){
            if(i<=30){
                promo.add(10);
                sumpromo = sumpromo+10;
            }else if(i>30 && i<=90){
                promo.add(9);
                sumpromo = sumpromo+9;
            }else if(i>90 && i<=150){
                promo.add(8);
                sumpromo = sumpromo+8;
            }else if(i>150 && i<=210){
                promo.add(7);
                sumpromo = sumpromo+7;
            }else if(i>210 && i<=270){
                detract.add(6);
                sumdetract=sumdetract+6;
            }else if(i>270 && i<=330){
                detract.add(5);
                sumdetract=sumdetract+5;
            }else if(i>330 && i<=390){
                detract.add(4);
                sumdetract=sumdetract+4;
            }else if(i>390 && i<=450){
                detract.add(3);
                sumdetract=sumdetract+3;
            }else if(i>450 && i<=510){
                detract.add(2);
                sumdetract=sumdetract+2;
            }else if(i>510 && i<=570){
                detract.add(1);
                sumdetract=sumdetract+1;
            }else{
                detract.add(0);
            }
        }
        float detNPS;
        float promoNPS;
        if(detract.size()>0){
            detNPS =  (float) sumdetract/(detract.size()*10);
        }else{
            detNPS =0;
        }
        if(promo.size()>0){
            promoNPS = (float) sumpromo/(promo.size()*10);
        }else{
            promoNPS =0;
        }
        float NPS = promoNPS-detNPS;
        int fNPS = (int)(NPS*100);
        return fNPS;
    }


    /**
     This Method find out the distance of customer location from warehouse based on given grid directions
     */
    public Double findDistance(String directions){
        Double res =0.0;
        directions=  directions.substring(1,directions.length());
        directions=directions.replaceAll("E"," ").replaceAll("W"," ").replaceAll("N","  ").replaceAll("S"," ");
        String[] dirL = directions.split(" ");
        Double a = Double.parseDouble(dirL[0]);
        Double b = Double.parseDouble(dirL[1]);
        a= a*a;
        b=b*b;
        res =  Math.sqrt(a+b);
        return res;
    }

}
