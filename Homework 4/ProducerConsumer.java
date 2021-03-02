//Homework #4
//Morgan Maness
//010813680
import java.util.concurrent.Semaphore;
import java.util.*;
import java.io.*;

class Data
{
  public static Object LOCK = new Object();
  public static LinkedList list = new LinkedList();
  public static int capacity = 0;
  public static Semaphore full = new Semaphore(0);
  public static Semaphore empty = new Semaphore(5);
  public static Semaphore mutex = new Semaphore(1);
  public static long start = System.currentTimeMillis();
  public static long end = start + 5*1000;
}

class Consumer extends Thread{
  public void run(){
    Random rand = new Random();
    try{
      while(Data.full.tryAcquire() == false )
        Thread.sleep(100);
      while( true  && Data.capacity > 0){
        int randTime = rand.nextInt(500);
        Data.mutex.acquire();
        System.out.println("\t Consumer consumed " + Data.list.removeFirst());
        Data.capacity--;
        Data.empty.release();
        Data.mutex.release();
        Thread.sleep(randTime);
      }
    }
    catch (Exception x){
      x.printStackTrace();
    }
  }
}

class Producer extends Thread{
  public void run(){
    Random rand = new Random();
    try{
      int N = 0;
      while(Data.empty.tryAcquire() == false )
        Thread.sleep(100);
      while(true && Data.capacity < 5){
        int randTime = rand.nextInt(500);
        N = rand.nextInt(8999) + 1000;
        Data.mutex.acquire();
        Data.list.add(N);
        System.out.println("Producer produces " + N);
        Data.capacity ++;
        Data.full.release();
        Data.mutex.release();
        Thread.sleep(randTime);
      }
    }
    catch (Exception x){
      x.printStackTrace();
    }
  }
}

public class ProducerConsumer{
  public static void main(String[] args)
  {
    int sleep = Integer.parseInt(args[0]);
    int produce = Integer.parseInt(args[1]);
    int consume = Integer.parseInt(args[2]);
    System.out.println("Using arguments from command line");
    System.out.println("Sleep time = " + sleep);
    System.out.println("Producer threads = " + produce);
    System.out.println("Consumer threads = " + consume);
    for(int i = 0; i < produce; i++)
    {
      new Producer().start();
    }
    for (int i = 0; i < consume; i++)
    {
      new Consumer().start();
    }
  }
}
