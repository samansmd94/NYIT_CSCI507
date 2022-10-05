package week4;


public class Process implements Comparable<Process>{

    enum Priority{
        ExtraLow(1), Low(2), Medium(3), High(4), ExtraHigh(5);

        private int value = 0;

        private Priority(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    private String name;
    private Priority priority;

    private int arrivedTime;

    private int totalCpuSecondsNeed;
    private int restCpuSeconds;

    public Process(String pName, int pArrivedTime, int ptotalCpuSecondsNeed, Priority pPriority){
        name = pName;
        totalCpuSecondsNeed = ptotalCpuSecondsNeed;
        restCpuSeconds = ptotalCpuSecondsNeed;
        priority = pPriority;
        arrivedTime = pArrivedTime;
    }

    public void GetCpuSeconds(int cpuSeconds){
        restCpuSeconds = restCpuSeconds - cpuSeconds;
        if(restCpuSeconds > 0){
            System.out.println(name + " got " + cpuSeconds + " cpu slice, still need " + restCpuSeconds);
        } else {
            System.out.println(Color.RED_BRIGHT + name + " got " + cpuSeconds + " cpus slice, done" + Color.STYLE_RESET_ALL);
        }
    }

    public boolean IsStillWorking(){
        return restCpuSeconds > 0;
    }

    public boolean IsArrived(int currentTime){
        return currentTime == arrivedTime;
    }

    public Priority GetPriority(){
        return priority;
    }

    public String GetName(){
        return name;
    }

    @Override
    public int compareTo(Process o) {
        
        if(priority.value() > o.GetPriority().value()){
            return 1;
        } else if(priority.value() == o.GetPriority().value()){
            return 0;
        } else {
            return -1;
        }
    }

    public void show(){
        System.out.println(name + " arrive time:"+arrivedTime + 
        " cpu time slice needed:"+totalCpuSecondsNeed + " priority:"+priority);
    }
}
