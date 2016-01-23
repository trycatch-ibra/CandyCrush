package md.cc.controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSound {

	public static void play() throws LineUnavailableException {

		AudioInputStream audioInputStream = null;
		try {

			audioInputStream = AudioSystem.getAudioInputStream(new File("/home/trycatch/git/CandyCrush/656.wav"));
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
			line.start();

			byte bytes[] = new byte[1024];
			int bytesRead = 0;
			while (((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1)) {
				line.write(bytes, 0, bytesRead);
			}

			line.close();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
