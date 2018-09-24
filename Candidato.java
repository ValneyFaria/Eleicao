public class Candidato {
    private int nCandidato = 0;
    private int nVotos = 0;
    private boolean votou = false;

    private Candidato() {
    }

    private Candidato(int nCandidato) {
        this.nCandidato = nCandidato;
        this.nVotos += 1;
    }

    int getNCandidato() {
        return this.nCandidato;
    }

    void setNCandidato(int nCandidato) {
        this.nCandidato = nCandidato;
    }

    int getNVotos() {
        return this.nCandidato;
    }

    // Incrementa o numero de votos do candidato
    void setNVotos(int nVotos) {
        this.nVotos += 1;
    }

    boolean getVotou() {
        return this.votou;
    }

    // Seta que o candidato votou
    void setVotou() {
        this.votou = true;
    }
}