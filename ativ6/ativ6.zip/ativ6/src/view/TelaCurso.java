package view;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import db.DB;
import model.dao.CursoDAO;
import model.dao.CursoDAOImp;
import model.dao.FactoryDAO;
import model.entities.Curso;

public class TelaCurso {

	static CursoDAO cursoDAO = FactoryDAO.createCursoDAO();

	@SuppressWarnings("resource")
	public static Scanner menuCurso(Scanner console) throws InterruptedException, ParseException {
		int opcao = 0;

		do {
			System.out.println("\n\n ### Display Curso ###");
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
		Curso c = new Curso();

		System.out.println("\n\n ### Curso: Cadastra ###");
		System.out.println(" =======================================");
		System.out.println(" | Nome: ");
		c.setNomecurso(console.nextLine());
		System.out.println(" =======================================");
		CursoDAOImp cursoDao = new CursoDAOImp(DB.getConexao());
		cursoDao.insert(c);
		console.nextLine();

		if (c.getIdcurso() != 0) {
			System.out.println("Curso cadastrado com sucesso!");
		} else {
			System.out.println("Falha ao cadastrar o curso. Por favor, tente novamente.");
		}

		return console;
	}

	private static Scanner listar(Scanner console) {
		List<Curso> cursos = cursoDAO.findAll();
		System.out.println("\n\n");
		System.out.println(" ## Curso - Lista ## ");
		System.out.println("====================================");
		System.out.println("    |     Id \tNome");
		System.out.println("------------------------------------");
		for (Curso c : cursos) {
			System.out.println("    |      " + c.getIdcurso() + "\t" + c.getNomecurso());
		}
		System.out.println("====================================");

		console.nextLine();
		return console;
	}

	private static Scanner alterar(Scanner console) {
		CursoDAOImp cursoDao = new CursoDAOImp(DB.getConexao());

		System.out.println("\n\n ### Curso: Alterar ###");
		System.out.println(" =======================================");
		System.out.print(" | ID do Curso: ");
		int id = console.nextInt();
		console.nextLine();
		System.out.print(" | Novo Nome do Curso: ");
		String novoNome = console.nextLine();
		System.out.println(" =======================================");

		Curso curso = cursoDao.findById(id);

		if (curso != null) {
			curso.setNomecurso(novoNome);
			cursoDao.update(curso);
			System.out.println("Curso alterado com sucesso!");
		} else {
			System.out.println("Curso não encontrado!");
		}

		return console;
	}

	private static Scanner excluir(Scanner console) {
		CursoDAOImp cursoDao = new CursoDAOImp(DB.getConexao());

		System.out.println("\n\n ### Curso: Excluir ###");
		System.out.println(" =======================================");
		System.out.print(" | ID: ");
		int id = console.nextInt();
		System.out.println(" =======================================");

		cursoDao.deleteById(id);

		return console;
	}

}
