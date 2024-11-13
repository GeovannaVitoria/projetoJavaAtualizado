package br.com.arq.model;

import java.security.MessageDigest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	@Column(unique = true)
	private String email;
	private String senha;
	private String crm;
	private String hospital;
	private String especialidade;

	public Medico() {
		super();
	}

	public Medico(Long id, String nome, String email, String senha, String crm, String hospital, String especialidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.crm = crm;
		this.hospital = hospital;
		this.especialidade = especialidade;
	}

	public Medico(String nome, String senha, String crm, String hospital, String email, String especialidade) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.crm = crm;
		this.hospital = hospital;
		this.email = email;
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return "Medico [nome=" + nome + ", senha=" + senha + ", crm=" + crm + ", hospital=" + hospital + ", email="
				+ email + ", especialidade=" + especialidade + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String criptografarSenha(String senha) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] messageDigest = md5.digest(senha.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte b : messageDigest) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

}