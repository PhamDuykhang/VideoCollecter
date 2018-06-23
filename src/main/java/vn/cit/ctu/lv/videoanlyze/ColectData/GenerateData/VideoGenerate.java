package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import org.opencv.videoio.VideoCapture;



import vn.cit.ctu.lv.videoanlyze.ColectData.Util.CreateCameraFromUrl;
import vn.cit.ctu.lv.videoanlyze.ColectData.callback.GenerateCallBack;

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
		logger.info("Processing cameraId "+this.cameraID+" with url "+cameraURL);
		try {
			GenerateData(this.cameraID,this.cameraURL,producer,topicName);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}
	private void GenerateData(String cameraID, String cameraURL,KafkaProducer<String,String>producer ,String topiname) {
		VideoCapture camera=null;
		camera = CreateCameraFromUrl.create(cameraURL);
		try {
			CreateCameraFromUrl.isWorking(camera, 1000, 2);
		} catch (IllegalArgumentException e) {
			logger.error("Camera id: "+ cameraID+ " with "+ cameraURL+" not working!!! ");
		}
		Mat frame = new Mat();
		CreatData creatdata = new CreatData();
		while(camera.read(frame)) {
			String data =creatdata.setFrame(frame)
				.setHeight(480)
				.setWith(640)
				.create();
			logger.info("Sending!! "+data);
		
			producer.send(new ProducerRecord<String, String>(topiname,cameraID,data),new GenerateCallBack(cameraID));
			creatdata.clean();			
		}
		camera.release();
		frame.release();
		
		
	}

}
