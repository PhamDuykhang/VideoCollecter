package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import vn.cit.ctu.lv.videoanlyze.ColectData.Util.CreateCameraFromUrl;

public class TestRead {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture video = CreateCameraFromUrl.create("0");
		System.out.println(video.isOpened());
		Mat mat = new Mat();
		CreatData creatData = new CreatData();
		while(video.read(mat)) {
			String data =creatData.setFrame(mat)
					.setHeight(480)
					.setWith(640)
					.create();
			System.out.println(data);
		}
		
	}
}
