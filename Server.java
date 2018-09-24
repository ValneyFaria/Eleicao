import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements Eleicao {
    ArrayList<Candidato> votacao = new ArrayList<Candidato>();

    public int votar(int nCandidato, int nVotante) {
        // Verifica se o Votante já votou
        for (Candidato c : votacao) {
            if(c.getVotou() == true) {
                System.out.println("Voto Já Computado");
                return;
            }
        }

        // Verifica se o numero do candidato está na lista
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
                    return;
                }
            }

            // Marca que o Eleitor Votou
            for (Candidato c : votacao) {
                if(c.getNCandidato() == nVotante) {
                    c.setVotou();
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