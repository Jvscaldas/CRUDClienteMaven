package model;

import java.sql.Date;

public class Cliente {

	private int cpf;
	private String nome;
	private String email;
	private float limite_de_credito;
	private Date dt_nascimento;

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
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

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}

	@Override
	public String toString() {
		return "cliente [cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", limite_de_credito="
				+ limite_de_credito + ", dt_nascimento=" + dt_nascimento + "]";
	}

}
