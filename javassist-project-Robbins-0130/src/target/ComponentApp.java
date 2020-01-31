package target;

public class ComponentApp {
	public static void main(String[] args) throws Exception{
		System.out.println("[CA] Run...");
		ComponentApp localMyApp = new ComponentApp();
		localMyApp.runComponent();
		System.out.println(Class.forName("target." + args[0]).getField(args[1]).getName());
		System.out.println(Class.forName("target." + args[0]).getField(args[1]));
		System.out.println("Done.");
	}
	
	public void runComponent() {
		System.out.println("[CA] Called runComponent.");
	}
}
