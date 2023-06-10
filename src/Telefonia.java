import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Telefonia {

	private int numAssinantes;
	private static Assinante[] assinantes;
	public static Scanner input = new Scanner(System.in);

	public Telefonia() {
		Telefonia.assinantes = new Assinante[20];
	}

	public void cadastrarAssinante(Assinante[] assinante) {

		System.out.printf("\n1 - PrePago");
		System.out.printf("\n2 - PosPago");
		System.out.printf("\n\nSelecione o tipo de assinante: ");
		int tipoAssinante = input.nextInt();
	    input.nextLine();

		System.out.printf("\nInforme o CPF do assinante: ");
		long cpf = input.nextLong();
		input.nextLine();

		System.out.printf("\nInforme o nome do assinante: ");
		String nome = input.nextLine(); 

		System.out.printf("\nInforme o numero do telefone do assinante: ");
		int numero = input.nextInt();
		input.nextLine();

		if (tipoAssinante == 1) { // Pré-pago
			if (numAssinantes < assinantes.length) {
				assinantes[numAssinantes] = new PrePago(cpf, nome, numero);
				numAssinantes++;
				System.out.printf("\nAssinante pre-pago cadastrado com sucesso!\n\n");
			} else {
				System.out.printf(
						"\nNão foi possivel cadastrar o assinante. Limite maximo de assinantes pre-pagos atingido.\n\n");
			}
		} else if (tipoAssinante == 2) { // Pós-pago
			if (numAssinantes < assinantes.length) {
				assinantes[numAssinantes] = new PosPago(cpf, nome, numero);
				numAssinantes++;
				System.out.printf("\nAssinante pos-pago cadastrado com sucesso!\n\n");
			} else {
				System.out.printf(
						"\nNao foi possível cadastrar o assinante. Limite máximo de assinantes pos-pagos atingido.\n\n");
			}
		} else {
			System.out.printf("\nOpcao invalida.\n\n");
		}
	}

	public void listarAssinantes() {
		System.out.printf("\nPre pagos\n");
		for (int i = 0; i < Telefonia.assinantes.length; i++) {
			if (Telefonia.assinantes[i] != null && Telefonia.assinantes[i] instanceof PrePago) {
				System.out.println(assinantes[i]);
				System.out.printf("\n");
			}
		}

		System.out.printf("\n");
		System.out.printf("Pos pagos\n");
		for (int i = 0; i < Telefonia.assinantes.length; i++) {
			if (Telefonia.assinantes[i] != null && Telefonia.assinantes[i] instanceof PosPago) {
				System.out.println(assinantes[i]);
				System.out.printf("\n");
			}
		}
	}

	public void fazerChamada(Assinante[] assinantes)  {
		
		// solicita o tipo do assinante (pré-pago ou pós-pago)
		System.out.println("Digite o tipo do assinante (pre ou pos):");
		
		String tipo = input.nextLine();
		System.out.println();
		//input.nextLine();

		// solicita o CPF do assinante
		System.out.println("Digite o CPF do assinante:");
		long cpf = input.nextLong();
		input.nextLine();

		// procura pelo assinante no vetor correspondente
		if (tipo.equals("pre")) {
			Assinante pre = localizarAssinante(cpf);
			if (pre != null) {
				// solicita a duração e a data da chamada
				System.out.println("Digite a duracao da chamada em minutos:");
				int duracao = input.nextInt();
				input.nextLine(); // consome a quebra de linha

				System.out.println("Digite a data da chamada (dd/mm/yyyy):");
				String dataStr = input.nextLine();
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

				Date data = null;
				try {
					data = formatoData.parse(dataStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				GregorianCalendar dataChamada = new GregorianCalendar();
				dataChamada.setTime(data);

				// registra a chamada para o assinante
				pre.fazerChamada(dataChamada, duracao);
			} else {
				System.out.println("Assinante pre-pago nao encontrado!");
			}
		} else if (tipo.equals("pos")) {
			Assinante pos = localizarAssinante(cpf);
			if (pos != null) {
				// solicita a duração e a data da chamada
				System.out.println("Digite a duracao da chamada em minutos:");
				int duracao = input.nextInt();
				input.nextLine(); // consome a quebra de linha

				System.out.println("Digite a data da chamada (dd/mm/yyyy):");
				String dataStr = input.nextLine();
				SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				Date data = null;
				try {
					data = (Date) formatoData.parse(dataStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GregorianCalendar dataChamada = new GregorianCalendar();
				dataChamada.setTime(data);

				// registra a chamada para o assinante
				pos.fazerChamada(dataChamada, duracao);
			} else {
				System.out.println("Assinante pos-pago nao encontrado!");
			}
		} else {
			System.out.println("Tipo de assinante invalido!");
		}
	}

	public void fazerRecarga(PrePago prePago) {

		System.out.println("Digite o CPF do assinante pre-pago: ");
		long cpf = input.nextLong();

		PrePago assinantePrePago = (PrePago) localizarAssinante(cpf);

		if (assinantePrePago == null) {
			System.out.println("Assinante nao encontrado.");
			return;
		}

		System.out.println("Digite o valor da recarga: ");
		float valor = input.nextFloat();
		input.nextLine(); // limpa o buffer do teclado

		System.out.println("Digite a data da recarga (dd/mm/aaaa): ");
		String dataStr = input.nextLine();

		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = (Date) df.parse(dataStr);

			assinantePrePago.recarregar(new GregorianCalendar(data.getYear() + 1900, data.getMonth(), data.getDate()),
					valor);
			System.out.println("Recarga realizada com sucesso!");
		} catch (ParseException e) {
			System.out.println("Data invalida!");
		}
	}

	private Assinante localizarAssinante(long cpf) {
		for (int i = 0; i < numAssinantes; i++) {
			if (assinantes[i].getCpf() == cpf) {
				return assinantes[i];
			}
		}
		return null;

	}

	public void imprimirFaturas() {

		System.out.print("Digite o mes desejado (1 a 12): ");
		int mes = input.nextInt();
		input.nextLine();

		// Imprime as faturas dos assinantes
		for (int i = 0; i < numAssinantes; i++) {
			assinantes[i].imprimirFatura(mes);
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		Telefonia telefonia = new Telefonia();
		// Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Selecione uma opcao:");
			System.out.println("1 - Cadastrar assinante");
			System.out.println("2 - Listar assinantes");
			System.out.println("3 - Fazer chamada");
			System.out.println("4 - Fazer recarga");
			System.out.println("5 - Imprimir faturas");
			System.out.println("0 - Sair");

			int opcao = input.nextInt();
			input.nextLine();

			if (opcao == 0) {
				break;
			}

			switch (opcao) {
			case 1:
				telefonia.cadastrarAssinante(assinantes);
				break;
			case 2:
				telefonia.listarAssinantes();
				break;
			case 3:
				telefonia.fazerChamada(assinantes);
				break;
			case 4:
				for(Assinante a : Telefonia.assinantes) {
					if(a instanceof PrePago) {
						telefonia.fazerRecarga((PrePago)a);
					}
				}
				break;
			case 5:
				telefonia.imprimirFaturas();
				break;
			default:
				System.out.println("Opcao invalida!");
			}
		}
	}
