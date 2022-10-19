import java.util.ArrayDeque;
import java.util.Random;

public class Scheduler {
    public static int Quant = 50; //в миллисекундах
    //public Queue queue;

    public ArrayDeque<Process> queue;

    public static String  allLength = "";

    public Scheduler(){
        Random rnd = new Random();
        int size = rnd.nextInt(2,5); //определяется кол-во процессов
        //queue = new Queue(size);
        queue = new ArrayDeque<>();
        for(int i = 0; i < size; i++){
            queue.addLast(new Process(i + 1)); //процессы добавляются в циклическую очередь
        }
    }

    public void Print(){
        Process tempProcess;
        for(int i = 0; i < queue.size(); i++){
            tempProcess = queue.pop();
            System.out.println(tempProcess.Name + " [" + tempProcess.Time + "]ms" + ":  ");
            for(Thread thread: tempProcess.listThreads){
                System.out.println(thread.Name + " [" + thread.Time + "]ms" + ":  ");
            }
            queue.addLast(tempProcess);
        }
        System.out.println("*****************************************************");
        System.out.println();
    }

    public void DoWork()
    {
        Process tempProcess;
        int ostTime, threadTime;
        while(!queue.isEmpty()){
            ostTime = Quant;
            tempProcess = queue.pop(); //берем из очереди первый
            System.out.println();
            System.out.print(tempProcess.Name + " [" + tempProcess.Time + "]ms" + ":  ");

            for(int i = 0; i < tempProcess.listThreads.size(); i++) //идем по его потокам
            {
                if(tempProcess.listThreads.get(i).Time == 0) continue; //если текущий поток уже выполнен, то переходим на следующий поток
                threadTime = tempProcess.listThreads.get(i).Time;
                tempProcess.listThreads.get(i).DoWork(ostTime); //выполняем поток(насколько возможно)
                ostTime -= threadTime;
            }
            if(!tempProcess.Check()){
                queue.addLast(tempProcess);
            }
            System.out.println();
        }
    }
}
