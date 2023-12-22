package AdventureGame;

import java.util.Scanner;
import java.util.Random;

//check stats
public class Game {

    //text colours
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_MAGENTA = "\033[0;35m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RED_BACKGROUND = "\u001B[41m";

    private static boolean readyToPlay;
    private int round;
    private int numEnemies;
    private Enemy enemy;
    public static Player PLAYER;

    public static Scanner INPUT = new Scanner(System.in);
    public static final Random RANDOM = new Random();
    public static String choice;

    public Game(int round, int numEnemies) {
        this.round = round;
        this.numEnemies = numEnemies;
    }

    public void runGame() {
        charSelection(); //creates new player from 1 of 4 character options
        PLAYER.playerMaxHP();
        System.out.println("\n\n");
        System.out.println("PREPARE YOURSELF AS YOU ENTER THE DUNGEON");

        Round:
        while (true) {
            round++;
            numTotalEnemies(); //increases with round number
            int defeatedEnemies = 0;
            boolean bossBattle = false;

            System.out.println("\n\n_______________________\n");
            System.out.println(TEXT_RED + "       ROUND: " + round + TEXT_RESET);

            Next_Enemy:
            while (numEnemies >= defeatedEnemies) {

                if (numEnemies - 1 == defeatedEnemies) { //last enemy (aka boss)
                    bossBattle = true;
                    enemy = Enemy.boss();

                    System.out.println("\nJUST AS YOU NEAR THE EXIT...");
                    System.out.println("A " + enemy.getName() + " HAS APPEARED, FURIOUS AT YOUR SLAUGHTER !");
                    System.out.println("DEFEAT THE " + enemy.getName() + " TO CLEAR THIS ROUND !!");

                } else { //reg enemies
                    enemy = Enemy.newEnemy();
                    System.out.println("_______________________");
                    System.out.println("\nA " + enemy.getName() + " HAS APPEARED !");
                    System.out.println("\nENEMIES LEFT TO DEFEAT: " + (numEnemies - defeatedEnemies));
                }

                Battle:
                while (enemy.getHP() > 0 && PLAYER.getHP() > 0) {
                    System.out.println("_______________________");
                    enemy.enemyStatus();
                    PLAYER.playerStatus();

                    System.out.println("\n(A) ATTACK \n(H) HEAL \n(R) RUN AWAY ");
                    System.out.print(TEXT_MAGENTA + "YOUR MOVE ?" + TEXT_RESET + " ");
                    choice = INPUT.nextLine();
                    System.out.println("_______________________");

                    while (!choice.equalsIgnoreCase("a") && !choice.equalsIgnoreCase("h") && !choice.equalsIgnoreCase("r")) {
                        System.out.println("\nINVALID. ENTER YOUR CHOICE: ");
                        choice = INPUT.nextLine();
                    }

                    Choice:
                    while (true) {
                        switch (choice.toUpperCase()) {
                            case "A": 
                                Battle.combat(enemy, bossBattle);
                                continue Battle;
                            case "H":
                                PLAYER.heal();
                                continue Battle;
                            case "R":
                                if (bossBattle) {
                                    System.out.println("\nNO ESCAPE. STAND YOUR GROUND.");
                                    continue Battle;
                                } else {
                                    if (Battle.runAway()) { 
                                        System.out.println("\nYOU SPOT AN OPENING AND ESCAPE THE " + enemy.getName() + " UNSCATHED !");
                                        continue Next_Enemy;
                                    } else {
                                        System.out.println("\nTHE " + enemy.getName() + " BLOCKS YOUR PATH. STAND YOUR GROUND.");
                                        break Choice;
                                    }
                                }
                        }
                    }
                }

                if (enemy.getHP() <= 0) {
                    if (bossBattle) {
                        System.out.println("\n★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
                        System.out.println(TEXT_MAGENTA + "\nCONGRADULATIONS ! THE DUNGEON IS CLEARED !" + TEXT_RESET);
                        System.out.println("\n★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
                        System.out.println("\nCONTINUE DEEPER DOWN (C) OR EXIT DUNGEON (E) ?");
                        choice = INPUT.nextLine();

                        while (!choice.equalsIgnoreCase("c") && !choice.equalsIgnoreCase("e")) {
                            System.out.println("\nINVALID. ENTER YOUR CHOICE: ");
                            choice = INPUT.nextLine();
                        }

                        if (choice.equalsIgnoreCase("c")) {
                            System.out.println("\nONWARDS!");
                            continue Round; //next round
                        } else if (choice.equalsIgnoreCase("c")) {
                            gameOver();
                        }

                    } else {
                        System.out.println(TEXT_GREEN + "\nVICTORY " + TEXT_RESET + "! YOU HEAD DEEPER INTO THE DUNGEON...");
                        defeatedEnemies++;
                    }

                } else if (PLAYER.getHP() <= 0) {
                    System.out.println("\nYOUR LIFELESS BODY HITS THE GROUND.");
                    System.out.println(TEXT_RED_BACKGROUND + "GAME OVER" + TEXT_RESET);
                    System.out.println("\n(P) PLAY AGAIN \n(E) EXIT GAME");
                    choice = INPUT.nextLine();

                    while (!choice.equalsIgnoreCase("p") && !choice.equalsIgnoreCase("e")) {
                        System.out.println("\nINVALID. ENTER YOUR CHOICE: ");
                        choice = INPUT.nextLine();
                    }

                    if (choice.equalsIgnoreCase("p")) {
                        break Round; //returns to main, loops new game
                    } else if (choice.equalsIgnoreCase("e")) {
                        gameOver();
                    }
                }
            }
        }
    }

    public void numTotalEnemies() {
        numEnemies = round * (RANDOM.nextInt(10 - 8) + 8);
    }

    public static void gameOver() {
        System.out.println("\nTHANK YOU FOR PLAYING !!");
        System.exit(0);
    }

    public static void charSelection() {
        readyToPlay = false;
        
        System.out.println("_______________________");
        System.out.println("\n   CHOOSE A CHARACTER");
        System.out.println("_______________________");
        System.out.println("\n1. " + TEXT_PURPLE + "WARRI0R" + TEXT_RESET + "\nStrong, tough, and well-rounded fighter.");
        System.out.println("\n2. " + TEXT_PURPLE + "ROGUE" + TEXT_RESET + "\nQuick and nimble, effective at landing critical attacks.");
        System.out.println("\n3. " + TEXT_PURPLE + "BERSERKER" + TEXT_RESET + "\nPowerful and reckless, but lacks armour.");
        System.out.println("\n4. " + TEXT_PURPLE + "BRUTE" + TEXT_RESET + "\nHard-to-kill, but packs a weak punch.");
        System.out.println("________________________");

        while (!readyToPlay) { //selection
            System.out.print("\nWHO WILL YOU CHOOSE? (type number) ");
            choice = INPUT.nextLine();

            switch (choice) {
                case "1":
                    readyToPlay = displayStats("WARRIOR", 100, 20, 35, 1.0, 50); //name, maxHP, minAtkDamage, maxAtkDamage, critChance, potionDropChance
                    PLAYER = Player.warrior();
                    break;
                case "2":
                    readyToPlay = displayStats("ROGUE", 85, 15, 45, 3.0, 65);
                    PLAYER = Player.rogue();
                    break;
                case "3":
                    readyToPlay = displayStats("BERSERKER", 75, 30, 60, 2.0, 70);
                    PLAYER = Player.berserker();
                    break;
                case "4":
                    readyToPlay = displayStats("BRUTE", 120, 10, 30, 2.5, 45);
                    PLAYER = Player.brute();
                    break;
            }
        }
    }

    public static boolean displayStats(String name, int maxHP, int minAtkDamage, int maxAtkDamage, double critChance, int potionDropChance) {
        System.out.println("________________________");
        System.out.println("\n" + TEXT_CYAN + name + TEXT_RESET);
        System.out.println("MAX HP: " + maxHP);
        System.out.println("ATTACK: " + minAtkDamage + "-" + maxAtkDamage);
        System.out.println("CRITICAL HIT CHANCE: " + critChance + "%");
        System.out.println("POTION DROP CHANCE: " + potionDropChance + "%");
        System.out.println("________________________");
        System.out.print("\nARE YOU SURE YOU WANT TO PLAY AS A " + TEXT_PURPLE + name + TEXT_RESET + "? " + "(y/n) ");

        while (true) {
            choice = INPUT.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.println("\n" + TEXT_CYAN + name + TEXT_RESET + " SELECTED");
                System.out.println("_______________________");
                return true;
            } else if (choice.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.print("\nPLEASE SELECT 'y' OR 'n':");
            }
        }
    }
}
