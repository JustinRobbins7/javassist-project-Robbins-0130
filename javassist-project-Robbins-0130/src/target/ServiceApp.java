package target;

public class ServiceApp {
	public static void main(String[] args) throws Exception{
		System.out.println("[SA] Run...");
		ServiceApp localMyApp = new ServiceApp();
		localMyApp.runService();
		System.out.println(Class.forName("target." + args[0]).getField(args[1]).getName());
		System.out.println(Class.forName("target." + args[0]).getField(args[1]));
		System.out.println("Done.");
	}
	
	public void runService() {
		System.out.println("[SA] Called runService.");
	}
}