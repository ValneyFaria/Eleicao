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
        System.out.println("Método votar()\n");
        // Verifica se o Votante já votou

        for (int i = 0; i < votacao.size(); i++) {
            if (votacao.get(i).getNCandidato() == nVotante && votacao.get(i).getVotou()) {
                System.out.println("Voto já Computado!");
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
            for (int i = 0; i < votacao.size(); i++) {
                if (votacao.get(i).getNCandidato() == nVotante) {
                    votacao.get(i).setVotou();
                    i = votacao.size();
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
    public boolean buscaCandidato(int nCandidato) {

        for (Candidato d : votacao) {
            if (d.getNCandidato() == nCandidato) {
                return true;
            }
        }
        return false;
    }

    public int resultadoParcial(int nCandidato) {
        System.out.println("Método resultadoParcial()\n");
        int nVotos = 0;

        for (Candidato c : votacao) {
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

        for (int i = 0; i < votacao.size(); i++) {
            // Verifica se o nVotos do candidato é o maior
            if (votacao.get(i).getNVotos() >= maior) {
                maior = votacao.get(i).getNVotos();
                nCandidato = votacao.get(i).getNCandidato();
            }
        }
        return nCandidato;
    }

    public String sayHello() throws RemoteException {
        System.out.println("HELLO WORLD!");
        return null;
    }

}