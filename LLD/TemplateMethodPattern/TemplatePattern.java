abstract class Game {
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }
    
    protected abstract void initialize();
    protected abstract void startPlay();
    protected abstract void endPlay();
}

class Cricket extends Game {
    
    @Override
    protected void initialize() {
        System.out.println("Cricket Game Initialized! Setting up the field and players.");
    }
    
    @Override
    protected void startPlay() {
        System.out.println("Cricket Game Started. Play the game!");
    }
    
    @Override
    protected void endPlay() {
        System.out.println("Cricket Game Ended! Wrapping up and announcing the score.");
    }
}

class Hockey extends Game {
    
    @Override
    protected void initialize() {
        System.out.println("Hockey Game Initialized! Setting up the field and players.");
    }
    
    @Override
    protected void startPlay() {
        System.out.println("Hockey Game Started. Play the game!");
    }
    
    @Override
    protected void endPlay() {
        System.out.println("Hockey Game Ended! Wrapping up and announcing the score.");
    }
}

public class TemplatePattern{
    public static void main(String[] args) {

        System.out.println("\n =============== Playing Cricket =============== \n");
        Game game = new Cricket();
        game.play();
        System.out.println("\n =============== Playing Hockey =============== \n");
        Game game2 = new Hockey();
        game2.play();

    }
}