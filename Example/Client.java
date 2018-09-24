
//package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	private Client() {
	}

	public static void main(String[] args) {

		String host = (args.length < 1) ? null : args[0];
		System.out.println("Endereco do servidor: " + host);
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			// Lookup busca quem tem a interface Hello em Registry. No Registry, existe uma
			// tupla que
			// armazena o stub e a sua label
			Hello stub = (Hello) registry.lookup("Hello");
			// Chama o Hello no servidor
			String response = stub.sayHello();
			System.out.println("response: " + response);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
