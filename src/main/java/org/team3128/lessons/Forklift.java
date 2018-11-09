class Forklift {
    enum ForkliftState{
        highPos(100*Length.in),
        mediumPos(50*Length.in),
        lowPos(25*Length.in);
        public double height;
        private ForkliftState(){
            this.height= 30;
        
        }
    }
    public static void main(String args[]){
        ForkliftState fs;
        fs=ForkliftState.lowPos;
    }
}