import java.util.Random;

public class Scheduler {
    public static int Quant = 50; //в миллисекундах
    public Queue queue;

    public Scheduler(){
        Random rnd = new Random();
        int size = rnd.nextInt(2,5); //определяется кол-во процессов
        queue = new Queue(size);
        for(int i = 0; i < size; i++){
            queue.Add(new Process(i + 1)); //процессы добавляются в циклическую очередь
        }
    }

    public void Print(){
        Process tempProcess;
        for(int i = 0; i < queue.queue.length; i++){
            tempProcess = queue.Remove();
            System.out.println(tempProcess.Name + " [" + tempProcess.Time + "]ms" + ":  ");
            for(Thread thread: tempProcess.listThreads){
                System.out.println(thread.Name + " [" + thread.Time + "]ms" + ":  ");
            }
            queue.Add(tempProcess);
        }
        System.out.println();
        System.out.println();
    }

    public void DoWork()
    {
        Process tempProcess;
        int workTime = 0;
        while(!queue.isEmpty){
            workTime = 0;
            tempProcess = queue.Remove();//берем из очереди первый
            System.out.println(tempProcess.Name + " [" + tempProcess.Time + "] ms" + ":  ");

            for(int i = 0; i < tempProcess.listThreads.size(); i++) //идем по его потокам
            {
                if(tempProcess.listThreads.get(i).Time == 0) continue;
                tempProcess.listThreads.get(i).DoWork(Quant - workTime); //выполняем поток(насколько возможно)
                workTime += tempProcess.listThreads.get(i).Time; //прибавляем время работы потока к общему времени выполнения процесса на данный момент
                if(workTime == Quant)break; //если времени на выполненние потоков процесса больше не осталось - прерываем
            }
            if(!tempProcess.Check()){
                queue.Add(tempProcess);
            }
        }
    }
}
