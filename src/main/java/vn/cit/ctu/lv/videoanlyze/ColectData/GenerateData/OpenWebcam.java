package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

import vn.cit.ctu.lv.videoanlyze.ColectData.Util.CreateCameraFromUrl;

public class OpenWebcam {
	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		VideoCapture camera = CreateCameraFromUrl.create("0");
		
	}
}
