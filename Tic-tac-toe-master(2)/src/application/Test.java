package application;

public class Test {

  public static void main(String[] args) {
    ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
    int total  = Thread.activeCount();
    Thread[] threads = new Thread[total];
    threadGroup.enumerate(threads);
    for (Thread t:threads) {
      System.out.println("线程"+t.getId()+" "+t.getName());
    }
  }
}
