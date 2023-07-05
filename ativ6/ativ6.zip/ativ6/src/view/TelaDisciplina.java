package view;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import db.DB;
import model.dao.DisciplinaDAO;
import model.dao.DisciplinaDAOImp;
import model.dao.FactoryDAO;
import model.entities.Disciplina;

public class TelaDisciplina {

	static DisciplinaDAO disciplinaDAO = FactoryDAO.createDisciplinaDAO();

	@SuppressWarnings("resource")
	public static Scanner menuDisciplina(Scanner console) throws InterruptedException, ParseException {
		int opcao = 0;

		do {
			System.out.println("\n\n ### Display Disciplina ###");
			System.out.println(" =======================================");
			System.out.println(" | 2 - Listar                          |");
			System.out.println(" | 1 - Cadastrar                       |");
			System.out.println(" | 4 - Excluir                         |");
			System.out.println(" | 3 - Alterar                         |");
			System.out.println(" | 0 - Sair                            |");
			System.out.println(" =======================================");
			System.out.print("Opção -> ");
			opcao = console.nextInt();
			console.nextLine();

			switch (opcao) {
			case 1:
				console = listar(console);
				break;
			case 2:
				console = cadastrar(console);
				break;
			case 3:
				console = excluir(console);
				break;
			case 4:
				console = alterar(console);
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
		Disciplina disciplina = new Disciplina();

		System.out.println("\n\n ### Disciplina: Cadastrar ###");
		System.out.println(" =======================================");
		System.out.println(" | Nome: ");
		disciplina.setNomedisciplina(console.nextLine());
		System.out.println(" | Carga Horária: ");
		disciplina.setCargahoraria(console.nextInt());
		console.nextLine(); // Limpa o buffer

		DisciplinaDAOImp disciplinaDao = new DisciplinaDAOImp(DB.getConexao());
		disciplinaDao.insert(disciplina);

		if (disciplina.getId() != null) {
			System.out.println("Cadastrada com sucesso!");
		} else {
			System.out.println("Falha ao cadastrar a disciplina. Por favor, tente novamente.");
		}

		return console;
	}

	private static Scanner listar(Scanner console) {
		List<Disciplina> disciplinas = disciplinaDAO.findAll();
		System.out.println("\n\n");
		System.out.println(" ## Disciplina - Lista ## ");
		System.out.println("====================================");
		System.out.println("    |     Id \tNome\tCarga Horária");
		System.out.println("------------------------------------");
		for (Disciplina disciplina : disciplinas) {
			System.out.println("    |      " + disciplina.getId() + "\t" + disciplina.getNomedisciplina() + "\t"
					+ disciplina.getCargahoraria());
		}
		System.out.println("====================================");

		return console;
	}

	private static Scanner alterar(Scanner console) {
		DisciplinaDAOImp disciplinaDao = new DisciplinaDAOImp(DB.getConexao());

		System.out.println("\n\n ### Disciplina: Alterar ###");
		System.out.println(" =======================================");
		System.out.print(" | ID da Disciplina: ");
		int id = console.nextInt();
		console.nextLine();
		System.out.print(" | Novo Nome da Disciplina: ");
		String novoNome = console.nextLine();
		System.out.print(" | Nova Carga Horária: ");
		int novaCargaHoraria = console.nextInt();
		console.nextLine(); // Limpa o buffer
		System.out.println(" =======================================");

		Disciplina disciplina = disciplinaDao.findById(id);

		if (disciplina != null) {
			disciplina.setNomedisciplina(novoNome);
			disciplina.setCargahoraria(novaCargaHoraria);
			disciplinaDao.update(disciplina);
			System.out.println("Disciplina alterada com sucesso!");
		} else {
			System.out.println("Disciplina não encontrada!");
		}

		return console;
	}

	private static Scanner excluir(Scanner console) {
		DisciplinaDAOImp disciplinaDao = new DisciplinaDAOImp(DB.getConexao());

		System.out.println("\n\n ### Disciplina: Excluir ###");
		System.out.println(" =======================================");
		System.out.print(" | ID: ");
		int id = console.nextInt();
		console.nextLine();
		System.out.println(" =======================================");

		disciplinaDao.deleteById(id);
		System.out.println("Disciplina excluída com sucesso!");

		return console;
	}
}
