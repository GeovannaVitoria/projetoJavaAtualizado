package br.com.arq.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String cpf;
	private String nome;
	private String dataNasc;
	private String nomeDoenca;
	private String sexo;
	private String descDoenca;

	public Paciente() {
		super();
	}

	public Paciente(Long id, String cpf, String nome, String data_nasc, String nome_doenca, String sexo,
			String desc_doenca) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = data_nasc;
		this.nomeDoenca = nome_doenca;
		this.sexo = sexo;
		this.descDoenca = desc_doenca;
	}

	public Paciente(String cpf, String nome, String data_nasc, String nome_doenca, String sexo, String desc_doenca) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = data_nasc;
		this.nomeDoenca = nome_doenca;
		this.sexo = sexo;
		this.descDoenca = desc_doenca;
	}

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

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getNomeDoenca() {
		return nomeDoenca;
	}

	public void setNomeDoenca(String nomeDoenca) {
		this.nomeDoenca = nomeDoenca;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDescDoenca() {
		return descDoenca;
	}

	public void setDescDoenca(String descDoenca) {
		this.descDoenca = descDoenca;
	}

	@Override
	public String toString() {
		return "Paciente [cpf=" + cpf + ", nome=" + nome + ", data_nasc=" + dataNasc + ", nome_doenca=" + nomeDoenca
				+ ", sexo=" + sexo + ", desc_doenca=" + descDoenca + "]";
	}

}