package AdventureGame;

public class AdventureGame {

    public static void main(String[] args) {

        System.out.println("WELCOME ADVENTURER...\nTO THE DUNGEON !\n");

        while (true) {
            Game g = new Game(0, 0); //new game, round 0, 0 enemies
            g.runGame();
        }
    }
}
