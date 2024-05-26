package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import compute.Compute;

public class ComputeE {
	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry(args[0]);
			String name = "Compute";
			Compute comp = (Compute) registry.lookup(name);
			E task = new E(Double.parseDouble(args[1]), Double.parseDouble(args[2]));
			BigDecimal e = comp.executeTask(task);
			System.out.println(e);
		} catch (Exception e) {
			System.err.println("ComputeE exception:");
			e.printStackTrace();
		}
	}
}