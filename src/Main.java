import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 100;
    public static String bossDefence;
    public static int[] heroesHealth = {280, 270, 250, 250, 400, 500};
    public static int[] heroesDamage = {10, 15, 20, 0, 0, 5};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Witcher", "Lucky"};
    public static int roundNumber;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }

    }

    public static void lifeThor (){
        Random random = new Random();
        boolean randT = random.nextBoolean();
        if (randT){
            for (int i = 0; i < heroesHealth.length; i++) {
                heroesHealth[i] += bossDamage;
            }
            System.out.println("thor did it");
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        healingHero();
        bossAttack();
        lifeThor();
        luckyAbility();
        lifeWitcher();
        heroesAttack();
        printStatistics();
    }

    public static void healingHero() {
        boolean heroHealthUnder100 = false;
        int medicHelp = 50;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] > 0) {
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                    if (!heroHealthUnder100) {
                        if (heroesAttackType[i] != "Medic") {
                            heroHealthUnder100 = true;
                            heroesHealth[i] = heroesHealth[i] + medicHelp;
                        }
                    }
                }
            }
        }
    }

    public static void luckyAbility() {
        Random random = new Random();
        boolean rand2 = random.nextBoolean();
        if (rand2) {
            if (heroesHealth[5] > 0) {
                heroesHealth[5] += bossDamage;
                System.out.println("lucky");
            }
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }

        }
    }

    public static void lifeWitcher() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[4] > 0) {
                if (heroesHealth[i] <= 0) {
                    heroesHealth[i] = heroesHealth[i] + heroesHealth[4];
                    heroesHealth[4] = 0;
                    System.out.println("Witcher");
                }
            }
        }
    }

    public static void bossAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                /*Random random = new Random();
                boolean luck = random.nextBoolean();
                if (luck = true) {
                    heroesHealth[3] = heroesHealth[3];
                }else*/
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
         */
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("ROUND " + roundNumber + " -------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage +
                " defence: " + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }

}