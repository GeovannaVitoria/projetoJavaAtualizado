package br.com.arq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.arq.model.Medico;
import br.com.arq.model.Paciente;
import br.com.arq.repository.MedicoRepository;
import br.com.arq.repository.PacienteRepository;

@Controller
public class UserController {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@GetMapping("/cadastroMedico")
	public String cadastroMedicoForm() {
		return "cadastroMedico";
	}

	@GetMapping("/cadastroPaciente")
	public String cadastroPacienteForm() {
		return "cadastroPaciente";
	}

	@PostMapping("/cadastroMedico")
	public String cadastrarMedico(@RequestParam String email, @RequestParam String nome, @RequestParam String senha,
			@RequestParam String crm, @RequestParam String hospital, @RequestParam String especialidade, Model model) {
		if (medicoRepository.findByEmail(email).isPresent()) {
			model.addAttribute("message", "Email já cadastrado para médico. Tente outro.");
			return "cadastroMedico";
		}

		try {
			Medico medico = new Medico(nome, senha, crm, hospital, email, especialidade);
			medico.setSenha(medico.criptografarSenha(senha));
			medicoRepository.save(medico);
			model.addAttribute("message", "Cadastro realizado com sucesso!");
			return "login";
		} catch (Exception ex) {
			model.addAttribute("message", "Erro: " + ex.getMessage());
			return "cadastroMedico";
		}
	}

	// Método de cadastro de pacientes
	@PostMapping("/cadastroPaciente")
	public String cadastrarPaciente(@RequestParam String cpf, @RequestParam String nome, @RequestParam String dataNasc,
			@RequestParam String nomeDoenca, @RequestParam String sexo, @RequestParam String descDoenca, Model model) {

		if (pacienteRepository.findByCpf(cpf).isPresent()) {
			model.addAttribute("message", "CPF já cadastrado para paciente. Tente outro.");
			return "cadastroPaciente";
		}

		try {
			Paciente paciente = new Paciente();
			paciente.setCpf(cpf);
			paciente.setNome(nome);
			paciente.setDataNasc(dataNasc);
			paciente.setNomeDoenca(nomeDoenca);
			paciente.setSexo(sexo);
			paciente.setDescDoenca(descDoenca);
			pacienteRepository.save(paciente);
			return "redirect:/home";
		} catch (Exception ex) {
			model.addAttribute("message", "Erro: " + ex.getMessage());
			return "cadastroPaciente";
		}
	}

	// Página de login
	@GetMapping("/login")
	public String loginForm(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String senha, Model model) throws Exception {
		Optional<Medico> optionalMedico = medicoRepository.findByEmail(email);

		if (optionalMedico.isPresent()
				&& optionalMedico.get().getSenha().equals(optionalMedico.get().criptografarSenha(senha))) {
			model.addAttribute("message", "Login realizado com sucesso!");
			return "redirect:/home";
		} else {
			model.addAttribute("message", "Email ou senha inválidos ou médico não cadastrado.");
			return "login";
		}
	}

	// Página inicial após login
	@GetMapping("/home")
	public String home(Model model) {
		List<Paciente> pacientes = pacienteRepository.findAll();
		model.addAttribute("pacientes", pacientes); // Adiciona os pacientes no modelo
		return "home"; // Retorna a página home.html
	}

	// Logout
	@GetMapping("/logout")
	public String logout(Model model) {
		return "login";
	}

	@GetMapping("/relatorio")
	public String relatorioForm() {
		return "relatorio";
	}
}