public class Thread {
    public String Name;
    public int Time; //в миллисекундах
    public String length;
    public Thread(int number, int time){
        Name = "      Thread #" + number;
        Time = time;
    }
    public void DoWork(int ostTime){
        length = "";
        System.out.println();
        System.out.print(Name + " [" + Time + "]" + ": ");

        for(int i = 0; i < Scheduler.allLength.length(); i++){
            System.out.print(" ");
        }
        while(ostTime > 0){
            if(Time == 0) break;
            length += "-";
            Time -= 10;
            ostTime -= 10;
        }
        Scheduler.allLength += length;
        System.out.print(length);
    }
}
