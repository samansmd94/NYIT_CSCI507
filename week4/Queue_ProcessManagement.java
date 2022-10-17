/**

@Group no : Group 2 (Hao, Sam)
@university : NYIT (cybersecurity)
@Student ID : 1313045, 1314389

*/

package week4;

import week4.OperationSystem;
import week4.Process;
import week4.Process.Priority;

public class Queue_ProcessManagement{

    public static void main(String[] args) throws InterruptedException {

        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long t1 = System.nanoTime();

        OperationSystem operationSystem = new OperationSystem();

        operationSystem.run();

        long time_total = System.nanoTime() - t1;
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;

        System.out.println("\nMemory consumtion(MB):" + String.format("%.2f", actualMemUsed/(1024.0 * 1024.0)) + " Time cost(ns):"+time_total + " (ms):"+time_total/1000000000.0);
    }


}




