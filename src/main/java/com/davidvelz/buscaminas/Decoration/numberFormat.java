package com.davidvelz.buscaminas.Decoration;

public class numberFormat {
    private int zeroCant;
    private int numbervalue;
    private int length;

    public numberFormat(int zeroCant, int numbervalue){
        this.numbervalue = numbervalue;
        this.zeroCant = zeroCant;
    }
    public numberFormat(){}
    public String getFormatedNumber(){
        String tempFormated = "";
        for (int i = 0; i < zeroCant; i++){
            if ((tempFormated+numbervalue).length() <= zeroCant){
                tempFormated = tempFormated + 0;
            }

        }
        String formated = tempFormated + numbervalue;
        return formated;
    }
    public static int reformatNumber(String numberString){
        int tempNumber = Integer.parseInt(numberString);
        return tempNumber;
    }
}
