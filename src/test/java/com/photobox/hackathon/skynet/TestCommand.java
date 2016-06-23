package com.photobox.hackathon.skynet;

import org.junit.Test;

import java.io.File;

import static java.util.Arrays.asList;

/**
 * Created by slamps on 23/06/16.
 */
public class TestCommand {

	//@Test
	public void testImageMacgickExecutor() {
		ImageMacgickExecutor executor = new ImageMacgickExecutor();
		Parameters parameters = new Parameters();
		parameters.setMainParameter("+clone -channel A  -blur 0x2.5 -level 0,50% +channel  -compose DstOver  -composite ");
		parameters.setSubParameters(asList(""));
		executor.execute("src/test/resources/in/Terminator-Genisys.jpg", "src/test/resources/in/Terminator-Genisys-test.jpg", parameters);
	}

	@Test
	public void testGMicExecutor() {
		GMicExecutor executor = new GMicExecutor();
		Parameters parameters = new Parameters();
		parameters.setMainParameter(" -rodilius ");
		parameters.setMainParameter(" -texturize_paper ");
//		parameters.setSubParameters(asList("12,10,300,10"));

		File dir = new File("src/test/resources/in");
		File out = new File("src/test/resources/out");
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				executor.execute(
						file.getAbsolutePath(),
						out.getAbsolutePath() + File.separator + file.getName(),
						parameters);
			}
		}


	}
}
