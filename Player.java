package AdventureGame;

public class Player {

    private final String name;
    private final int maxHP;
    public int HP;
    private final int minAtkDamage;
    private final int maxAtkDamage;
    private int critChance;
    private int numPotions;
    private int potionDropChance;
    private int healAmount;

    public Player(String name, int maxHP, int minAtkDamage, int maxAtkDamage, int critChance, int numPotions, int potionDropChance) {
        this.name = name;
        this.maxHP = maxHP;
        this.HP = maxHP;
        this.minAtkDamage = minAtkDamage;
        this.maxAtkDamage = maxAtkDamage;
        this.critChance = critChance; //displayed as percentage
        this.numPotions = numPotions;
        this.potionDropChance = potionDropChance; //percentage
    }
    //player character choices + stats (name, HP, minAtkDam, maxAtkDam, crit, potion, potionDrop)
    public static Player warrior() { 
        return new Player("WARRIOR", 100, 20, 35, 5, 3, 50);
    }

    public static Player rogue() {
        return new Player("ROGUE", 85, 15, 45, 10, 3, 55);
    }

    public static Player berserker() {
        return new Player("BERSERKER", 75, 30, 60, 7, 3, 60);
    }

    public static Player brute() {
        return new Player("BRUTE", 120, 10, 30, 6, 3, 50);
    }

    public int getHP() {
        return HP;
    }

    public int getNumPotions() {
        return numPotions;
    }
    
    public int getPotionDropChance() {
        return potionDropChance;
    }
    
    public int getMinAtkDamage() {
        return minAtkDamage;
    }
    
    public int getMaxAtkDamage() {
        return maxAtkDamage;
    }
    
    public int getCritChance() {
        return critChance;
    }

    public void playerStatus() {
        System.out.println("\n" + Game.TEXT_CYAN + name + Game.TEXT_RESET);
        System.out.println("HP: " + HP);
        System.out.println("POTIONS: " + numPotions);
    }
    
    public void heal() {
        if (numPotions > 0 && HP != maxHP) { //must have potions avail. + taken damage
            healAmount = Game.RANDOM.nextInt(maxHP - HP);
            HP += healAmount;
            numPotions--;

            System.out.println("\nYOU REGAIN " + Game.TEXT_GREEN + healAmount + Game.TEXT_RESET + " HP");
            playerStatus();
        } else if (numPotions == 0) {
            System.out.println("\nNO MORE POTIONS.");
        } else {
            System.out.println("\nMAX HEALTH. CARRY ON WITH THE BATTLE !");
        }
    }
    
    public void addPotion() {
        if (Game.RANDOM.nextInt(101) < potionDropChance) {
            numPotions++;
            System.out.println("\nTHE ENEMY DROPPED A POTION.");   
        }
    }
    
    public void playerMaxHP() { //called only for new rounds
        HP = maxHP;
    }
}
