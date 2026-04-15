package io.github.bulldogboy.iverbstrainer;

public class Verb {
	public Integer id;
	public String infinitive;
	public String pastSimple;
	public String pastParticiple;
	public String translation;
	public String dataGroup;

	public Verb(Integer id, String infinitive, String pastSimple, String pastParticiple, String translation,
			String dataGroup) {
		this.id = id;
		this.infinitive = infinitive;
		this.pastSimple = pastSimple;
		this.pastParticiple = pastParticiple;
		this.translation = translation;
		this.dataGroup = dataGroup;
	}

	public Integer getId() {
		return id;
	}

	public String getInfinitive() {
		return infinitive;
	}

	public String getPastSimple() {
		return pastSimple;
	}

	public String getPastParticiple() {
		return pastParticiple;
	}

	public String getTranslation() {
		return translation;
	}

	public String getDataGroup() {
		return dataGroup;
	}

}