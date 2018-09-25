import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Eleicao {
    ArrayList<Candidato> votacao = new ArrayList<Candidato>();

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


    public int votar(int nCandidato, int nVotante) {
        // Verifica se o Votante já votou
        for (Candidato c : votacao) {
            if (c.getVotou() == true) {
                System.out.println("ERRO! Voto Já Computado");
                return 0;
            }
        }

        // Verifica se o numero do candidato está na lista
        if (buscaCandidato(nCandidato)) {
            // Se o numero do nVotante é igual ao de um candidato
            if (buscaCandidato(nVotante)) {
                System.out.println("ERRO! Não é possível votar em si mesmo!");
                return 0;
            }
            // Busca o Candidato passado e seta um voto para ele
            for (Candidato c : votacao) {
                if (c.getNCandidato() == nCandidato) {
                    c.votarCandidato();
                    return 1;
                }
            }

            // Marca que o Eleitor Votou
            for (Candidato c : votacao) {
                if (c.getNCandidato() == nVotante) {
                    c.setVotou();
                }
            }
            return 1;
        }

        // Senao, adiciona o novo candidato na lista
        Candidato c = new Candidato(nCandidato);
        // Marca que o candidato votou
        c.setVotou();
        // Dá um voto para o candidato
        votacao.add(c);

        return 1;
    }

    // Verifica se um candidato já está presente na lista
    public Candidato buscaCandidato(int nCandidato) {
        for (Candidato c : votacao) {
            if (c.getNCandidato() == nCandidato) {
                return c;
            }
        }
        return c;
    }

    public int resultadoParcial(int nCandidato) {
        int nVotos = 0;

        for (Candidato c : votacao) {
            if (c.getNCandidato() == nCandidato) {
                nVotos = c.getNVotos();
                System.out.println("O candidato " + nCandidato + " recebeu " + nVotos + " votos!");
                return nVotos;
            }
        }

        System.out.println("O candidato " + nCandidato + " recebeu " + nVotos + " votos!");
        return nVotos;
    }

    public int resultado() {
        // 20 pois não há muitos alunos na turma
        int maior = 20;
        Candidato Ca;

        for (Candidato c : votacao) {
            if (c.getNVotos() >= maior) {
                maior = c.getNVotos();
                Ca = c;
            }
        }

        System.out.println("O candidato " + Ca.getNCandidato() + " foi eleito com " + nVotos + " votos!");
        return resultado;
    }

}