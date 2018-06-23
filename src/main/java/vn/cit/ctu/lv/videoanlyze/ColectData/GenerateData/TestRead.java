package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import java.awt.Frame;
import java.util.Base64;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import vn.cit.ctu.lv.videoanlyze.ColectData.Util.CreateCameraFromUrl;

public class TestRead {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture video = CreateCameraFromUrl.create("0");
		System.out.println(video.isOpened());
		Mat mat = new Mat();
		CreatData creatData = new CreatData();
		int i=0;
		while(video.read(mat)) {
			String data =creatData.setFrame(mat)
				.setHeight(480)
				.setWith(640)
				.create();
			System.out.println(data);
			
		}
		
	}
}
