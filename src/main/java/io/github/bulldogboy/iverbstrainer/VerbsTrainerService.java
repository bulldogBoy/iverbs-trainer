package io.github.bulldogboy.iverbstrainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.InfoCmp;

public class VerbsTrainerService<T extends Verb> {
	public VerbsTrainerService(ArrayList<T> vbs, Terminal tr, LineReader lr) {
		this.vbs = vbs;
		this.tr = tr;
		this.lr = lr;
		bindKeyMaps();
	}

	KeyMap<String> keyMap = new KeyMap<>();

	private void bindKeyMaps() {
		keyMap.bind("q", "q");
		keyMap.bind("w", "w");
		keyMap.bind("e", "e");
	};

	Terminal tr;
	LineReader lr;
	Random random = new Random();
	private List<T> vbs;

	public void run() throws IOException {
		for (int i = 0; vbs.size() > i;) {
			new VerbChecker(vbs.remove(i));
		}

		/*
		 * A working version with random values for(int i;vbs.size() > 0;) { i =
		 * random.nextInt(vbs.size()); System.out.println(vbs.get(i).translation);
		 * System.out.println(count++); vbs.remove(i); }
		 */
	};

	private class VerbChecker {
		AttributedStringBuilder builder = new AttributedStringBuilder();
		private final String KEY_Q = "q";
		private final String KEY_W = "w";
		private final String KEY_E = "e";

		String inf;
		String ps;
		String pp;

		enum Status {
			INF(false, ""), PS(false, ""), PP(false, "");

			private Boolean beenChecked;
			private String meaning;

			Status(Boolean beenChecked, String meaning) {
				this.beenChecked = beenChecked;
				this.meaning = meaning;
			}

			public Boolean getChecked() {
				return beenChecked;
			}

			public void setChecked(Boolean val) {
				this.beenChecked = val;
			}

			public String getMeaning() {
				return meaning;
			}

			public void setMeaning(String val) {
				this.meaning = val;
			}
		}

		Map<String, String> bindsToValue = new HashMap<>();

		private void generateRandomBinds() {
			List<String> value = new ArrayList<>(Arrays.asList(inf, ps, pp));
			List<String> keys = new ArrayList<>(Arrays.asList(KEY_Q, KEY_W, KEY_E));
			keys.forEach(item -> {
				bindsToValue.put(item, value.remove(random.nextInt(value.size())));
			});
		};

		private void generateOutMessage(String nameOfTime, String translation) {
			tr.puts(InfoCmp.Capability.clear_screen);
			tr.flush();
			 AttributedString translationText = builder.style(AttributedStyle.BOLD.foreground(AttributedStyle.BLUE))
			            .append(translation.toUpperCase())
			            .style(AttributedStyle.BOLD)
			            .toAttributedString();
			 tr.writer().println(translationText.toAnsi());
			 builder = new AttributedStringBuilder();
			 
			  AttributedString chooseFormText = builder.append("Choose the ")
			            .style(AttributedStyle.DEFAULT.underline().italic().foreground(AttributedStyle.MAGENTA))
			            .append(nameOfTime)
			            .style(AttributedStyle.DEFAULT)
			            .append(" form")
			            .toAttributedString();
			    builder = new AttributedStringBuilder();
			    tr.writer().println(chooseFormText.toAnsi());
			    
			tr.writer().println("Q) " + bindsToValue.get(KEY_Q));
			tr.writer().println("W) " + bindsToValue.get(KEY_W));
			tr.writer().println("E) " + bindsToValue.get(KEY_E));
		};

		private void validateAnswer(Status currItem, Boolean firstSeal) throws IOException {
			if (!firstSeal) {
				 AttributedString wrongText = builder.style(AttributedStyle.BOLD.foreground(AttributedStyle.RED))
				            .append("Wrong answer")
				            .style(AttributedStyle.BOLD)
				            .toAttributedString();
				 tr.writer().println(wrongText.toAnsi());
				 builder = new AttributedStringBuilder();
			}
			BindingReader bindingReader = new BindingReader(tr.reader());
			String key = bindingReader.readBinding(keyMap);
			if (!currItem.getMeaning().equals(bindsToValue.get(key.toLowerCase()))) {
				validateAnswer(currItem, false);
			} else {
				currItem.setChecked(true);
				AttributedString correctlyText = builder.style(AttributedStyle.BOLD.foreground(AttributedStyle.GREEN))
			            .append("Correctly")
			            .style(AttributedStyle.BOLD)
			            .toAttributedString();
			 tr.writer().println(correctlyText.toAnsi());
			 builder = new AttributedStringBuilder();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		};

		public VerbChecker(Verb verb) throws IOException {
			this.inf = verb.infinitive;
			this.ps = verb.pastSimple;
			this.pp = verb.pastParticiple;
			generateRandomBinds();
			Status.INF.setChecked(false);
			Status.PS.setChecked(false);
			Status.PP.setChecked(false);
			Status.INF.setMeaning(inf);
			Status.PS.setMeaning(ps);
			Status.PP.setMeaning(pp);
			List<Status> status = new ArrayList<>(Arrays.asList(Status.values()));
			for (int i = 0; i < status.size(); i++) {
				Status item = status.get(i);
				switch (item) {
				case INF:
					if (!item.getChecked()) {
						generateRandomBinds();
						generateOutMessage(" Infinitive I ", verb.translation);
						validateAnswer(item, true);
						break;
					}
				case PS:
					if (!item.getChecked()) {
						generateRandomBinds();
						generateOutMessage(" Past Simple II ", verb.translation);
						validateAnswer(item, true);
						break;
					}
				case PP:
					if (!item.getChecked()) {
						generateRandomBinds();
						generateOutMessage(" Past Participle III ", verb.translation);
						validateAnswer(item, true);
						break;
					}
					break;
				}
			}

		}

	}
}
