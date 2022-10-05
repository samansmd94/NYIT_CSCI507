package week4;

import java.util.ArrayList;

import week4.Process.Priority;

public class OperationSystem {

    static int CPUTimeSlice = 1; // For simplicity, define the cpu time slice in units of 1
    ArrayList<Process> processes = new ArrayList<Process>();
    ArrayPriorityQueue<Process> readyQueue = new ArrayPriorityQueue<Process>();
    Process workingProcess = null;
    ArrayList<String> executionFlow = new ArrayList<String>();

    public OperationSystem(){

        Process process1 = new Process("p1", 0, 10, Priority.Medium);
        Process process2 = new Process("p2", 2, 6, Priority.High);
        Process process3 = new Process("p3", 6, 5, Priority.ExtraLow);
        Process process4 = new Process("p4", 10, 6, Priority.ExtraHigh);
        Process process5 = new Process("p5", 12, 3, Priority.Low);


        processes.add(process1);
        processes.add(process2);
        processes.add(process3);
        processes.add(process4);
        processes.add(process5);

        System.out.println("There are 5 processes to execute");
        for (Process p : processes) {
            p.show();
        }
    }

    public boolean IsStillWorking(){
        boolean ret = false;

        for (Process p : processes) {
            if(p.IsStillWorking()){
                ret = true;
                break;
            }
        }

        return ret;
    }

    public ArrayList<Process> getArrivedProcesses(int currentCpuTime){
        ArrayList<Process> arrivedProcesses = new ArrayList<Process>();

        for (Process p : processes) {
            if(p.IsStillWorking() && p.IsArrived(currentCpuTime)){
                arrivedProcesses.add(p);
                break;
            }
        }

        return arrivedProcesses;
    }

    public void run() throws InterruptedException{

        int cpuTime = 0;
        while(IsStillWorking()){

            System.out.println("\n------ cpu time slice "+cpuTime+" has started");

            ArrayList<Process> arrivedProcesses = getArrivedProcesses(cpuTime);

            for (Process process : arrivedProcesses) {
                readyQueue.enqueue(process);
                System.out.println(Color.RED_BRIGHT + "Process " + process.GetName() + " entered into the ready queue"+Color.STYLE_RESET_ALL);
            }

            System.out.print("now there are " + readyQueue.activeSize() + " active process:");
            ArrayList<Process> readyBuffer = readyQueue.getActiviedBuffer();
            for (Process p : readyBuffer) {
                if(p.IsStillWorking()){
                    System.out.print(p.GetName()+"("+p.GetPriority()+":"+p.GetPriority().value()+") ");
                }
            }
            System.out.println();

            Process processHighPriority = readyQueue.max();
            processHighPriority.GetCpuSeconds(CPUTimeSlice);
            executionFlow.add(processHighPriority.GetName()+"("+processHighPriority.GetPriority().value()+")");
            if(!processHighPriority.IsStillWorking()){
                readyQueue.invalidMaxE();
            }

            cpuTime++;
        }

        System.out.println("flow:");
        for (String f : executionFlow) {
            System.out.print(f+"->");
        }
        System.out.println();

    }

}