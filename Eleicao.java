import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Eleicao extends Remote {
    String sayHello() throws RemoteException;

    int votar(int nCandidato, int nVotante) throws RemoteException;

    int resultadoParcial(int nCandidato) throws RemoteException;

    int resultado() throws RemoteException;
}