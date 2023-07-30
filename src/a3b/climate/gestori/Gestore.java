/*
 * Interdisciplinary Workshop A
 * Climate Monitoring
 * A.A. 2022-2023
 *
 * Authors:
 * - Iuri Antico, 753144
 * - Beatrice Balzarini, 752257
 * - Michael Bernasconi, 752259
 * - Gabriele Borgia, 753262
 *
 * Some rights reserved.
 * See LICENSE file for additional information.
 */
package a3b.climate.gestori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.Panic;
import a3b.climate.utils.result.Result;

/**
 * La classe astratta {@code Gestore} si occupa della gestione di dati contenuti
 * nei file CSV.
 * <p>
 * Fornisce metodi per la lettura e scrittura dei dati d'interesse.
 */
public abstract class Gestore implements AutoCloseable {
	protected final String FILE;
	protected final String METAFILE;
	protected final String[] HEADERS;

	protected CSVFormat format;
	protected Reader in;
	protected Writer out;

	protected BufferedReader metaIn;
	protected BufferedWriter metaOut;

	protected List<CSVRecord> records;
	protected CSVPrinter p;

	/**
	 * Costruttore di un'istanza di {@code Gestore} che si occupa di gestire i dati
	 * contenuti in file CSV.
	 *
	 * @param file    file su cui effettuare operazioni di lettura/scrittura
	 * @param headers intestazione nel file relativa all'oggetto
	 */
	protected Gestore(String file, String[] headers) {
		FILE = file;
		METAFILE = FILE + ".DAT";
		HEADERS = headers;
		start();
	}

	/**
	 * Inizializza e apre il file.
	 *
	 * @throws Panic se l'operazione non viene eseguita correttamente
	 * @see CSVFormat
	 * @see CSVPrinter
	 * @see CSVRecord
	 * @see FileReader
	 * @see FileWriter
	 * @see BufferedReader
	 * @see BufferedWriter
	 */
	private void start() {
		format = CSVFormat.DEFAULT.builder()
				.setHeader(HEADERS)
				.setSkipHeaderRecord(true)
				.build();

		try {
			in = new FileReader(FILE);
			out = new FileWriter(FILE, true);

			metaIn = new BufferedReader(new FileReader(METAFILE));
			metaOut = new BufferedWriter(new FileWriter(METAFILE));

			records = format.parse(in).getRecords();
			p = new CSVPrinter(out, format);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Panic("Non riesco a usare i file");
		}
	}

	/**
	 * Chiude un file.
	 *
	 * @see CSVPrinter#close(boolean)
	 * @see List#clear()
	 * @see FileWriter#close()
	 * @see FileReader#close()
	 * @see BufferedWriter#close()
	 * @see BufferedReader#close()
	 */

	@Override
	public void close() {
		try {
			p.close(true);
			records.clear();

			out.close();
			in.close();

			metaOut.close();
			metaIn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ricarica (chiude e riapre) un file.
	 *
	 * @see #close()
	 * @see #start()
	 */

	protected void reload() {
		close();
		start();
	}

	/**
	 * Costruisce un'istanza di {@link DataTable} con i dati forniti da un file CSV.
	 *
	 * @param record record CSV che rappresenta una riga nel file CSV
	 * @return istanza di {@link DataTable} contenente i dati forniti
	 */
	protected abstract DataTable buildObject(CSVRecord record);

	/**
	 * Recupera una propriet&agrave nel file di metadati associato ad una tabella
	 * CSV in base alla chiave data.
	 *
	 * @param key chiave per accedere alla propriet&agrave del file
	 * @return {@link Result} contenente la stringa relativa alla proprietà richiesta
	 */
	protected Result<String> getProperty(String key) {
		String val = "";
		for (String line : metaIn.lines().toList()) {
			String[] arr = line.split("=");

			String k;
			String v;

			switch (arr.length) {
				case 0 -> {
					k = "";
					v = "";
				}
				case 1 -> {
					k = arr[0].strip();
					v = "";
				}
				default -> {
					k = arr[0].strip();
					v = arr[1].strip();
				}
			}

			if (k.equals(key.strip())) {
				val = v;
				break;
			}
		}

		return new Result<>(val);
	}

	/**
	 * Imposta una propriet&agrave nel file di metadati associato ad una tabella CSV
	 * in base alla chiave data.
	 * <p>
	 * Nel caso in fosse impossibile scrivere sul file di metadati, restituisce un
	 * {@link Result} con un codice di errore.
	 *
	 * @param key chiave per accedere al file
	 * @param val propriet&agrave da impostare
	 * @return {@link Result} relativo all'operazione effettuata
	 */
	protected Result<Object> setProperty(String key, String val) {
		String contents = metaIn.toString();
		contents = contents.replaceFirst(
				String.format("%s[ ]*=[ ]*[A-Za-z0-9]*", key),
				String.format("%s = %s", key, val));

		try {
			metaOut.write(contents);
		} catch (IOException e) {
			e.printStackTrace();
			return new Result<>(1, "Impossibile scrivere sul file metadati");
		}

		return new Result<>(val);
	}
}
