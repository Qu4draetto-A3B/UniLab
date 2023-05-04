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

import a3b.climate.utils.result.Panic;
import a3b.climate.utils.result.Result;

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

	protected Gestore(String file, String[] headers) {
		FILE = file;
		METAFILE = FILE + ".DAT";
		HEADERS = headers;
		start();
	}

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

	protected void reload() {
		close();
		start();
	}

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
