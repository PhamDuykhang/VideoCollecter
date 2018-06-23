package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.log4j.Logger;

public class CreateKafkaStream {
	private static final Logger logger = Logger.getLogger(CreatData.class) ;
	private String topicname;
	private String cameraids;
	private String cameraUrls;
	private KafkaProducer<String, String> producer;
	public CreateKafkaStream() {
		
	}
	public CreateKafkaStream setTopicname(String topicname) {
		this.topicname = topicname;
		return this;
	}
	public CreateKafkaStream setCameraids(String cameraids) {
		this.cameraids = cameraids;
		return this;
	}
	public CreateKafkaStream setCameraUrls(String cameraUrls) {
		this.cameraUrls = cameraUrls;
		return this;
	}
	public CreateKafkaStream setProducer(KafkaProducer<String, String> producer) {
		this.producer = producer;
		return this;
	}
	public void create () {
		String[] urls = this.cameraUrls.split(",");
		String[] ids = this.cameraids.split(",");
		if(urls.length != ids.length) {
			throw new IllegalArgumentException("camera url not same camera id");
		}
		logger.info("Total camera: "+urls.length);
		for(int i=0;i<urls.length; i++) {
			logger.info("camera: "+ids[i]+ " In process!");
			Thread thread = new Thread(new VideoGenerate(ids[i].trim(),urls[i].trim(),producer,topicname));
			thread.start();
		}
		
	}
	
	
}
