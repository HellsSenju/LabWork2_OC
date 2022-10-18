import java.util.Random;

public class Thread {
    public String Name;
    public int Time; //в миллисекундах

    public Thread(int number, int time){
        Name = "      Thread #" + number;
        Time = time;
    }

    public void DoWork(int ostTime){
        System.out.print(Name + " [" + Time + "] ms" + ":   ");
        if(ostTime - Time >= 0){
            int time = Time;
            while(time > 0){
                System.out.print("--");
                time -= 10;
            }
            System.out.println();
            Time = 0;
        }
        else{
            int time = Time - ostTime;
            while(time > 0){
                System.out.print("--");
                time -= 10;
            }
            System.out.println();
            Time = Time - ostTime;
        }
    }
}
