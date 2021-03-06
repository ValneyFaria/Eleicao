public class Candidato {
	private int nCandidato = 0;
	private int nVotos = 0;
	private boolean votou = false;

	Candidato() {
	}

	Candidato(int nCandidato) {
		this.nCandidato = nCandidato;
	}

	int getNCandidato() {
		return this.nCandidato;
	}

	void setNCandidato(int nCandidato) {
		this.nCandidato = nCandidato;
	}

	int getNVotos() {
		return this.nVotos;
	}

	// Incrementa o numero de votos do candidato
	void votarCandidato() {
		this.nVotos++;
	}

	boolean getVotou() {
		return this.votou;
	}

	// Seta que o candidato votou
	void setVotou() {
		this.votou = true;
	}
}