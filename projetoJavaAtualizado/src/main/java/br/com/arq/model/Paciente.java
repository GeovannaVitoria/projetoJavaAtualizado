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

	private String cpf;
	@Column(unique = true)
	private String nome;
	private String data_nasc;
	private String nome_doenca;
	private String sexo;
	private String desc_doenca;

	public Paciente() {
		super();
	}

	public Paciente(Long id, String cpf, String nome, String data_nasc, String nome_doenca, String sexo,
			String desc_doenca) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.data_nasc = data_nasc;
		this.nome_doenca = nome_doenca;
		this.sexo = sexo;
		this.desc_doenca = desc_doenca;
	}

	public Paciente(String cpf, String nome, String data_nasc, String nome_doenca, String sexo, String desc_doenca) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.data_nasc = data_nasc;
		this.nome_doenca = nome_doenca;
		this.sexo = sexo;
		this.desc_doenca = desc_doenca;
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

	public String getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getNome_doenca() {
		return nome_doenca;
	}

	public void setNome_doenca(String nome_doenca) {
		this.nome_doenca = nome_doenca;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDesc_doenca() {
		return desc_doenca;
	}

	public void setDesc_doenca(String desc_doenca) {
		this.desc_doenca = desc_doenca;
	}

	@Override
	public String toString() {
		return "Paciente [cpf=" + cpf + ", nome=" + nome + ", data_nasc=" + data_nasc + ", nome_doenca=" + nome_doenca
				+ ", sexo=" + sexo + ", desc_doenca=" + desc_doenca + "]";
	}

}