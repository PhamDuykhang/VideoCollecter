/**
 * 
 */
package vn.cit.ctu.lv.videoanlyze.ColectData;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import vn.cit.ctu.lv.videoanlyze.ColectData.Util.CreateCameraFromUrl;

/**
 * @author pdkhang
 *
 */
public class VideoGenerateTest {
	@Test
	public void GivenValidUrlWhenCreateCameraThenReturn() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture camera = CreateCameraFromUrl.create("0");
		assertThat(CreateCameraFromUrl.isWorking(camera, 1000, 2), Is.is(true));
	
	}
	@Test (expected = IllegalArgumentException.class)
	public void GivenInvalidUrlWhenCreateCameraThenExceptionBeThrown() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture camera = CreateCameraFromUrl.create("D:/BigDataVideoAnalyze/sample-video/sample.mp4");
		CreateCameraFromUrl.isWorking(camera, 1000, 2);
	}
}
