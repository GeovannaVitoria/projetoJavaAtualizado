package br.com.arq.controller;

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
public class UsersController {

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
	public String cadastrarPaciente(@RequestParam String email, @RequestParam String nome, @RequestParam String senha,
			@RequestParam String cpf, @RequestParam String dataNasc, @RequestParam String nomeDoenca,
			@RequestParam String sexo, @RequestParam String descDoenca, Model model) {
		if (pacienteRepository.findByEmail(email).isPresent()) {
			model.addAttribute("message", "Email já cadastrado para paciente. Tente outro.");
			return "cadastroPaciente";
		}

		try {
			Paciente paciente = new Paciente(cpf, nome, dataNasc, nomeDoenca, sexo, descDoenca);
			pacienteRepository.save(paciente);
			model.addAttribute("message", "Cadastro realizado com sucesso!");
			return "login"; // Redireciona para a página de login
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

	// Método de login
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String senha, @RequestParam String tipo, Model model)
			throws Exception {
		Optional<Medico> optionalMedico = Optional.empty();
		Optional<Paciente> optionalPaciente = Optional.empty();

		if (tipo.equals("medico")) {
			optionalMedico = medicoRepository.findByEmail(email);
		} else if (tipo.equals("paciente")) {
			optionalPaciente = pacienteRepository.findByEmail(email);
		}

		if (tipo.equals("medico") && optionalMedico.isPresent()
				&& optionalMedico.get().getSenha().equals(optionalMedico.get().criptografarSenha(senha))) {
			model.addAttribute("message", "Login realizado com sucesso!");
			return "redirect:/home";
		} else if (tipo.equals("paciente") && optionalPaciente.isPresent()
				&& optionalPaciente.get().getNome().equals(email) && optionalPaciente.get().getCpf().equals(senha)) {
			model.addAttribute("message", "Login realizado com sucesso!");
			return "redirect:/home";
		} else {
			model.addAttribute("message", "Email ou senha inválidos.");
			return "login";
		}
	}

	// Página inicial após login
	@GetMapping("/home")
	public String home(Model model) {
		return "home";
	}

	// Logout
	@GetMapping("/logout")
	public String logout(Model model) {
		return "login";
	}
}
