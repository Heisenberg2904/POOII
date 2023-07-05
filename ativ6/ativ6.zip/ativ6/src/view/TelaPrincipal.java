package view;

import java.text.ParseException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.dao.CursoDAO;
import model.dao.FactoryDAO;

public class TelaPrincipal {

	static CursoDAO cursoDAO = FactoryDAO.createCursoDAO();

	public static Scanner menuPrincipal(Scanner console) throws ParseException, InterruptedException {

		int opcao = 0;
		do {
			System.out.println("\n\n### Controle Escolar  ###");
			System.out.println(" =======================================");
			System.out.println(" | 1 - Curso                        	|");
			System.out.println(" | 2 - Aluno                          	|");
			System.out.println(" | 3 - Disciplina                       |");
			System.out.println(" | 0 - Sair                         	|");
			System.out.println(" =======================================");
			System.out.print("    Opcao -> ");
			opcao = console.nextInt();
			console.nextLine();

			switch (opcao) {
			case 1:
				return TelaCurso.menuCurso(console);
			case 2:
				return TelaAluno.menuAluno(console);
			case 3:
				return TelaDisciplina.menuDisciplina(console);
			case 0:
				System.out.println("Encerrada!");
				break;
			default:
				System.out.println("Opcao Invalida!");
				TimeUnit.SECONDS.sleep(1);
			}
		} while (opcao != 0);
		return console;
	}
}