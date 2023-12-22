package AdventureGame;

public class Battle {

    private static int randomEnemyChance;
    private static int escape;
    private static int playerDamageDealt;
    private static int enemyDamageDealt;

    public static void combat(Enemy enemy, boolean bossBattle) {
        playerAttack();
        enemy.HP -= playerDamageDealt;
        System.out.println("\nYOU HIT THE " + enemy.getName() + " FOR " + Game.TEXT_GREEN + playerDamageDealt + Game.TEXT_RESET + " HP");
        
        if (enemy.getHP() < 0) {
            Game.PLAYER.addPotion();
            return;
        }

        enemyAttack(enemy, bossBattle);
        Game.PLAYER.HP -= enemyDamageDealt;
        System.out.println("\nTHE " + enemy.getName() + " HITS YOU BACK FOR " + Game.TEXT_RED + enemyDamageDealt + Game.TEXT_RESET + " HP");
    }

    public static void playerAttack() {
        playerDamageDealt = Game.RANDOM.nextInt(Game.PLAYER.getMaxAtkDamage() - Game.PLAYER.getMinAtkDamage() + 1) + Game.PLAYER.getMinAtkDamage();

        if (Game.RANDOM.nextInt(101) <= Game.PLAYER.getCritChance()) { //double damage chance 
            System.out.println("\nNICE ! YOU LANDED A CRTICAL HIT ! (X2 DAMAGE)");
            playerDamageDealt = 2 * playerDamageDealt;
        }
    }

    public static void enemyAttack(Enemy enemy, boolean bossBattle) {
        randomEnemyChance = Game.RANDOM.nextInt(19);
        enemyDamageDealt = Game.RANDOM.nextInt(enemy.getMaxAtkDamage() - enemy.getMinAtkDamage() + 1) + enemy.getMinAtkDamage();

        if (!bossBattle) { //reg enemy
            if (randomEnemyChance == 2) { // 5% chance for double damage
                enemyDamageDealt = 2 * Game.RANDOM.nextInt(enemy.getMaxAtkDamage() - enemy.getMinAtkDamage() + 1) + enemy.getMinAtkDamage();
                System.out.println("\nUH OH ! THE " + enemy.getName() + " LANDS A CRITICAL HIT");
            } else if (randomEnemyChance == 1) { //5% chance for half damage
                enemyDamageDealt = Game.RANDOM.nextInt(enemy.getMaxAtkDamage() - enemy.getMinAtkDamage() + 1) + enemy.getMinAtkDamage() / 2;
                System.out.println("\nTHE " + enemy.getName() + ", WEAK FROM YOUR DAMAGE, ATTACKS AT ONLY HALF THEIR STRENGTH");
            }
        }
    }

    public static boolean runAway() { //30% chance to escape
        escape = Game.RANDOM.nextInt(10);
        return escape < 3;
    }

}
