package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.log4j.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import com.google.gson.Gson;

import vn.cit.ctu.lv.videoanlyze.ColectData.Util.CreateCameraFromUrl;

public class VideoGenerate implements Runnable {
	private static final Logger logger = Logger.getLogger(VideoGenerate.class);
	private String cameraID;
	private String cameraURL;
	private KafkaProducer<String, String> producer;
	private String topicName;
	
	public VideoGenerate(String cameraID, String cameraURL, KafkaProducer<String, String> producer, String topicName) {
		this.cameraID = cameraID;
		this.cameraURL = cameraURL;
		this.producer = producer;
		this.topicName = topicName;
	}
	
	public VideoGenerate() {
	
	}
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	private void GenerateData(String cameraID, String cameraURL,KafkaProducer<String,String>producer ,String topiname) {
		VideoCapture camera=null;
		camera = CreateCameraFromUrl.create(cameraURL);
		try {
			CreateCameraFromUrl.isWorking(camera, 1000, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Camera: "+cameraID+" with"+cameraURL+" not working!! ");
		}
		Mat mat = new Mat();
		Gson gson = new Gson();
		
		
		
	}

}
