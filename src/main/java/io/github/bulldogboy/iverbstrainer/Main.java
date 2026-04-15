package io.github.bulldogboy.iverbstrainer;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		DataOperations db = new DataOperations();
		VerbsTrainerService<Verb> service = new VerbsTrainerService<>(new ArrayList<Verb>(db.getAllVerbs()));
		service.run();
	}
}
