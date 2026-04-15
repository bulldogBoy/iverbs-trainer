package io.github.bulldogboy.iverbstrainer;

import java.io.IOException;
import java.util.ArrayList;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {
	public static void main(String[] args) {
		try {
			Terminal terminal = TerminalBuilder.builder().system(true).build();
			LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
			DataOperations db = new DataOperations();
			VerbsTrainerService<Verb> service = new VerbsTrainerService<>(new ArrayList<Verb>(db.getAllVerbs()),
					terminal, reader);
			service.run();
		} catch (IOException e) {
			System.out.println("I/O error");
		}
	}
}
