/**
 * Implementation of the queue where Tasks are processed
 */

public class TaskQueue {    
    /** queue of tasks, the task in front of the queue is being processed */
    private QueueInterface<Task> Q;
    /** current timer of the class */
    private int Time;
    /** time when the task in front of the queue needs to be removed,
     * as the transaction is complete */
    private int dequeueTime;
    /** number of tasks processed */
    private int tasks;
    /** total wait time. (for a task, the wait time is the time
     * difference between the moment the task entered the queue and
     * the time when it starts) */
    private int totalWaitTime;
    
    /** Constructor */
    public TaskQueue(){
	Time = 0;
	dequeueTime =-1;
	tasks = 0;
	totalWaitTime =0;
	Q = new QueueReferenceBased<Task>();
    }

    /** get the current time */
    public int getTime(){
	return Time;
    }

    /** add a task to the queue 
     * @param newTask new task to be handled by the queue.
     */
    public void add(Task newTask){
	tasks++;
	System.out.println(Time+ ": Task " + newTask.id 
			   + " enqueued (transaction time="
			   + newTask.transactionTime+")");
	// * TO COMPLETE *
	// enqueue the newTask
	// if the new task can start immediately, update the dequeue time 
    Q.enqueue(newTask);
    if (dequeueTime < Time) {
        dequeueTime = Time + newTask.transactionTime;
    }
    }
    
    /** get the number of tasks that have been present in the queue
     * @return number of tasks that entered the queue
     */
    public int getNumTasks(){
	return tasks;
    }
    /** get the current average wait time, i.e. the average time that
     * a task waited between the moment it entered the queue and the
     * moment when it starts to be processed
     * @return the average waiting time
     */
    public double getAvgWaitTime(){
	return (double) totalWaitTime/tasks;
    }

    /** tells whether the queue completed all the current task
     * @return return true when all the current tasks have been
     * performed (i.e. the queue is empty; false otherwise (i.e. the
     * queue is not empty) 
     */
    public boolean isComplete(){
	return Q.isEmpty();
    }
    /** manage the queue. When needed
     * <li> remove a completed task from the queue
     * <li> starts a new task
     * <li> process the current task
     */
    public void process() {
	System.out.print(Time + ": ");
	if (Q.isEmpty())
	    System.out.print("idle");
	if (Time==dequeueTime) {
        System.out.println(Q.peek().id + " dequeued");
	    // * TO COMPLETE *
	    // Remove task from queue
        Q.dequeue();
	    if (!Q.isEmpty()) {
            System.out.println(Q.peek().id + " started processing");
		// * TO COMPLETE *
		// Set dequeueTime by looking at the (but not removing) task 
		// at the front of the queue
		// Set the startTime of this task to Time
		// Increment total wait time by the wait time for this task
        Q.peek().startTime = Time;
        dequeueTime = Q.peek().startTime + Q.peek().transactionTime;
        totalWaitTime = totalWaitTime + (Q.peek().startTime - Q.peek().arrivalTime);
	    }
	}
	else if (!Q.isEmpty())
	    System.out.print(Q.peek().id+" processing");
	System.out.println();
	Time++;
    }// end process
    
}