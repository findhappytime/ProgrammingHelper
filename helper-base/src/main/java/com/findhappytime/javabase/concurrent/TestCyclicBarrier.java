package com.findhappytime.javabase.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//�����������Ǹ���4��ȥHappy���ͻ���֪ͨ��һ�£����ϰ˵�׼ʱ��xx�ư���ǰ���ϣ�������ɢ��
//�и�����ס�Ľ�������͵��ˡ��е�����æ���պòȵ㵽�ˡ����������������Ķ����ܶ����ж���ֻ�ܵȴ������� 

//�ܽ᣺CyclicBarrier����һ��դ�����ȴ������̵߳������ִ����صĲ�����barrier ���ͷŵȴ��̺߳�������á� 
public class TestCyclicBarrier {

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        final Random random = new Random();

        final CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("��Ҷ������ˣ���ʼhappyȥ");
            }
        });

        for (int i = 0; i < 4; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "���ˣ�����������");
                    try {
                        barrier.await();//�ȴ���������  
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        exec.shutdown();
    }

}  