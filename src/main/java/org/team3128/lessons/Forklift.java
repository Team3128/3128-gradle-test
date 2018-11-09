import org.team3128.common.util.units.Length;


class Forklift {
    enum ForkliftState{
        highPos(100*Length.in),
        mediumPos(50*Length.in),
        lowPos(25*Length.in);
        public ForkliftState(){
            this.ForkliftState = ForkliftState.highPos;

        }
    }
    main() {
        ForkliftState fs;
        fs=ForkliftState.lowPos;
    }
}