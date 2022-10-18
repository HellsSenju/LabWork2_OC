public class Queue {
    public Process[] queue;
    private int first;
    private int last;

    public boolean isEmpty = false;
    public Queue(int size){
        queue = new Process[size];
        first = -1;
        last = -1;
    }

    public void Add(Process process){
        if(last == -1){
            last++;
            queue[last] = process;
            first = 0;
        }
        else if(last == queue.length - 1){
            last = 0;
            queue[0] = process;
        }
        else queue[++last] = process;
    }

    public Process Remove(){
        if(last == first) isEmpty = true;

        if(first == queue.length - 1){
            first = 0;
            return queue[queue.length - 1];
        }
        else {
            first++;
            return queue[first - 1];
        }
    }
}
