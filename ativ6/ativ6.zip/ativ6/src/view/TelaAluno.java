package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import db.DB;
import model.dao.AlunoDAO;
import model.dao.AlunoDAOImp;
import model.dao.FactoryDAO;
import model.entities.Aluno;

public class TelaAluno {
	static AlunoDAO alunoDAO = FactoryDAO.createAlunoDAO();

	@SuppressWarnings("resource")
	public static Scanner menuAluno(Scanner console) throws InterruptedException, ParseException {
		int opcao = 0;

		do {
			System.out.println("\n\n ### Display Aluno ###");
			System.out.println(" =======================================");
			System.out.println(" | 1 - Cadastrar                       |");
			System.out.println(" | 2 - Listar                          |");
			System.out.println(" | 3 - Alterar                         |");
			System.out.println(" | 4 - Excluir                         |");
			System.out.println(" | 0 - Sair                            |");
			System.out.println(" =======================================");
			System.out.println("Opção -> ");
			opcao = console.nextInt();
			console.nextLine();

			switch (opcao) {
			case 1:
				console = cadastrar(console);
				break;
			case 2:
				console = listar(console);
				break;
			case 3:
				console = alterar(console);
				break;
			case 4:
				console = excluir(console);
				break;
			case 0:
				console = TelaPrincipal.menuPrincipal(console);
				break;
			default:
				System.out.println("Opção inválida!");
				TimeUnit.SECONDS.sleep(1);
			}

		} while (opcao != 0);

		return console;
	}

	private static Scanner cadastrar(Scanner console) {
		Aluno aluno = new Aluno();

		System.out.println("\n\n ### Aluno: Cadastrar ###");
		System.out.println(" =======================================");
		System.out.println(" | Nome: ");
		aluno.setNome(console.nextLine());
		System.out.println(" | Sexo: ");
		aluno.setSexo(console.nextLine());
		System.out.println(" | Data de Nascimento (dd/mm/aaaa): ");
		String dataNascimentoStr = console.nextLine();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			aluno.setDt_nasc(dateFormat.parse(dataNascimentoStr));
		} catch (ParseException e) {
			System.out.println("Formato de data inválido. Tente novamente.");
			return console;
		}
		System.out.println(" | Nota: ");
		aluno.setNota(console.nextFloat());
		console.nextLine();

		AlunoDAOImp alunoDao = new AlunoDAOImp(DB.getConexao());
		alunoDao.insert(aluno);

		if (aluno.getIdaluno() != 0) {
			System.out.println("Aluno cadastrado com sucesso!");
		} else {
			System.out.println("Falha ao cadastrar o aluno. Por favor, tente novamente.");
		}

		return console;
	}

	private static Scanner listar(Scanner console) {
		List<Aluno> alunos = alunoDAO.findAll();
		System.out.println("\n\n");
		System.out.println(" ## Aluno - Lista ## ");
		System.out.println("====================================");
		System.out.println("    |     Id \tNome");
		System.out.println("------------------------------------");
		for (Aluno aluno : alunos) {
			System.out.println("    |      " + aluno.getIdaluno() + "\t" + aluno.getNome());
		}
		System.out.println("====================================");

		return console;
	}

	private static Scanner alterar(Scanner console) {
		AlunoDAOImp alunoDao = new AlunoDAOImp(DB.getConexao());

		System.out.println("\n\n ### Aluno: Alterar ###");
		System.out.println(" =======================================");
		System.out.print(" | ID do Aluno: ");
		int id = console.nextInt();
		console.nextLine();
		System.out.print(" | Novo Nome do Aluno: ");
		String novoNome = console.nextLine();
		System.out.println(" =======================================");

		Aluno aluno = alunoDao.findById(id);

		if (aluno != null) {
			aluno.setNome(novoNome);
			alunoDao.update(aluno);
			System.out.println("Aluno alterado com sucesso!");
		} else {
			System.out.println("Aluno não encontrado!");
		}

		return console;
	}

	private static Scanner excluir(Scanner console) {
		AlunoDAOImp alunoDao = new AlunoDAOImp(DB.getConexao());

		System.out.println("\n\n ### Aluno: Excluir ###");
		System.out.println(" =======================================");
		System.out.print(" | ID: ");
		int id = console.nextInt();
		System.out.println(" =======================================");

		alunoDao.deleteById(id);

		return console;
	}
}
