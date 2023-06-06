import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Telefonia {

	private int numAssinantes;
	private static Assinante[] assinante;
	public static Scanner input = new Scanner(System.in);

	public Telefonia() {
		this.assinante = new Assinante[20];
	}

	public void cadastrarAssinante(Assinante[] assinante) {

		System.out.print("\n1 - PrePago");
		System.out.print("\n2 - PosPago");
		System.out.print("\n\nSelecione o tipo de assinante: ");
		int tipoAssinante = input.nextInt();
	    input.nextLine();

		System.out.print("\nInforme o CPF do assinante: ");
		long cpf = input.nextLong();
		input.nextLine();

		System.out.print("\nInforme o nome do assinante: ");
		String nome = input.nextLine(); 

		System.out.print("\nInforme o numero do telefone do assinante: ");
		int numero = input.nextInt();
		input.nextLine();

		if (tipoAssinante == 1) { // Pré-pago
			if (numAssinantes < assinante.length) {
				assinante[numAssinantes] = new PrePago(cpf, nome, numero);
				numAssinantes++;
				System.out.print("\nAssinante pre-pago cadastrado com sucesso!\n");
			} else {
				System.out.print(
						"\nNão foi possivel cadastrar o assinante. Limite maximo de assinantes pre-pagos atingido.\n");
			}
		} else if (tipoAssinante == 2) { // Pós-pago
			if (numAssinantes < assinante.length) {
				assinante[numAssinantes] = new PosPago(cpf, nome, numero);
				numAssinantes++;
				System.out.print("\nAssinante pos-pago cadastrado com sucesso!\n");
			} else {
				System.out.print(
						"\nNao foi possível cadastrar o assinante. Limite máximo de assinantes pos-pagos atingido.\n");
			}
		} else {
			System.out.print("\nOpcao invalida.\n");
		}
	}

	public void listarAssinantes() {
		System.out.println("Pre pagos");
		for (int i = 0; i < this.assinante.length; i++) {
			if (this.assinante[i] != null && this.assinante[i] instanceof PrePago) {
				System.out.println(assinante[i]);
			}
		}

		System.out.println("Pos pagos");
		for (int i = 0; i < this.assinante.length; i++) {
			if (this.assinante[i] != null && this.assinante[i] instanceof PosPago) {
				System.out.println(assinante[i]);
			}
		}
	}

	public void fazerChamada(Assinante[] assinante)  {
		
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
			if (assinante[i].getCpf() == cpf) {
				return assinante[i];
			}
		}
		return null;

	}

	public void imprimirFaturas() {

		System.out.print("Digite o mes desejado (1 a 12): ");
		int mes = input.nextInt();
		input.nextLine();

		// Imprime as faturas dos assinantes pré-pagos
		for (int i = 0; i < numAssinantes; i++) {
			assinante[i].imprimirFatura(mes);
		}

		// Imprime as faturas dos assinantes pós-pagos
		for (int i = 0; i < numAssinantes; i++) {
			assinante[i].imprimirFatura(mes);
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
				telefonia.cadastrarAssinante(assinante);
				break;
			case 2:
				telefonia.listarAssinantes();
				break;
			case 3:
				// digite o cpf
				// busque pelo cpf no vetor de assintantes // if( assinantes[i] instance of PrePago){}
				// 
				
				telefonia.fazerChamada(assinante);
				break;
			case 4:
				System.out.println("Digite o CPF do assinante:");
				long cpf = input.nextLong();
				input.nextLine();
				
				for(Assinante a : Telefonia.assinante) {
					if(a instanceof PrePago && a.getCpf() == cpf) {
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
}
