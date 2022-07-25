package poo.lambda;

public class Task {
	public Task() {
		System.out.println("Task invoked...");
	}//Task
	
	public static void main(String[] args) {
		Thread t=new Thread( Task::new );
		t.run();
	}//main
}//Task
