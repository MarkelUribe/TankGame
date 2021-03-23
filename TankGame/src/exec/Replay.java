package exec;

import model.*;

public class Replay {
    public static void main(String[] args) {
        TicState tic = PartidaGorde.ticIrakurri("1_proba.dat", 1);
        
        System.out.println(tic.getTimer());
        
        
    }
}
