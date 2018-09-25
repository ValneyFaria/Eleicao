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
				System.out.println("0 - Finalizar");

				opt = s.nextInt();

				switch (opt) {

				case 0:
					opt = 0;
					break;
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

					System.out.printf("O candidato %d teve %d votos!\n\n", nCandidato, a);

					break;

				case 3:
					System.out.println("COMPUTANDO RESULTADO...");
					a = stub.resultado();

					System.out.printf("O candidato %d foi o mais votado!\n", a);

					break;

				default:
					System.out.println("ELEIÇÃO CONCLUÍDA!");
					break;
				}

			}

			s.close();

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}