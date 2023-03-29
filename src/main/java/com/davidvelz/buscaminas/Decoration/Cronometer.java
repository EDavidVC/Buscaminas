package com.davidvelz.buscaminas.Decoration;

import com.davidvelz.buscaminas.Decoration.numberFormat;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Cronometer extends Thread{
    private boolean running;
    private int nuMin;
    private int nuSeg;
    private int nuHora;
    private static Text cronometerTextField;

    public Cronometer( Text cronometerTextField){
        this.running = false;
        this.cronometerTextField = cronometerTextField;
        int nuMin = 0;
        int nuSeg = 0;
        int nuHora = 0;

    }
    public void run(){
        try {//si ocurre un error al dormir el proceso(sleep(999))
            while (this.running){ //inicio del for infinito
                if(nuSeg!=59) {//si no es el ultimo segundo
                    nuSeg++; //incremento el numero de segundos
                }else{
                    if(nuMin!=59){//si no es el ultimo minuto
                        nuSeg=0;//pongo en cero los segundos
                        nuMin++;//incremento el numero de minutos
                    }else{//incremento el numero de horas
                        nuHora++;
                        nuMin=0;//pongo en cero los minutos
                        nuSeg=0;//pongo en cero los segundos
                    }
                }
                String textHour = new numberFormat(1, nuHora).getFormatedNumber();
                String textMin = new numberFormat(1, nuMin).getFormatedNumber();
                String textSecond = new numberFormat(1, nuSeg).getFormatedNumber();

                this.cronometerTextField.setText(textHour + ":" + textMin+":"+textSecond);

                sleep(999);//Duermo el hilo durante 999 milisegundos(casi un segundo, quintandole el tiempo de proceso)
            }//Fin del for infinito
        } catch (Exception ex) {
            System.out.println(ex);//Imprima el error
        }
    }
    public void startCronometer(){
        running = true;
        this.start();
    }
    public void stopCronometer(){
        running = false;
    }
    public void resetCronometer(){
        this.stopCronometer();
        int nuMin = 0;
        int nuSeg = 0;
        int nuHora = 0;
        this.startCronometer();
    }
}
