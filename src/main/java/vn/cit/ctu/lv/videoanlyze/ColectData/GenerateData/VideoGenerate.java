package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
	private void GenerateData(String cameraID, String cameraURL,KafkaProducer<String,String>producer ,String topiname) throws IOException {
		VideoCapture camera=null;
		camera = CreateCameraFromUrl.create(cameraURL);
		try {
			CreateCameraFromUrl.isWorking(camera, 1000, 2);
		} catch (IllegalArgumentException e) {
			logger.error("Camera id: "+ cameraID+ " with "+ cameraURL+" not working!!! ");
		}
		Mat frame = new Mat();
		Gson gson = new Gson();
		 FileWriter writer = new FileWriter("D:/testout.txt");
	     BufferedWriter buffer = new BufferedWriter(writer);
		while(camera.read(frame)) {
			Imgproc.resize(frame, frame, new Size(640, 480), 0, 0, Imgproc.INTER_CUBIC);
			int cols = frame.cols();
	        int rows = frame.rows();
	        int type = frame.type();
			byte[] data = new byte[(int) (frame.total() * frame.channels())];
			frame.get(0, 0, data);
	        String timestamp = new Timestamp(System.currentTimeMillis()).toString();
			JsonObject obj = new JsonObject();
			obj.addProperty("cameraId",cameraID);
	        obj.addProperty("timestamp", timestamp);
	        obj.addProperty("rows", rows);
	        obj.addProperty("cols", cols);
	        obj.addProperty("type", type);
	        obj.addProperty("data", Base64.getEncoder().encodeToString(data));  
	        String json = gson.toJson(obj);
	       
	        buffer.write(json);
	        buffer.write("\n");
	        buffer.flush();
			logger.info("Sending!! "+json);
		
			producer.send(new ProducerRecord<String, String>(topiname,cameraID,json),new GenerateCallBack(cameraID));
//			creatdata.clean();			
		}
		camera.release();
		frame.release();
		
		
	}

}
