import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements Eleicao {
    ArrayList<Candidato> votacao = new ArrayList<Candidato>();

    public int votar(int nCandidato, int nVotante) {
        // Se o numero do candidato está na lista
        if(votacao.contains(nCandidato)) {
            // Se o numero do nVotante é igual ao de um candidato
            if(votacao.contains(nVotante)) {
                System.out.println("Não é possível votar em si mesmo!");
                return;
            }
            // Busca o Candidato passado e seta um voto para ele
            for (Candidato c : votacao) {
                if(c.getNCandidato() == nCandidato) {
                    c.setNVotos();
                }
            }
            return;
        }

        // Senao, adiciona o novo candidato na lista
        Candidato c = new Candidato(int nCandidato);
        // Marca que o candidato votou
        c.setVotou();
        // Dá um voto para o candidato
        votacao.add(c);

        return;
    }

    public int resultadoParcial(int nCandidato) {
        int nVotos = 0;

        for (Candidato c : votacao) {
            if (c.getNCandidato() == nCandidato) {
                nVotos = c.getNVotos();
                break;
            }
        }

        System.out.println("O candidato " + nCandidato + " recebeu " + nVotos + " votos!");
    }

    public int resultado() {

    }

}