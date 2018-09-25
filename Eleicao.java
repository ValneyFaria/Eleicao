import java.rmi.Remote;
import java.rmi.RemoteException;

// IP: 172.18.2.46

public interface Eleicao extends Remote {
    String sayHello() throws RemoteException;

    int votar(int nCandidato, int nVotante) throws RemoteException;

    int resultadoParcial(int nCandidato) throws RemoteException;

    int resultado() throws RemoteException;
}