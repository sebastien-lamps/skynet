/**
 * $Licence$
 */
package com.photobox.hackathon.skynet;

import java.io.File;

/**
 * Interface for all Picture Processor, independently of the kind of
 * implementation (ImageMagick or GraphicsMagick)
 *
 * @author $Author$
 * @version $Revision$
 * @copyright $Copyright$
 * @lastrevision $Date$
 * @modifiedby $LastChangedBy$
 * @lastmodified $LastChangedDate$
 */
public interface IPictureProcessor {

	/**
	 * Execute a processor
	 *
	 * @throws PictureProcessorException
	 */
	void execute(String imageIn, String imageOut, Parameters parameters) throws PictureProcessorException;

}
