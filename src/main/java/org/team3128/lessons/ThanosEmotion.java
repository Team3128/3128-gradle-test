package org.team3128.lessons;
import java.util.Random;

public class ThanosEmotion {
    enum emotions{
        happy(5),
        sad(3),
        depressed(1),
        excited(8),
        elated(7);
        private final int emotionSeverity;      // Private variable
   
        emotions(int emotionSeverity) {     // Constructor
           this.emotionSeverity = emotionSeverity;
        }
        
        int getSeverity() {              // Getter
           return emotionSeverity;
        }
    }
    public static void main(String[] args) {
        Random random=new Random();
        int randomNum=random.nextInt(5);
        emotions thanosEmotion=emotions.happy;
        if(randomNum==0){
            thanosEmotion=emotions.depressed;
        }
        else if(randomNum==1){
            thanosEmotion=emotions.sad;
        }
        else if(randomNum==2){
            thanosEmotion=emotions.happy;
        }
        else if(randomNum==3){
            thanosEmotion=emotions.elated;
        }
        else if(randomNum==4){
            thanosEmotion=emotions.excited;
        }
        else{
            System.out.println("Something went wrong.");
        }
        System.out.println("Thanos is "+thanosEmotion+".");
        System.out.println("The severity of this emotion is: "+thanosEmotion.getSeverity()+".");
    }
}