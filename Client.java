import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Client {

	private Client() {
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a;
		int opt = 1, nCandidato = 0, nVotante = 0;

		String host = (args.length < 1) ? null : args[0];
		System.out.println("Endereco do servidor: " + host);
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			// Lookup busca quem tem a interface Eleicao em Registry.
			// No Registry, existe uma tupla que
			// armazena o stub e a sua label
			Eleicao stub = (Eleicao) registry.lookup("Eleicao");
			// Chama o Eleicao no servidor
			String response = stub.sayHello();

			System.out.println("response: " + response);

			while (opt != 0) {
				System.out.println("Selecione uma opção:\n");
				System.out.println("1 - Votar num candidato\n");
				System.out.println("2 - Ver o resultado de um candidato\n");
				System.out.println("3 - Ver o resultado da eleição\n");
				opt = s.nextInt();

				switch (opt) {
				case 1:
					System.out.println("Insira o numero do candidato:");
					nCandidato = s.nextInt();
					System.out.println("Agora insira o seu numero:");
					nVotante = s.nextInt();

					a = stub.votar(nCandidato, nVotante);

					if (a == 1) {
						System.out.println("VOTAÇÃO CONCLUÍDA!");
					} else {
						System.out.println("ERRO NA VOTAÇÃO!");
					}
					break;

				case 2:
					System.out.println("Insira o numero do candidato:");
					nCandidato = s.nextInt();

					a = stub.resultadoParcial(nCandidato);

					if (a == 1) {
						System.out.println("OPERAÇÃO CONCLUÍDA COM SUCESSO!");
					} else {
						System.out.println("ERRO NA VOTAÇÃO!");
					}

					break;

				case 3:
					System.out.println("COMPUTANDO RESULTADO...");
					stub.resultado();

					break;

				default:
					System.out.println("ELEIÇÃO CONCLUÍDA!");
					break;
				}
			}

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}