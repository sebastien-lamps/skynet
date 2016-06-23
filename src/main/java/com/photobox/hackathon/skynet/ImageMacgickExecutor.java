package com.photobox.hackathon.skynet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ImageMacgickExecutor implements IPictureProcessor {
	private final static Logger logger = LoggerFactory.getLogger(SystemCmd.class);

	public void execute(String imageIn, String imageOut, Parameters parameters) throws PictureProcessorException {
		try {
			String cmd = buildCommandLine(imageIn, imageOut, parameters);
			SystemCmd.run(cmd, "Image enhancer");
		} catch (Exception e) {
			throw new PictureProcessorException("Exception during image enhancer", e);
		}
	}

	private String buildCommandLine(String imageIn, String imageOut, Parameters parameters) {
		String imageEnhancerBaseFolder = "/usr/bin/";
		String imageEnhancerExecName = "convert";

		File fileIn = new File(imageIn);
		File fileOut = new File(imageOut);
		if (!fileIn.exists()) throw new PictureProcessorException("Image in not found");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(imageEnhancerBaseFolder).append(imageEnhancerExecName)
				.append(" \"").append(fileIn.getAbsolutePath()).append("\"")
				.append(" ").append(parameters.getMainParameter());
		for (String subParameter : parameters.getSubParameters()) {
			stringBuilder.append(" ").append(subParameter);
		}
		stringBuilder.append(" \"").append(fileOut.getAbsolutePath()).append("\"");


		logger.debug(stringBuilder.toString());
		return stringBuilder.toString();
	}
}
