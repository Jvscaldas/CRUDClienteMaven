package model;

public class Cliente {

	private String cpf;
	private String nome;
	private String email;
	private float limite_de_credito;
	private String dt_nascimento;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getLimite_de_credito() {
		return limite_de_credito;
	}

	public void setLimite_de_credito(float limite_de_credito) {
		this.limite_de_credito = limite_de_credito;
	}

	public String getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(String dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	@Override
	public String toString() {
		return "cliente [cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", limite_de_credito="
				+ limite_de_credito + ", dt_nascimento=" + dt_nascimento + "]";
	}

}
