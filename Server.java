import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Eleicao {
	ArrayList<Candidato> candidatosList = new ArrayList<Candidato>();
	ArrayList<Candidato> eleitoresList = new ArrayList<Candidato>();

	Server() {
		candidatosList.add(new Candidato(96));
		candidatosList.add(new Candidato(12));
		candidatosList.add(new Candidato(13));
		candidatosList.add(new Candidato(51));
		candidatosList.add(new Candidato(20));
		candidatosList.add(new Candidato(17));
	}

	public static void main(String args[]) {

		try {
			Server obj = new Server();
			// Converte o Objeto para um Unicast Remote Object
			Eleicao stub = (Eleicao) UnicastRemoteObject.exportObject(obj, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Eleicao", stub);
			// O cliente conhece apenas a interface e não a chamada da classe

			System.err.println("Servidor Executando!");

			System.out.println("INICIANDO VOTACAO:");

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public int votar(int nCandidato, int nVotante) throws RemoteException {
		System.out.println("Método votar()\n");

		// Verifica se o Eleitor está votando em si mesmo
		if (nCandidato == nVotante) {
			System.out.println("ERRO! Não é possível votar em si mesmo!");
			return 1;
		}

		// Verifica se o Votante já votou
		for (int i = 0; i < eleitoresList.size(); i++) {
			if (eleitoresList.get(i).getNCandidato() == nVotante) {
				if (eleitoresList.get(i).getVotou() == true) {
					System.out.println("Voto já Computado!");
					// return 2; // Tratamento de Erro
					return 1;
				}
			}
		}

		// Verifica se o numero do candidato está na lista
		if (buscaCandidato(nCandidato)) {
			// Se o numero do nVotante é igual ao de um candidato
			if (nVotante == nCandidato) {
				System.out.println("ERRO! Não é possível votar em si mesmo!");
				// return 3; // Tratamento de Erro
				return 1;
			}
			// Busca o Candidato passado e seta um voto para ele
			for (Candidato c : candidatosList) {
				if (c.getNCandidato() == nCandidato) {
					System.out.println("Setando Voto!");
					c.votarCandidato();
					eleitoresList.add(new Candidato(nVotante));
					break;
				}
			}

			// Marca que o Eleitor Votou
			for (int i = 0; i < eleitoresList.size(); i++) {
				if (eleitoresList.get(i).getNCandidato() == nVotante) {
					eleitoresList.get(i).setVotou();
					System.out.println("Marcando voto");
					i = eleitoresList.size();
				}
			}
			return 0;
		} else {
			// Senao, adiciona o novo candidato na lista
			Candidato c = new Candidato();
			// Marca que o candidato votou
			c.setVotou();
			// Dá um voto para o candidato
			candidatosList.add(c);
		}

		return 0;
	}

	// Verifica se um candidato já está presente na lista de Candidatos
	public boolean buscaCandidato(int nCandidato) {
		for (int i = 0; i < candidatosList.size(); i++) {
			System.out.println("Buscando Candidato");
			if (candidatosList.get(i).getNCandidato() == nCandidato) {
				return true;
			}
		}
		return false;
	}

	public int resultadoParcial(int nCandidato) throws RemoteException {
		System.out.println("Método resultadoParcial()\n");
		int nVotos = 0;

		for (Candidato c : candidatosList) {
			if (c.getNCandidato() == nCandidato) {
				nVotos = c.getNVotos();
				return nVotos;
			}
		}

		return nVotos;
	}

	// Retorna o numero do candidato com mais votos
	public int resultado() {
		System.out.println("Método resultado()\n");
		// 20 pois não há muitos alunos na turma
		int maior = 0, nCandidato = 0;

		for (int i = 0; i < candidatosList.size(); i++) {
			// Verifica se o nVotos do candidato é o maior
			if (candidatosList.get(i).getNVotos() >= maior) {
				maior = candidatosList.get(i).getNVotos();
				nCandidato = candidatosList.get(i).getNCandidato();
			}
		}
		return nCandidato;
	}
}