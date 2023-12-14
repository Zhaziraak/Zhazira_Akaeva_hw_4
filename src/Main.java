import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int [] heroesHealth = {280, 270, 250, 265, 190, 200, 180};
    public static int[] heroesDamage = {10, 20, 15, 0, 10, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic",
            "Medic", "Lucky", "Thor", "Witcher"};
    public static int roundNumber;


    public static void main(String[] args) {
        showStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        medicHelp();
        bossAttacks();
        chanceThor();
        luckyLucky();
        lifeWitcher();
        heroesAttacks();
        showStatistics();
        System.out.println();

    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesHealth.length); //0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }



    public static void lifeWitcher(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[6] > 0) {
                if (heroesHealth [i] <=0) {
                    heroesHealth[i] = heroesHealth[i] + heroesHealth[6];
                    heroesHealth[6] = 0;
                    System.out.println("Witcher help");
                }
            }

        }
    }
    public static void bossAttacks() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else{
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }
    public static void chanceThor (){
        Random random = new Random();
        boolean chance = random.nextBoolean();
        if (chance) {
            for (int i = 0; i < heroesHealth.length; i++) {
                heroesHealth[i] += bossDamage;
            }
            System.out.println("Thor`s Chance");
        }
    }

    public static void luckyLucky (){
        Random random = new Random();
        boolean randomm = random.nextBoolean();
        if (randomm) {
            if (heroesHealth[4] > 0) {
                heroesHealth[4] += bossDamage;
                System.out.println("Lucky");
            }
        }
    }
    public static void medicHelp(){
        boolean heroHealthUnder100 = false;
        Random random = new Random();
        int healing = random.nextInt(2) + 2;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] > 0){
                if (heroesHealth[i] < 100 && heroesHealth[i] > 0){
                    if (!heroHealthUnder100) {
                        if (heroesAttackType[i] != "Medic"){
                            heroHealthUnder100 = true;
                            heroesHealth [i] = heroesHealth[i] * healing;
                            System.out.println("Medic helped");
                        }
                    }
                }
            }

        }
    }
    public static void heroesAttacks() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence){
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



    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1]
                <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void showStatistics() {
        /*String defence;
        if (bossDefence == null){defence = "No defence";
        }else {
            defence = bossDefence;
        }*/
        System.out.println("ROUND " + roundNumber + "-------------");
        System.out.println("Boss health: " + bossHealth + ", damage: " +
                bossDamage + ", defence: " + (bossDefence == null ? "No defence" : bossDefence)); //термальная конструкция
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + ", damage: " + heroesDamage[i]);
        }

    }

}