import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.print.attribute.standard.Sides;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CentroMonitoraggio {
	// CAMPI
	private String nome;
	private Indirizzo indirizzo;
	private ListaAree aree;

	// COSTRUTTORE
	public CentroMonitoraggio(String nome, Indirizzo indirizzo) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.aree = new ListaAree();
		try {
			this.creazioneListaAree();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public CentroMonitoraggio() {
		nome = "Torre Civile";
		indirizzo = new Indirizzo();
		aree = new ListaAree();
	}

	// METODO
	public ListaAree getListaAree() {
		return this.aree;
	}

	public void creazioneListaAree() throws IOException {
		String[] HEADERS = { "GeonameID", "Name", "ASCIIName", "CountryCode", "CountryName", "Lat", "Long" };
		Reader in = new FileReader("./data/CoordinateMonitoraggio.csv");
		CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
				.setHeader(HEADERS)
				.setSkipHeaderRecord(true)
				.build();

		Iterable<CSVRecord> records = csvFormat.parse(in);
		Scanner sc = new Scanner(System.in);

		AreaGeografica ag;
		ListaAree lag = new ListaAree();

		for (CSVRecord record : records) {
			// System.out.println(record.toString());
			ag = new AreaGeografica(Double.parseDouble(record.get("Lat")), Double.parseDouble(record.get("Long")),
					record.get("CountryName"), record.get("Name"));
			lag.addFirst(ag);
		}
		System.out.print(
				"inserire: \n 1)cerca area per denominazione: \n 2)cerca aree per coordinate : \n per uscire qualsiasi altro valore: \n");
		int caso = sc.nextInt();
		sc.nextLine();
		do {
			switch (caso) {
				case 1:
					System.out.print("Inserire nome area geografica : ");
					String nome = sc.nextLine();
					ListaAree listatmp = lag.cercaAreaGeografica(nome, null);
					int i = 0;
					for (AreaGeografica tmp : listatmp)
						System.out.println("" + ++i + " " + tmp.toString());

					do {
						System.out.print(
								"Inserire l'area da aggiungere nel centro di monitoraggio (inserire valore compreso tra 1 e "
										+ listatmp.size() + ") \n");
						i = sc.nextInt();
						sc.nextLine();
					} while ((i <= 0) || (i > listatmp.size()));

					this.aree.addFirst(listatmp.get(i - 1));

					break;

				case 2: // se cerco con le coordinate mi resituisce l'area geografica
					System.out.print("Inserire la latitudine : ");
					double lat;
					try {
						lat = sc.nextDouble();
						sc.nextLine();
					} catch (InputMismatchException e) {
						e.printStackTrace();
						continue;
					}


					System.out.print("Inserire la longitudine : ");
					double lon;
					try {
						lon = sc.nextDouble();
						sc.nextLine();
					} catch (InputMismatchException e) {
						e.printStackTrace();
						continue;
					}

					ag = lag.cercaAreeGeografiche(lat, lon).get();
					System.out.println("Area trovata: " + ag.toString());

					System.out.println(
							"Vuoi aggiungere l'area al centro di monitoraggio? \n inserire s per si \n inserire n per no \n");
					String r = sc.nextLine();
					if (r.toLowerCase().equals("s")) {
						this.aree.addFirst(ag);
						System.out.println("area aggiunta");
					} else
						System.out.println("area non aggiunta");

					break;

				default:
					if (this.aree.size() > 0)
						return;

			}
			System.out.print(
					"inserire: \n 1)cerca area per denominazione: \n 2)cerca aree per coordinate : \n per uscire qualsiasi altro valore: \n");
			caso = sc.nextInt();
			sc.nextLine();

		} while (true);

	}

}
