import java.util.ArrayList;
import java.util.Random;

public class Process {
    public String Name;
    public int Time; //в миллисекундах
    public ArrayList<Thread> listThreads = new ArrayList<>();

    public Process(int number) {
        Name = "Process №" + number;
        Random rnd = new Random();
        int time;
        for(int i = 0; i < rnd.nextInt(2,10); i++){
            time = rnd.nextInt(1,8) * 10;
            Time += time;
            listThreads.add(new Thread(i + 1,time));
        }
    }

    public boolean Check(){
        for(Thread thread : listThreads){
            if(thread.Time != 0) return false;
        }
        return true;
    }

}
