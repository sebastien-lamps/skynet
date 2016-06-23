/**
 * $Licence$
 */
package com.photobox.hackathon.skynet;


public class PictureProcessorException extends TechnicalException {

	private static final long serialVersionUID = -8868083444501053997L;

	public PictureProcessorException(String message) {
		super(message);
	}

	public PictureProcessorException(String message, Exception e) {
		super(message, e);
	}
}
