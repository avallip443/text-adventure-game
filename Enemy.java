package AdventureGame;

public class Enemy {

    private final String name;
    public int HP;
    private final int minAtkDamage;
    private final int maxAtkDamage;
    private static int whichEnemy;

    private Enemy(String name, int HP, int minAtkDamage, int maxAtkDamage) {
        this.name = name;
        this.HP = HP;
        this.minAtkDamage = minAtkDamage;
        this.maxAtkDamage = maxAtkDamage;
    }
    
    public static Enemy boss() {
        whichEnemy = Game.RANDOM.nextInt(4);
        
        switch (whichEnemy) { //name, HP, minAtk, maxAtk
            case 0:
                return new Enemy("TITAN", 350, 10, 40);
            case 1:
                return new Enemy("DRAGON", 300, 20, 50); 
            case 2:
                return new Enemy("ARCHMAGE", 280, 35, 50);
            case 3:
                return new Enemy("HYDRA", 250, 30, 55);
            case 4:
                return new Enemy("GRIFFIN", 240, 25, 65);
        }
        return null;
    }
    
    public static Enemy newEnemy() { //name, HP, minAtk, maxAtk
        whichEnemy = Game.RANDOM.nextInt(10);

        switch (whichEnemy) {
            case 0:
                return new Enemy("BANDIT", 15, 3, 6);
            case 1:
                return new Enemy("ZOMBIE", 45, 6, 12);
            case 2:
                return new Enemy("GOBLIN", 22, 4, 15);
            case 3:
                return new Enemy("GARGOYLE", 60, 8, 14);
            case 4:
                return new Enemy("ASSASSIN", 80, 9, 18);
            case 5:
                return new Enemy("VAMPIRE", 30, 4, 12);
            case 6:
                return new Enemy("TROLL", 50, 8, 10);
            case 7:
                return new Enemy("WOLF", 20, 4, 6);
            case 8:
                return new Enemy("MAGE", 33, 7, 12);
            case 9:
                return new Enemy("NECROMANCER", 75, 8, 15);
            case 10:
                return new Enemy("GHOUL", 20, 2, 5);
        }
        return null;
    }
    
    public String getName() {
        return (Game.TEXT_YELLOW + name + Game.TEXT_RESET);
    }
    
    public int getHP() {
        return HP;
    }
    
    public int getMinAtkDamage() {
        return minAtkDamage;
    }
    
    public int getMaxAtkDamage() {
        return maxAtkDamage;
    }
        
    public void enemyStatus() {
        System.out.println("\n" + getName());
        System.out.println("ENEMY HP: " + HP);
        System.out.println("ATTACK DAMAGE: " + minAtkDamage + "-" + maxAtkDamage);
    }

}
