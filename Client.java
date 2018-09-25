import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Client {

	private Client() {
	}

	public static void main(String[] args) {

		String host = (args.length < 1) ? null : args[0];
		System.out.println("Endereco do servidor: " + host);
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			// Lookup busca quem tem a interface Eleicao em Registry. No Registry, existe uma
			// tupla que
			// armazena o stub e a sua label
			Eleicao stub = (Eleicao) registry.lookup("Eleicao");
			// Chama o Eleicao no servidor
            String response = stub.sayHello();

			System.out.println("response: " + response);



            votar(int nCandidato, int nVotante);

            resultadoParcial(int nCandidato);

            resultado();


		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}