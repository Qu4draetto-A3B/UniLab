import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CentroMonitoraggio
{
    //CAMPI
    private String nome;
    private Indirizzo indirizzo;
    private ListaAree aree;

    //COSTRUTTORE
    public CentroMonitoraggio (String nome, Indirizzo indirizzo)
    {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.aree = new ListaAree();
        try{this.creazioneListaAree();}
        catch(IOException e){e.printStackTrace();}

    }

    //METODO
    public ListaAree getListaAree(){
        return this.aree;
    }
    public void creazioneListaAree () throws IOException
    {
        String[] HEADERS = { "GeonameID", "Name", "ASCIIName", "CountryCode", "CountryName", "Lat","Long"};
        Reader in = new FileReader("./data/CoordinateMonitoraggio.csv");
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);
        Scanner sc = new Scanner(System.in);

        AreaGeografica ag;
        ListaAree lag = new ListaAree();

        boolean b = true;
        for (CSVRecord record : records)
        {
            //System.out.println(record.toString());
            ag = new AreaGeografica(Double.parseDouble(record.get("Lat")), Double.parseDouble(record.get("Long")), record.get("CountryName"), record.get("Name"));
            lag.addFirst(ag);
        }
            System.out.print("Cerca : ");
            int caso = sc.nextInt();
            sc.next();
            do
            {
                switch(caso)
                {
                    case 1:
                        System.out.print("Inserire nome area geografica : ");
                        String nome = sc.nextLine();
                        ListaAree listatmp = lag.cercaAreaGeografica(nome, null);
                        int i = 0;
                        for(AreaGeografica tmp : listatmp)
                            System.out.println(""+ ++i + " " + tmp.toString());

                        System.out.print("Inserire l'area da aggiungere nel centro di monitoraggio : ");
                        i = sc.nextInt();

                        this.aree.addFirst(listatmp.get(i-1));



                        break;

                    case 2: //se cerco con le coordinate mi resituisce l'area geografica
                        for (CSVRecord record : records)
                        {
                            double lon = Double.parseDouble(record.get("Long"));
                            double lat = Double.parseDouble(record.get("Lat"));
                            //if()

                        }



                    default:
                        b = this.aree != null;

                }

                System.out.print("Cerca : ");
                caso = sc.nextInt();

            }while (b);
            return;



    }



}
