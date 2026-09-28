package com.mycompany.mathdungeon;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/*
 * MATH DUNGEON
 * Educational 2D Java Game
 *
 * OOP concepts used:
 *
 * 1. Encapsulation
 *    - Player and Enemy fields are private.
 *
 * 2. Inheritance
 *    - Goblin, Skeleton, Orc, etc. inherit from Enemy.
 *
 * 3. Polymorphism
 *    - The enemy variable can hold different Enemy objects.
 *
 * 4. Abstraction
 *    - Enemy is an abstract class.
 *
 * No external libraries are needed.
 * Works with Java Swing / Java2D.
 */

public class MathDungeon extends JFrame {

    public MathDungeon() {

        setTitle("Math Dungeon");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new GamePanel());

        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MathDungeon();
        });
    }
}


/* =========================================================
   PLAYER CLASS
   ========================================================= */

class Player {

    private String name;

    private int hp;
    private int maxHp;

    private int level;

    private int xp;
    private int xpNeeded;

    private int attack;
    private int defense;

    private int gold;
    private int potions;

    private int correctAnswers;
    private int wrongAnswers;

    private int combo;


    public Player(String name) {

        this.name = name;

        hp = 100;
        maxHp = 100;

        level = 1;

        xp = 0;
        xpNeeded = 100;

        attack = 15;
        defense = 5;

        gold = 50;
        potions = 1;

        correctAnswers = 0;
        wrongAnswers = 0;

        combo = 0;
    }


    public int takeDamage(int damage) {

        int actualDamage = damage - defense;

        if (actualDamage < 1) {
            actualDamage = 1;
        }

        hp -= actualDamage;

        if (hp < 0) {
            hp = 0;
        }

        return actualDamage;
    }


    public int heal() {

        if (potions <= 0) {
            return 0;
        }

        if (hp >= maxHp) {
            return 0;
        }

        potions--;

        int oldHP = hp;

        hp += 30;

        if (hp > maxHp) {
            hp = maxHp;
        }

        return hp - oldHP;
    }


    public void addPotion() {
        potions++;
    }


    public void addGold(int amount) {
        gold += amount;
    }


    public boolean gainXP(int amount) {

        xp += amount;

        boolean leveledUp = false;

        while (xp >= xpNeeded) {

            xp -= xpNeeded;

            level++;

            xpNeeded += 50;

            maxHp += 15;

            attack += 3;

            defense += 2;

            hp = maxHp;

            leveledUp = true;
        }

        return leveledUp;
    }


    public void correctAnswer() {
        correctAnswers++;
    }


    public void wrongAnswer() {
        wrongAnswers++;
    }


    public void addCombo() {
        combo++;
    }


    public void resetCombo() {
        combo = 0;
    }


    public String getName() {
        return name;
    }


    public int getHp() {
        return hp;
    }


    public int getMaxHp() {
        return maxHp;
    }


    public int getLevel() {
        return level;
    }


    public int getXp() {
        return xp;
    }


    public int getXpNeeded() {
        return xpNeeded;
    }


    public int getAttack() {
        return attack;
    }


    public int getDefense() {
        return defense;
    }


    public int getGold() {
        return gold;
    }


    public int getPotions() {
        return potions;
    }


    public int getCorrectAnswers() {
        return correctAnswers;
    }


    public int getWrongAnswers() {
        return wrongAnswers;
    }


    public int getCombo() {
        return combo;
    }
}


/* =========================================================
   ABSTRACT ENEMY CLASS
   ========================================================= */

abstract class Enemy {

    private String name;

    private int hp;
    private int maxHp;

    private int damage;

    private int goldReward;
    private int xpReward;

    private boolean boss;


    public Enemy(
            String name,
            int hp,
            int damage,
            int goldReward,
            int xpReward,
            boolean boss) {

        this.name = name;

        this.hp = hp;
        this.maxHp = hp;

        this.damage = damage;

        this.goldReward = goldReward;
        this.xpReward = xpReward;

        this.boss = boss;
    }


    // Abstract method.
    // Every enemy can have its own battle message.
    public abstract String attackMessage();


    public void takeDamage(int damage) {

        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }
    }


    public boolean isDead() {
        return hp <= 0;
    }


    /*
     * Makes the enemy stronger.
     *
     * This is used after the player reaches
     * another cycle of enemies.
     */
    public void makeStronger(
            int hpIncrease,
            int damageIncrease) {

        maxHp += hpIncrease;

        hp += hpIncrease;

        damage += damageIncrease;
    }


    public String getName() {
        return name;
    }


    public int getHp() {
        return hp;
    }


    public int getMaxHp() {
        return maxHp;
    }


    public int getDamage() {
        return damage;
    }


    public int getGoldReward() {
        return goldReward;
    }


    public int getXpReward() {
        return xpReward;
    }


    public boolean isBoss() {
        return boss;
    }
}


/* =========================================================
   GOBLIN
   ========================================================= */

class Goblin extends Enemy {

    public Goblin() {

        super(
                "William",
                50,
                10,
                15,
                25,
                false
        );
    }


    @Override
    public String attackMessage() {

        return "William Attacks!";
    }
}


/* =========================================================
   SKELETON
   ========================================================= */

class Skeleton extends Enemy {

    public Skeleton() {

        super(
                "Vince",
                70,
                13,
                20,
                35,
                false
        );
    }


    @Override
    public String attackMessage() {

        return "Vince Attacks!";
    }
}


/* =========================================================
   ORC
   ========================================================= */

class Orc extends Enemy {

    public Orc() {

        super(
                "Karl",
                95,
                16,
                30,
                45,
                false
        );
    }


    @Override
    public String attackMessage() {

        return "Karl Attacks!";
    }
}


/* =========================================================
   ORC SHAMAN
   ========================================================= */

class OrcShaman extends Enemy {

    public OrcShaman() {

        super(
                "Patrick",
                115,
                19,
                40,
                55,
                false
        );
    }


    @Override
    public String attackMessage() {

        return "Patrick Attacks!";
    }
}


/* =========================================================
   DARK KNIGHT
   ========================================================= */

class DarkKnight extends Enemy {

    public DarkKnight() {

        super(
                "Jaypee",
                140,
                23,
                60,
                70,
                false
        );
    }


    @Override
    public String attackMessage() {

        return "Jaypee Attacks!";
    }
}


/* =========================================================
   MATH DRAGON
   ========================================================= */

class MathDragon extends Enemy {

    public MathDragon() {

        super(
                "Kim",
                220,
                28,
                150,
                120,
                true
        );
    }

    @Override
    public String attackMessage() {

        return "Kim throws a deadly fan!";
    }
}


/* =========================================================
   MATH QUESTION CLASS
   ========================================================= */

class MathQuestion {

    private String question;

    private int answer;

    private int[] choices;


    public MathQuestion(int difficulty) {

        Random random = new Random();

        int number1;
        int number2;

        int operation = random.nextInt(3);


        /*
         * Difficulty 1
         * Small numbers
         */
        if (difficulty == 1) {

            number1 = random.nextInt(10) + 1;

            number2 = random.nextInt(10) + 1;
        }


        /*
         * Difficulty 2
         */
        else if (difficulty == 2) {

            number1 = random.nextInt(20) + 5;

            number2 = random.nextInt(15) + 2;
        }


        /*
         * Difficulty 3
         */
        else {

            number1 = random.nextInt(40) + 10;

            number2 = random.nextInt(25) + 5;
        }


        /*
         * Addition
         */
        if (operation == 0) {

            question =
                    number1
                    + " + "
                    + number2
                    + " = ?";

            answer = number1 + number2;
        }


        /*
         * Subtraction
         */
        else if (operation == 1) {

            // Prevent negative answers.

            if (number1 < number2) {

                int temporary = number1;

                number1 = number2;

                number2 = temporary;
            }

            question =
                    number1
                    + " - "
                    + number2
                    + " = ?";

            answer = number1 - number2;
        }


        /*
         * Multiplication
         */
        else {

            question =
                    number1
                    + " x "
                    + number2
                    + " = ?";

            answer = number1 * number2;
        }


        createChoices(random);
    }
    
        public MathQuestion(String question, int answer) {

        this.question = question;

        this.answer = answer;

        createChoices(new Random());
    }
    

    private void createChoices(Random random) {

        choices = new int[4];

        choices[0] = answer;


        /*
         * Create three wrong answers.
         */
        for (int i = 1; i < 4; i++) {

            int wrongAnswer;

            do {

                wrongAnswer =
                        answer
                        + random.nextInt(21)
                        - 10;

            } while (wrongAnswer == answer);


            choices[i] = wrongAnswer;
        }


        /*
         * Shuffle choices.
         */
        for (int i = 0; i < choices.length; i++) {

            int randomIndex =
                    random.nextInt(choices.length);

            int temporary =
                    choices[i];

            choices[i] =
                    choices[randomIndex];

            choices[randomIndex] =
                    temporary;
        }
    }


    public String getQuestion() {
        return question;
    }


    public int getAnswer() {
        return answer;
    }


    public int[] getChoices() {
        return choices;
    }
}


class BossQuestionGenerator {

public static MathQuestion create() {

    Random random = new Random();

    int type = random.nextInt(5);

    int a = random.nextInt(9) + 2;
    int b = random.nextInt(8) + 2;
    int c = random.nextInt(15) + 2;

    int answer;
    String question;


    // Example: 8 x 7 + 12
    if (type == 0) {

        answer = (a * b) + c;

        question =
                a + " x " + b + " + " + c + " = ?";
    }


    // Example: 9 x 6 - 14
    else if (type == 1) {

        answer = (a * b) - c;

        if (answer < 0) {

            answer = (a * b) + c;

            question =
                    a + " x " + b + " + " + c + " = ?";

        } else {

            question =
                    a + " x " + b + " - " + c + " = ?";
        }
    }


    // Example: (8 + 4) x 3
    else if (type == 2) {

        answer = (a + b) * c;

        question =
                "("
                + a
                + " + "
                + b
                + ") x "
                + c
                + " = ?";
    }


    // Example: 6 x 4 + 5 x 3
    else if (type == 3) {

        int d = random.nextInt(7) + 2;
        int e = random.nextInt(6) + 2;

        answer =
                (a * b) + (d * e);

        question =
                a
                + " x "
                + b
                + " + "
                + d
                + " x "
                + e
                + " = ?";
    }

    else {

        int subtract =
                random.nextInt(6) + 2;

        int multiply =
                random.nextInt(5) + 2;

        int addition =
                random.nextInt(15) + 2;

        if (a <= subtract) {
            a = subtract + 2;
        }
        answer =
                (a - subtract)
                * multiply
                + addition;
        question =
                "("
                + a
                + " - "
                + subtract
                + ") x "
                + multiply
                + " + "
                + addition
                + " = ?";
    }

    return new MathQuestion(
            question,
            answer
    );
}
}
/* =========================================================
   GAME PANEL
   ========================================================= */
class GamePanel extends JPanel {

    private Player player;
    private Enemy enemy;
    private MathQuestion mathQuestion;
    private JButton[] answerButtons =
            new JButton[4];
    private JButton potionButton;
    private JButton statsButton;
    private JButton restartButton;
    private int enemiesDefeated = 0; // statistics only; no progression
    private int floor = 1;
    private int room = 1;
    private boolean attacking = false;
    private boolean gameOver = false;
    /*
     * Countdown timer.
     * Normal enemy = 10 seconds.
     * Boss = 15 seconds.
     */
    private int timeLeft = 10;
    private Timer countdownTimer;
/*
 * ================= FAN ATTACK =================
 */
    private int fanX = 820;
    private int fanY = 420;
    private boolean fanFlying = false;
    private int fanRotation = 0;
    /*
     * LOOPING FLYING CREATURE ANIMATIONS
     *
     * These creatures continuously fly around the dungeon.
     */
    private int fly1X = 80;
    private int fly1Y = 150;
    private int fly1Direction = 1;
    private int fly2X = 1080;
    private int fly2Y = 210;
    private int fly2Direction = -1;
    private int babyDragonX = 560;
    private int babyDragonY = 145;
    private int babyDragonDirection = 1;
    private Timer animationTimer;
/*
 * Character positions.
 */
    private int heroX = 300;
    private int enemyX = 820;
/*
 * Animation movement.
 */
    private int heroMovement = 0;
    private int enemyMovement = 0;
    /*
     * Damage display.
     */
    private boolean showEnemyDamage = false;
    private boolean showHeroDamage = false;
    private int damageNumber = 0;
    private String battleMessage =
            "Solve the math problem to attack!";
    private String potionMessage = "";
    public GamePanel() {
        player =
                new Player("Joriz");
        setLayout(null);
        setBackground(
                new Color(15, 18, 28)
        );
        createButtons();
        spawnEnemy();
    }
    /* =====================================================
       BUTTON CREATION
       ===================================================== */
    private void createButtons() {

        int startingX = 280;
        /*
         * Answer buttons
         */
        for (int i = 0; i < 4; i++) {
            answerButtons[i] =
                    new JButton();
            answerButtons[i].setBounds(
                    startingX + (i * 155),
                    620,
                    140,
                    55
            );
            answerButtons[i].setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    )
            );
            
            answerButtons[i].setFocusPainted(false);
            final int buttonNumber = i;
            answerButtons[i].addActionListener(
                    e -> answerQuestion(buttonNumber)
            );
            add(answerButtons[i]);
        }
        
        /*
         * Potion button
         */
        potionButton =
                new JButton("USE POTION");
        potionButton.setBounds(
                1000,
                515,
                180,
                50
        );
        potionButton.addActionListener(
                e -> usePotion()
        );
        add(potionButton);

        /*
         * Stats button
         */
        statsButton =
                new JButton("PLAYER STATS");
        statsButton.setBounds(
                1000,
                575,
                180,
                45
        );
        statsButton.addActionListener(
                e -> showStats()
        );
        add(statsButton);

        /*
         * Restart button
         */
        restartButton =
                new JButton("RESTART GAME");
        restartButton.setBounds(
                1000,
                630,
                180,
                45
        );
        restartButton.addActionListener(
                e -> restartGame()
        );
        add(restartButton);
    }

    /* =====================================================
       CREATE NEXT ENEMY
       ===================================================== */

    private void spawnEnemy() {

        int enemyNumber =
                (enemiesDefeated % 6) + 1;


        /*
         * Polymorphism:
         *
         * enemy is an Enemy reference,
         * but it can contain any subclass.
         */

        if (enemyNumber == 1) {
            enemy = new Goblin();

        } else if (enemyNumber == 2) {
            enemy = new Skeleton();

        } else if (enemyNumber == 3) {
            enemy = new Orc();

        } else if (enemyNumber == 4) {
            enemy = new OrcShaman();

        } else if (enemyNumber == 5) {
            enemy = new DarkKnight();
        } else {
            enemy = new MathDragon();
        }

        /*
         * Every 6 enemies,
         * the next cycle becomes stronger.
         */
        int cycle =
                enemiesDefeated / 6;

        if (cycle > 0) {

            enemy.makeStronger(
                    cycle * 30,
                    cycle * 4
            );
        }

        /*
         * Increase math difficulty.
         */
        int difficulty;

        if (enemiesDefeated < 4) {
            difficulty = 1;

        } else if (enemiesDefeated < 9) {
            difficulty = 2;

        } else {
            difficulty = 3;
        }

        if (enemy.isBoss()) {
            mathQuestion =
                BossQuestionGenerator.create();
        } else {

        mathQuestion =
            new MathQuestion(difficulty);
}

        heroX = 300;
        enemyX = 820;
        battleMessage =
                "A "
                + enemy.getName()
                + " appears!";
        potionMessage = "";
        updateButtons();
        startCountdownTimer();
        repaint();
    }

    /* =====================================================
       ANSWER MATH QUESTION
       ===================================================== */

    private void answerQuestion(int index) {

        if (attacking) {
            return;
        }


        if (gameOver) {
            return;
        }


        attacking = true;


        int selectedAnswer =
                mathQuestion
                        .getChoices()[index];


        /*
         * CORRECT ANSWER
         */
        if (selectedAnswer ==
                mathQuestion.getAnswer()) {


            player.correctAnswer();

            player.addCombo();


            int damage =
                    player.getAttack();


            /*
             * Combo bonus.
             */
            if (player.getCombo() >= 2) {

                damage +=
                        player.getCombo() * 3;
            }


            enemy.takeDamage(damage);


            damageNumber =
                    damage;


            showEnemyDamage =
                    true;


            battleMessage =
                    "CORRECT! Hero attacks for "
                    + damage
                    + " damage!";


            /*
             * Reset the timer after every correct answer.
             * Normal enemies get 10 seconds.
             * Bosses get 15 seconds.
             */
            if (enemy.isBoss()) {

                timeLeft = 15;

            } else {

                timeLeft = 10;
            }

            if (countdownTimer != null) {
                countdownTimer.stop();
            }

            startCountdownTimer();


            animateHeroAttack();
        }


        /*
         * WRONG ANSWER
         */
        else {

            player.wrongAnswer();

            player.resetCombo();


            battleMessage =
                    "WRONG! Correct answer was "
                    + mathQuestion.getAnswer();


            animateEnemyAttack();
        }

        repaint();
    }


    /* =====================================================
       HERO ATTACK ANIMATION
       ===================================================== */

    private void animateHeroAttack() {

        Timer timer =
                new Timer(25, null);


        final int[] frame =
                {0};


        timer.addActionListener(e -> {

            frame[0]++;


            /*
             * Move hero toward enemy.
             */
            if (frame[0] <= 12) {

                heroMovement =
                        frame[0] * 10;
            }


            /*
             * Move hero back.
             */
            else if (frame[0] <= 24) {

                heroMovement =
                        (24 - frame[0]) * 10;
            }


            /*
             * Finish attack.
             */
            else {

                heroMovement = 0;

                timer.stop();


                /*
                 * If enemy died,
                 * give rewards.
                 */
                if (enemy.isDead()) {

                    defeatEnemy();

                } else {

                    /*
                     * Enemy attacks back.
                     */
                    animateEnemyAttack();
                }
            }


            repaint();
        });


        timer.start();
    }


    /* =====================================================
       ENEMY ATTACK ANIMATION
       ===================================================== */

    private void animateEnemyAttack() {

        Timer timer =
                new Timer(25, null);


        final int[] frame =
                {0};


        timer.addActionListener(e -> {

            frame[0]++;


            /*
             * Enemy moves toward hero.
             */
            if (frame[0] <= 12) {

                enemyMovement =
                        -frame[0] * 8;
            }


            /*
             * Enemy returns.
             */
            else if (frame[0] <= 24) {

                enemyMovement =
                        -(24 - frame[0]) * 8;
            }


            /*
             * Attack finished.
             */
            else {

                enemyMovement = 0;


                int damage =
                        player.takeDamage(
                                enemy.getDamage()
                        );


                damageNumber =
                        damage;


                showHeroDamage =
                        true;


                battleMessage =
                        enemy.attackMessage()
                        + " -"
                        + damage
                        + " HP!";


                timer.stop();


                /*
                 * Player died.
                 */
                if (player.getHp() <= 0) {

                    gameOver = true;

                    battleMessage =
                            "GAME OVER! Press RESTART GAME.";
                }


                /*
                 * Continue game.
                 */
                else {

                    if (enemy.isBoss()) {

                        mathQuestion =
                                BossQuestionGenerator.create();

                    } else {

                        mathQuestion =
                                new MathQuestion(
                                        getDifficulty()
                                );
                    }
                }


                attacking = false;

                updateButtons();
            }


            repaint();
        });


        timer.start();
    }


    /* =====================================================
       ENEMY DEFEATED
       ===================================================== */

    private void defeatEnemy() {

        int gold =
                enemy.getGoldReward();


        int xp =
                enemy.getXpReward();


        player.addGold(gold);


        boolean levelUp =
                player.gainXP(xp);


        /*
         * Every enemy drops a potion.
         */
        player.addPotion();


        enemiesDefeated++;


        room++;


        /*
         * Five rooms per floor.
         */
        if (room > 5) {

            room = 1;

            floor++;
        }


        potionMessage =
                "POTION DROPPED! +1 Potion";


        if (enemy.isBoss()) {

            JOptionPane.showMessageDialog(
                    this,

                    "BOSS DEFEATED!\n\n"
                    + "You defeated the Math Dragon!\n\n"
                    + "Gold: +"
                    + gold
                    + "\nXP: +"
                    + xp
                    + "\nPotion: +1",

                    "Victory!",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }


        if (levelUp) {

            battleMessage =
                    "LEVEL UP! You are now Level "
                    + player.getLevel()
                    + "!";

        } else {

            battleMessage =
                    enemy.getName()
                    + " defeated! Gold +"
                    + gold
                    + ", XP +"
                    + xp;
        }


        attacking = false;

        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        spawnEnemy();
    }


    /* =====================================================
       GET DIFFICULTY
       ===================================================== */

    private int getDifficulty() {

        if (enemiesDefeated < 4) {

            return 1;
        }

        if (enemiesDefeated < 9) {

            return 2;
        }

        return 3;
    }


    /* =====================================================
       USE POTION
       ===================================================== */

    private void usePotion() {

        if (gameOver) {
            return;
        }


        if (attacking) {
            return;
        }


        if (player.getPotions() <= 0) {

            battleMessage =
                    "You do not have any potions!";

        } else if (
                player.getHp()
                >= player.getMaxHp()) {

            battleMessage =
                    "Your HP is already full!";

        } else {

            int healed =
                    player.heal();


            battleMessage =
                    "Potion used! +"
                    + healed
                    + " HP restored.";
        }


        updateButtons();

        repaint();
    }


    /* =====================================================
       SHOW PLAYER STATS
       ===================================================== */

    private void showStats() {

        int totalAnswers =
                player.getCorrectAnswers()
                + player.getWrongAnswers();


        double accuracy = 0;


        if (totalAnswers > 0) {

            accuracy =
                    player.getCorrectAnswers()
                    * 100.0
                    / totalAnswers;
        }


        String stats =

                "========== HERO STATS ==========\n\n"

                + "Level: "
                + player.getLevel()
                + "\n"

                + "Floor: "
                + floor
                + "\n"

                + "Room: "
                + room
                + "\n"

                + "Enemies Defeated: "
                + enemiesDefeated
                + "\n\n"

                + "HP: "
                + player.getHp()
                + " / "
                + player.getMaxHp()
                + "\n"

                + "Attack: "
                + player.getAttack()
                + "\n"

                + "Defense: "
                + player.getDefense()
                + "\n"

                + "Gold: "
                + player.getGold()
                + "\n"

                + "Potions: "
                + player.getPotions()
                + "\n\n"

                + "Correct Answers: "
                + player.getCorrectAnswers()
                + "\n"

                + "Wrong Answers: "
                + player.getWrongAnswers()
                + "\n"

                + "Accuracy: "
                + String.format(
                        "%.1f",
                        accuracy
                )
                + "%\n"

                + "Combo: x"
                + player.getCombo();


        JOptionPane.showMessageDialog(
                this,
                stats,
                "Player Statistics",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    /* =====================================================
       RESTART GAME
       ===================================================== */

    private void restartGame() {

        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,

                        "Start a new adventure?",

                        "Restart Game",

                        JOptionPane.YES_NO_OPTION
                );


        if (result ==
                JOptionPane.YES_OPTION) {

            player =
                    new Player("Joriz");


            enemiesDefeated = 0;

            floor = 1;

            room = 1;

            attacking = false;

            gameOver = false;


            spawnEnemy();

            repaint();
        }
    }


    /* =====================================================
       UPDATE BUTTONS
       ===================================================== */

    private void updateButtons() {

        if (mathQuestion == null) {
            return;
        }


        int[] choices =
                mathQuestion.getChoices();


        for (int i = 0; i < 4; i++) {

            answerButtons[i].setText(
                    String.valueOf(
                            choices[i]
                    )
            );


            answerButtons[i].setEnabled(
                    !gameOver
                    && !attacking
            );
        }


        potionButton.setText(
                "USE POTION ("
                + player.getPotions()
                + ")"
        );


        potionButton.setEnabled(
                !gameOver
                && !attacking
        );
    }


    /* =====================================================
       COUNTDOWN TIMER
       ===================================================== */

    private void startCountdownTimer() {

        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        if (enemy.isBoss()) {
            timeLeft = 15;
        } else {
            timeLeft = 10;
        }

        countdownTimer = new Timer(1000, e -> {

            if (gameOver || attacking) {
                return;
            }

            timeLeft--;

            repaint();

            if (timeLeft <= 0) {
                countdownTimer.stop();
                timeRanOut();
            }
        });

        countdownTimer.start();
    }


    private void timeRanOut() {

        if (gameOver) {
            return;
        }

        attacking = true;
        player.resetCombo();

        battleMessage =
                "TIME'S UP! "
                + enemy.getName()
                + " attacks!";

        repaint();

        Timer delay = new Timer(700, e -> {

            if (enemy.isBoss()) {
                animateBossFanAttack();
            } else {

                int damage =
                        player.takeDamage(enemy.getDamage());

                damageNumber = damage;
                showHeroDamage = true;

                battleMessage =
                        "TIME'S UP! "
                        + enemy.getName()
                        + " attacks for -"
                        + damage
                        + " HP!";

                finishEnemyAttack();
            }
        });

        delay.setRepeats(false);
        delay.start();
    }


    private void finishEnemyAttack() {

        if (player.getHp() <= 0) {

            gameOver = true;
            attacking = false;

            battleMessage =
                    "GAME OVER! Press RESTART GAME.";

        } else {

            if (enemy.isBoss()) {
                mathQuestion = BossQuestionGenerator.create();
            } else {
                mathQuestion =
                        new MathQuestion(getDifficulty());
            }

            attacking = false;
            showHeroDamage = false;

            updateButtons();
            startCountdownTimer();
        }

        updateButtons();
        repaint();
    }


    private void animateBossFanAttack() {

        fanX = enemyX;
        fanY = 420;
        fanRotation = 0;
        fanFlying = true;

        battleMessage =
                enemy.getName()
                + " throws a deadly fan!";

        repaint();

        Timer timer = new Timer(25, null);

        timer.addActionListener(e -> {

            fanX -= 14;
            fanRotation += 25;

            if (fanX <= heroX + 40) {

                fanFlying = false;
                timer.stop();

                int damage =
                        player.takeDamage(enemy.getDamage());

                damageNumber = damage;
                showHeroDamage = true;

                battleMessage =
                        "The fan hits "
                        + player.getName()
                        + "! -"
                        + damage
                        + " HP!";

                finishEnemyAttack();
            }

            repaint();
        });

        timer.start();
    }


    /* =====================================================
       TIMER DISPLAY
       ===================================================== */

    private void drawTimer(Graphics2D g) {

        g.setColor(new Color(7, 11, 18));

        g.fillRoundRect(
                480,
                285,
                240,
                50,
                15,
                15
        );

        if (timeLeft <= 3) {
            g.setColor(Color.RED);
        } else if (timeLeft <= 5) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.WHITE);
        }

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        String text =
                "TIME: " + timeLeft + "s";

        int width =
                g.getFontMetrics().stringWidth(text);

        g.drawString(
                text,
                600 - width / 2,
                318
        );

        if (enemy != null && enemy.isBoss()) {

            g.setColor(Color.RED);

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            11
                    )
            );

            g.drawString(
                    "BOSS TIMER - 15 SECONDS",
                    535,
                    333
            );
        }
    }


    /* =====================================================
       FLYING FAN
       ===================================================== */

    private void drawFlyingFan(
            Graphics2D g,
            int x,
            int y,
            int rotation) {

        Graphics2D copy =
                (Graphics2D) g.create();

        copy.translate(x, y);

        copy.rotate(
                Math.toRadians(rotation)
        );

        copy.setColor(
                new Color(210, 210, 220)
        );

        for (int i = 0; i < 6; i++) {

            copy.fillOval(
                    -8,
                    -55,
                    16,
                    55
            );

            copy.rotate(
                    Math.PI / 3
            );
        }

        copy.setColor(
                new Color(150, 150, 160)
        );

        copy.fillOval(
                -12,
                -12,
                24,
                24
        );

        copy.setColor(Color.RED);

        copy.drawOval(
                -60,
                -60,
                120,
                120
        );

        copy.dispose();
    }


    /* =====================================================
       DRAW EVERYTHING
       ===================================================== */

    /*
     * Starts the endless creature animation loop.
     */
    private void startLoopingAnimations() {

        animationTimer = new Timer(30, e -> {

            fly1X += fly1Direction * 3;
            fly1Y += (int) (Math.sin(fly1X * 0.05) * 2);

            fly2X += fly2Direction * 4;
            fly2Y += (int) (Math.cos(fly2X * 0.04) * 2);

            babyDragonX += babyDragonDirection * 2;

            babyDragonY =
                    145
                    + (int)
                    (Math.sin(babyDragonX * 0.035) * 22);

            if (fly1X > 1240) {
                fly1X = -80;
            }

            if (fly2X < -80) {
                fly2X = 1240;
            }

            if (babyDragonX > 1240) {
                babyDragonX = -100;
            }

            repaint();
        });

        animationTimer.start();
    }

    /*
     * Draws all continuously moving creatures.
     */
    private void drawLoopingCreatures(Graphics2D g) {

        drawFlyingBug(
                g,
                fly1X,
                fly1Y,
                fly1Direction
        );

        drawFlyingBug(
                g,
                fly2X,
                fly2Y,
                fly2Direction
        );

        drawBabyDragon(
                g,
                babyDragonX,
                babyDragonY,
                babyDragonDirection
        );
    }

    /*
     * Small flying bug / fly.
     */
    private void drawFlyingBug(
            Graphics2D g,
            int x,
            int y,
            int direction) {

        int wingLift =
                (int)
                (Math.sin(
                        (x + y) * 0.15
                ) * 4);

        g.setColor(
                new Color(25, 25, 30)
        );

        g.fillOval(
                x,
                y,
                22,
                12
        );

        g.setColor(
                new Color(210, 210, 220)
        );

        g.fillOval(
                x - 8,
                y - 8 + wingLift,
                18,
                12
        );

        g.fillOval(
                x + 12,
                y - 8 - wingLift,
                18,
                12
        );

        g.setColor(Color.RED);

        g.fillOval(
                x + 15,
                y + 3,
                4,
                4
        );

        g.setColor(
                new Color(100, 100, 110)
        );

        int tailX =
                direction > 0
                ? x - 8
                : x + 22;

        g.drawLine(
                x + 10,
                y + 5,
                tailX,
                y + 5
        );
    }

    /*
     * Cute looping baby dragon.
     */
    private void drawBabyDragon(
            Graphics2D g,
            int x,
            int y,
            int direction) {

        int wingFlap =
                (int)
                (Math.sin(
                        x * 0.12
                ) * 8);

        g.setColor(
                new Color(70, 150, 75)
        );

        g.fillOval(
                x,
                y,
                55,
                32
        );

        g.setColor(
                new Color(95, 180, 90)
        );

        g.fillOval(
                x + 38,
                y - 8,
                30,
                28
        );

        g.setColor(
                new Color(50, 110, 55)
        );

        g.fillOval(
                x - 18,
                y - 5 - wingFlap,
                32,
                20
        );

        g.fillOval(
                x + 20,
                y - 10 + wingFlap,
                32,
                20
        );

        g.setColor(Color.WHITE);

        g.fillOval(
                x + 54,
                y - 2,
                7,
                7
        );

        g.setColor(Color.BLACK);

        g.fillOval(
                x + 56,
                y,
                3,
                3
        );

        g.setColor(
                new Color(220, 90, 55)
        );

        Polygon fire = new Polygon();

        fire.addPoint(
                x + 68,
                y + 10
        );

        fire.addPoint(
                x + 82,
                y + 6
        );

        fire.addPoint(
                x + 70,
                y + 16
        );

        g.fillPolygon(fire);

        g.setColor(
                new Color(40, 90, 45)
        );

        for (int i = 0; i < 4; i++) {

            int sx =
                    x
                    + 8
                    + i * 12;

            g.fillPolygon(
                    new int[]{
                        sx,
                        sx + 6,
                        sx + 12
                    },
                    new int[]{
                        y + 2,
                        y - 9,
                        y + 5
                    },
                    3
            );
        }
    }


    @Override
    
protected void paintComponent(Graphics g) {

        super.paintComponent(g);


        Graphics2D g2 =
                (Graphics2D) g.create();


        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );


        drawDungeon(g2);

        drawTopHUD(g2);

        drawTimer(g2);

        drawCharacters(g2);

        drawQuestionBox(g2);

        drawSidePanels(g2);

        drawBattleLog(g2);


        g2.dispose();
    }


    /* =====================================================
       DUNGEON BACKGROUND
       ===================================================== */

    private void drawDungeon(Graphics2D g) {

        /*
         * Background.
         */
        g.setColor(
                new Color(20, 23, 32)
        );

        g.fillRect(
                0,
                0,
                1200,
                800
        );


        /*
         * Wall.
         */
        g.setColor(
                new Color(48, 50, 58)
        );

        g.fillRect(
                0,
                0,
                1200,
                500
        );


        /*
         * Bricks.
         */
        g.setColor(
                new Color(70, 72, 80)
        );


        for (
                int y = 0;
                y < 500;
                y += 45
        ) {

            int offset =
                    (y / 45 % 2 == 0)
                    ? 0
                    : 45;


            for (
                    int x = -45 + offset;
                    x < 1200;
                    x += 90
            ) {

                g.drawRect(
                        x,
                        y,
                        85,
                        40
                );
            }
        }


        /*
         * Door.
         */
        g.setColor(
                new Color(10, 12, 18)
        );

        g.fillOval(
                470,
                80,
                260,
                380
        );


        g.setColor(
                new Color(100, 95, 90)
        );

        g.setStroke(
                new BasicStroke(7)
        );

        g.drawOval(
                460,
                70,
                280,
                400
        );


        /*
         * Floor.
         */
        g.setColor(
                new Color(65, 50, 45)
        );

        g.fillRect(
                0,
                475,
                1200,
                100
        );


        /*
         * Floor lines.
         */
        g.setColor(
                new Color(95, 70, 60)
        );


        for (
                int x = 0;
                x < 1200;
                x += 75
        ) {

            g.drawLine(
                    x,
                    475,
                    x + 20,
                    575
            );
        }


        /*
         * Torches.
         */
        drawTorch(g, 90, 240);

        drawTorch(g, 1090, 240);

        drawTorch(g, 400, 180);

        drawTorch(g, 800, 180);
    }


    /* =====================================================
       TORCH
       ===================================================== */

    private void drawTorch(
            Graphics2D g,
            int x,
            int y) {

        g.setColor(
                new Color(110, 70, 35)
        );

        g.fillRect(
                x,
                y,
                12,
                55
        );


        g.setColor(
                new Color(255, 130, 20)
        );

        g.fillOval(
                x - 15,
                y - 25,
                42,
                48
        );


        g.setColor(
                new Color(255, 220, 70)
        );

        g.fillOval(
                x - 5,
                y - 15,
                22,
                30
        );
    }


    /* =====================================================
       TOP HUD
       ===================================================== */

    private void drawTopHUD(
            Graphics2D g) {

        /*
         * Main header.
         */
        g.setColor(
                new Color(7, 11, 18)
        );

        g.fillRoundRect(
                15,
                15,
                1170,
                65,
                15,
                15
        );


        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );


        g.drawString(
                "MATH DUNGEON",
                500,
                45
        );


        g.setColor(
                new Color(255, 205, 50)
        );


        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );


        g.drawString(
                "FLOOR "
                + floor
                + "  |  ROOM "
                + room,
                515,
                68
        );


        /*
         * Hero status.
         */
        drawStatusBox(
                g,
                20,
                100,
                350,
                true
        );


        /*
         * Enemy status.
         */
        drawStatusBox(
                g,
                830,
                100,
                350,
                false
        );


        /*
         * XP.
         */
        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        g.drawString(
                "XP "
                + player.getXp()
                + " / "
                + player.getXpNeeded(),
                30,
                265
        );


        /*
         * XP background.
         */
        g.setColor(
                new Color(30, 35, 45)
        );

        g.fillRoundRect(
                30,
                275,
                300,
                12,
                6,
                6
        );


        int xpWidth =
                (int)
                (
                        300
                        *
                        player.getXp()
                        /
                        (double)
                        player.getXpNeeded()
                );


        g.setColor(
                new Color(50, 160, 225)
        );


        g.fillRoundRect(
                30,
                275,
                xpWidth,
                12,
                6,
                6
        );
    }


    /* =====================================================
       STATUS BOX
       ===================================================== */

    private void drawStatusBox(
            Graphics2D g,
            int x,
            int y,
            int width,
            boolean isPlayer) {


        g.setColor(
                new Color(7, 12, 20)
        );


        g.fillRoundRect(
                x,
                y,
                width,
                140,
                15,
                15
        );


        g.setColor(
                new Color(110, 95, 75)
        );


        g.setStroke(
                new BasicStroke(2)
        );


        g.drawRoundRect(
                x,
                y,
                width,
                140,
                15,
                15
        );


        String name;

        int level;

        int hp;

        int maxHp;


        if (isPlayer) {

            name = player.getName();

            level = player.getLevel();

            hp = player.getHp();

            maxHp = player.getMaxHp();

        } else {

            name = enemy.getName();

            level = floor;

            hp = enemy.getHp();

            maxHp = enemy.getMaxHp();
        }


        /*
         * Name.
         */
        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );


        g.drawString(
                name,
                x + 20,
                y + 30
        );


        /*
         * Level.
         */
        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );


        g.drawString(
                "LV "
                + level,
                x + width - 60,
                y + 30
        );


        /*
         * HP background.
         */
        g.setColor(
                new Color(70, 25, 30)
        );


        g.fillRoundRect(
                x + 20,
                y + 48,
                width - 40,
                28,
                8,
                8
        );


        /*
         * HP amount.
         */
        int hpWidth =
                (int)
                (
                        (width - 40)
                        *
                        hp
                        /
                        (double)
                        maxHp
                );


        if (hpWidth > 0) {

            g.setColor(
                    new Color(225, 45, 45)
            );


            g.fillRoundRect(
                    x + 20,
                    y + 48,
                    hpWidth,
                    28,
                    8,
                    8
            );
        }


        /*
         * HP text.
         */
        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );


        g.drawString(
                "HP "
                + hp
                + " / "
                + maxHp,
                x + width / 2 - 45,
                y + 67
        );


        /*
         * Additional information.
         */
        if (isPlayer) {

            g.setColor(
                    new Color(50, 145, 210)
            );


            g.fillRoundRect(
                    x + 20,
                    y + 90,
                    width - 40,
                    25,
                    8,
                    8
            );


            g.setColor(Color.WHITE);


            g.drawString(
                    "ATK "
                    + player.getAttack()
                    + "    DEF "
                    + player.getDefense(),
                    x + 35,
                    y + 108
            );


        } else {

            g.setColor(
                    new Color(255, 195, 60)
            );


            g.drawString(
                    "Enemy",
                    x + 20,
                    y + 108
            );


            if (enemy.isBoss()) {

                g.setColor(
                        new Color(255, 70, 70)
                );


                g.drawString(
                        "BOSS",
                        x + width - 60,
                        y + 108
                );
            }
        }
    }


    /* =====================================================
       CHARACTERS
       ===================================================== */

    private void drawCharacters(
            Graphics2D g) {

        drawHero(
                g,
                heroX + heroMovement,
                350
        );


        drawEnemy(
                g,
                enemyX + enemyMovement,
                350
        );


        if (fanFlying) {

            drawFlyingFan(
                    g,
                    fanX,
                    fanY,
                    fanRotation
            );
        }


        /*
         * Enemy damage number.
         */
        if (showEnemyDamage) {

            g.setColor(Color.RED);

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            40
                    )
            );


            g.drawString(
                    "-"
                    + damageNumber,
                    enemyX - 30,
                    320
            );


            showEnemyDamage = false;
        }


        /*
         * Hero damage number.
         */
        if (showHeroDamage) {

            g.setColor(Color.RED);

            g.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            40
                    )
            );


            g.drawString(
                    "-"
                    + damageNumber,
                    heroX - 25,
                    320
            );


            showHeroDamage = false;
        }
    }


    /* =====================================================
       DRAW HERO
       ===================================================== */

    private void drawHero(
            Graphics2D g,
            int x,
            int y) {


        /*
         * Shadow.
         */
        g.setColor(
                new Color(0, 0, 0, 100)
        );


        g.fillOval(
                x - 55,
                y + 115,
                110,
                25
        );


        /*
         * Cape.
         */
        g.setColor(
                new Color(30, 75, 145)
        );


        int[] capeX = {

            x - 25,
            x - 70,
            x - 20
        };


        int[] capeY = {

            y + 5,
            y + 105,
            y + 110
        };


        g.fillPolygon(
                capeX,
                capeY,
                3
        );


        /*
         * Body.
         */
        g.setColor(
                new Color(70, 90, 115)
        );


        g.fillRoundRect(
                x - 25,
                y + 25,
                50,
                75,
                15,
                15
        );


        /*
         * Head.
         */
        g.setColor(
                new Color(220, 165, 120)
        );


        g.fillOval(
                x - 23,
                y - 25,
                46,
                50
        );


        /*
         * Hair.
         */
        g.setColor(
                new Color(30, 25, 25)
        );


        g.fillArc(
                x - 27,
                y - 35,
                54,
                45,
                0,
                180
        );


        /*
         * Legs.
         */
        g.setColor(
                new Color(35, 40, 50)
        );


        g.fillRect(
                x - 17,
                y + 90,
                15,
                35
        );


        g.fillRect(
                x + 5,
                y + 90,
                15,
                35
        );


        /*
         * Sword.
         */
        g.setColor(
                new Color(220, 225, 235)
        );


        g.setStroke(
                new BasicStroke(6)
        );


        g.drawLine(
                x + 20,
                y + 50,
                x + 95,
                y + 5
        );


        /*
         * Sword handle.
         */
        g.setColor(
                new Color(130, 80, 40)
        );


        g.setStroke(
                new BasicStroke(8)
        );


        g.drawLine(
                x + 20,
                y + 50,
                x - 5,
                y + 70
        );


        /*
         * Eyes.
         */
        g.setColor(Color.BLACK);


        g.fillOval(
                x - 10,
                y - 5,
                5,
                6
        );


        g.fillOval(
                x + 8,
                y - 5,
                5,
                6
        );
    }


    /* =====================================================
       DRAW ENEMY
       ===================================================== */

    private void drawEnemy(
            Graphics2D g,
            int x,
            int y) {


        /*
         * Choose drawing based on
         * the actual Enemy subclass.
         *
         * This is another simple example
         * of polymorphism.
         */

        if (enemy instanceof Goblin) {

            drawGoblin(g, x, y);

        } else if (enemy instanceof Skeleton) {

            drawSkeleton(g, x, y);

        } else if (enemy instanceof OrcShaman) {

            drawOrcShaman(g, x, y);

        } else if (enemy instanceof Orc) {

            drawOrc(g, x, y);

        } else if (enemy instanceof DarkKnight) {

            drawDarkKnight(g, x, y);

        } else if (enemy instanceof MathDragon) {

            drawDragon(g, x, y);
        }
    }


    /* =====================================================
       GOBLIN DRAWING
       ===================================================== */

    private void drawGoblin(
            Graphics2D g,
            int x,
            int y) {


        /*
         * Body.
         */
        g.setColor(
                new Color(70, 145, 65)
        );


        g.fillRoundRect(
                x - 30,
                y + 25,
                60,
                80,
                15,
                15
        );


        /*
         * Head.
         */
        g.fillOval(
                x - 35,
                y - 25,
                70,
                65
        );


        /*
         * Ears.
         */
        int[] leftEarX = {

            x - 25,
            x - 65,
            x - 25
        };


        int[] leftEarY = {

            y - 5,
            y - 30,
            y + 15
        };


        g.fillPolygon(
                leftEarX,
                leftEarY,
                3
        );


        int[] rightEarX = {

            x + 25,
            x + 65,
            x + 25
        };


        int[] rightEarY = {

            y - 5,
            y - 30,
            y + 15
        };


        g.fillPolygon(
                rightEarX,
                rightEarY,
                3
        );


        /*
         * Eyes.
         */
        drawEnemyEyes(g, x, y);


        /*
         * Weapon.
         */
        g.setColor(
                new Color(140, 90, 45)
        );


        g.setStroke(
                new BasicStroke(8)
        );


        g.drawLine(
                x + 20,
                y + 55,
                x + 75,
                y + 10
        );
    }


    /* =====================================================
       SKELETON
       ===================================================== */

    private void drawSkeleton(
            Graphics2D g,
            int x,
            int y) {


        g.setColor(
                new Color(185, 185, 180)
        );


        g.fillRoundRect(
                x - 28,
                y + 25,
                56,
                80,
                15,
                15
        );


        g.fillOval(
                x - 34,
                y - 25,
                68,
                65
        );


        /*
         * Eyes.
         */
        g.setColor(Color.BLACK);


        g.fillOval(
                x - 18,
                y - 5,
                12,
                12
        );


        g.fillOval(
                x + 6,
                y - 5,
                12,
                12
        );


        /*
         * Bones.
         */
        g.setColor(
                new Color(80, 80, 80)
        );


        g.setStroke(
                new BasicStroke(5)
        );


        g.drawLine(
                x - 18,
                y + 50,
                x + 18,
                y + 85
        );


        g.drawLine(
                x + 18,
                y + 50,
                x - 18,
                y + 85
        );
    }


    /* =====================================================
       ORC
       ===================================================== */

    private void drawOrc(
            Graphics2D g,
            int x,
            int y) {


        g.setColor(
                new Color(75, 120, 60)
        );


        g.fillRoundRect(
                x - 40,
                y + 20,
                80,
                95,
                15,
                15
        );


        g.fillOval(
                x - 45,
                y - 35,
                90,
                75
        );


        drawEnemyEyes(
                g,
                x,
                y
        );


        /*
         * Club.
         */
        g.setColor(
                new Color(135, 85, 45)
        );


        g.setStroke(
                new BasicStroke(10)
        );


        g.drawLine(
                x + 20,
                y + 70,
                x + 85,
                y + 5
        );
    }


    /* =====================================================
       ORC SHAMAN
       ===================================================== */

    private void drawOrcShaman(
            Graphics2D g,
            int x,
            int y) {


        drawOrc(
                g,
                x,
                y
        );


        /*
         * Magic staff.
         */
        g.setColor(
                new Color(120, 75, 40)
        );


        g.setStroke(
                new BasicStroke(7)
        );


        g.drawLine(
                x - 70,
                y - 10,
                x - 70,
                y + 115
        );


        /*
         * Magic orb.
         */
        g.setColor(
                new Color(80, 160, 255)
        );


        g.fillOval(
                x - 85,
                y - 35,
                30,
                30
        );
    }


    /* =====================================================
       DARK KNIGHT
       ===================================================== */

    private void drawDarkKnight(
            Graphics2D g,
            int x,
            int y) {


        /*
         * Armor.
         */
        g.setColor(
                new Color(55, 60, 72)
        );


        g.fillRoundRect(
                x - 35,
                y + 15,
                70,
                105,
                12,
                12
        );


        /*
         * Helmet.
         */
        g.setColor(
                new Color(35, 38, 48)
        );


        g.fillOval(
                x - 42,
                y - 35,
                84,
                75
        );


        /*
         * Red eyes.
         */
        g.setColor(Color.RED);


        g.fillOval(
                x - 20,
                y - 5,
                12,
                8
        );


        g.fillOval(
                x + 8,
                y - 5,
                12,
                8
        );


        /*
         * Sword.
         */
        g.setColor(
                new Color(190, 195, 205)
        );


        g.setStroke(
                new BasicStroke(9)
        );


        g.drawLine(
                x - 20,
                y + 55,
                x - 85,
                y - 10
        );
    }


    /* =====================================================
       MATH DRAGON
       ===================================================== */

    private void drawDragon(
            Graphics2D g,
            int x,
            int y) {


        /*
         * Wings.
         */
        g.setColor(
                new Color(95, 40, 115)
        );


        int[] leftWingX = {

            x - 30,
            x - 130,
            x - 65
        };


        int[] leftWingY = {

            y + 25,
            y - 40,
            y + 95
        };


        g.fillPolygon(
                leftWingX,
                leftWingY,
                3
        );


        int[] rightWingX = {

            x + 30,
            x + 130,
            x + 65
        };


        int[] rightWingY = {

            y + 25,
            y - 40,
            y + 95
        };


        g.fillPolygon(
                rightWingX,
                rightWingY,
                3
        );


        /*
         * Dragon body.
         */
        g.setColor(
                new Color(120, 45, 130)
        );


        g.fillOval(
                x - 55,
                y + 10,
                110,
                120
        );


        /*
         * Dragon head.
         */
        g.fillOval(
                x - 55,
                y - 45,
                110,
                85
        );


        /*
         * Horns.
         */
        g.setColor(
                new Color(220, 195, 140)
        );


        int[] horn1X = {

            x - 35,
            x - 65,
            x - 15
        };


        int[] horn1Y = {

            y - 30,
            y - 75,
            y - 20
        };


        g.fillPolygon(
                horn1X,
                horn1Y,
                3
        );


        int[] horn2X = {

            x + 35,
            x + 65,
            x + 15
        };


        int[] horn2Y = {

            y - 30,
            y - 75,
            y - 20
        };


        g.fillPolygon(
                horn2X,
                horn2Y,
                3
        );


        /*
         * Eyes.
         */
        g.setColor(Color.RED);


        g.fillOval(
                x - 30,
                y - 10,
                18,
                12
        );


        g.fillOval(
                x + 12,
                y - 10,
                18,
                12
        );


        /*
         * Fire.
         */
        g.setColor(
                new Color(255, 130, 20)
        );


        g.fillOval(
                x - 20,
                y + 70,
                40,
                45
        );


        g.setColor(
                new Color(255, 220, 70)
        );


        g.fillOval(
                x - 10,
                y + 75,
                20,
                30
        );
    }


    /* =====================================================
       ENEMY EYES
       ===================================================== */

    private void drawEnemyEyes(
            Graphics2D g,
            int x,
            int y) {


        g.setColor(Color.RED);


        g.fillOval(
                x - 20,
                y - 3,
                10,
                10
        );


        g.fillOval(
                x + 10,
                y - 3,
                10,
                10
        );
    }


    /* =====================================================
       QUESTION BOX
       ===================================================== */

    private void drawQuestionBox(
            Graphics2D g) {


        g.setColor(
                new Color(6, 13, 22)
        );


        g.fillRoundRect(
                270,
                510,
                660,
                105,
                18,
                18
        );


        g.setColor(
                new Color(255, 205, 50)
        );


        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );


        g.drawString(
                "SOLVE THE PROBLEM TO ATTACK!",
                450,
                540
        );


        g.setColor(Color.WHITE);


        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );


        g.drawString(
                mathQuestion.getQuestion(),
                520,
                590
        );
    }


    /* =====================================================
       SIDE PANELS
       ===================================================== */

    private void drawSidePanels(
            Graphics2D g) {


        /*
         * Player information.
         */
        g.setColor(
                new Color(7, 13, 22)
        );


        g.fillRoundRect(
                15,
                510,
                235,
                180,
                15,
                15
        );


        g.setColor(
                new Color(255, 205, 50)
        );


        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );


        g.drawString(
                "PLAYER STATS",
                40,
                540
        );


        g.setColor(Color.WHITE);


        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        g.drawString(
                "Attack: "
                + player.getAttack(),
                30,
                570
        );


        g.drawString(
                "Defense: "
                + player.getDefense(),
                30,
                595
        );


        g.drawString(
                "Level: "
                + player.getLevel(),
                30,
                620
        );


        g.drawString(
                "Gold: "
                + player.getGold(),
                30,
                645
        );


        g.drawString(
                "Combo: x"
                + player.getCombo(),
                30,
                670
        );


        /*
         * Enemy progression.
         */
        g.setColor(
                new Color(7, 13, 22)
        );


        g.fillRoundRect(
                950,
                290,
                230,
                205,
                15,
                15
        );


        g.setColor(
                new Color(255, 205, 50)
        );


        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );


        g.drawString(
                "ENEMY PROGRESSION",
                965,
                320
        );


        g.setColor(Color.WHITE);


        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        g.drawString(
                "1. William",
                970,
                350
        );


        g.drawString(
                "2. Vince",
                970,
                375
        );


        g.drawString(
                "3. Karl",
                970,
                400
        );


        g.drawString(
                "4. Patrick",
                970,
                425
        );


        g.drawString(
                "5. Jaypee",
                970,
                450
        );


        g.drawString(
                "6. Kim",
                970,
                475
        );
    }


    /* =====================================================
       BATTLE LOG
       ===================================================== */

    private void drawBattleLog(
            Graphics2D g) {


        g.setColor(
                new Color(7, 12, 20)
        );


        g.fillRoundRect(
                15,
                710,
                1165,
                70,
                12,
                12
        );


        g.setColor(
                new Color(255, 205, 50)
        );


        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );


        g.drawString(
                "BATTLE LOG",
                30,
                735
        );


        g.setColor(Color.WHITE);


        g.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );


        g.drawString(
                battleMessage,
                30,
                760
        );


        /*
         * Potion drop message.
         */
        if (!potionMessage.isEmpty()) {

            g.setColor(
                    new Color(80, 255, 100)
            );


            g.drawString(
                    potionMessage,
                    450,
                    760
            );
        }

        /*
         * Kill counter.
         */
        g.setColor(
                new Color(255, 205, 50)
        );


        g.drawString(
                "Enemies Defeated: "
                + enemiesDefeated,
                930,
                735
        );
    }
}