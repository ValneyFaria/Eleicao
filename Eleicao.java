import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Eleicao extends Remote {
    String sayHello() throws RemoteException;

    public int votar(int nCandidato, int nVotante);

    public int resultadoParcial(int nCandidato);

    public int resultado();
}