package com.class03;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * 按阶段执行线程，如某个阶段只允许某些线程通过
 * 可随时注册，取消注册
 * 功能与CountDownLatch和CyclicBarrier相似
 * 结婚：宾客到达、吃饭、离开，拥抱（新郎新娘才可以）
 */
public class PhaserTest {
    static Random r = new Random();
    static MarryPhaser phaser = new MarryPhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i <5 ; i++) {
            new Thread(new person("p"+i)).start();
        }
        new Thread(new person("新娘")).start();
        new Thread(new person("新郎")).start();
    }
    static class MarryPhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch(phase){
                case 0:
                    System.out.println("所有人到齐了！" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了！" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了！" + registeredParties);
                    System.out.println();
                    return false;
                case 3 :
                    System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
                    System.out.println();
                    return true;
                default : return true;
            }
        }
    }
    static class person implements Runnable{
        String name;

        public person(String name) {
            this.name = name;
        }

        private void arrive(){
            try {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 到达现场！\n", name);
                phaser.arriveAndAwaitAdvance();
        }
        private void eat(){
            try {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 吃完！\n", name);
            phaser.arriveAndAwaitAdvance();

        }
        private void leave(){
            try {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 离开！\n", name);
            phaser.arriveAndAwaitAdvance();

        }
        private void hug(){
            if (name.equals("新娘")||name.equals("新郎")){
                try {
                    Thread.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s 拥抱！\n", name);
                phaser.arriveAndAwaitAdvance();
                //其他宾客到达但不注册
            }else  phaser.arriveAndDeregister();

        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

}
