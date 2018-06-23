package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;

import vn.cit.ctu.lv.videoanlyze.ColectData.Util.ReadProperty;

public class VideoStramKafkaProducer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Properties prop = new ReadProperty().setPropertyName(ReadProperty.DEFAULT_PATCH).readProperty();	
		Properties properties = new Properties();
		properties.put("bootstrap.servers", prop.getProperty("kafka.bootstrap.servers"));
		properties.put("acks", prop.getProperty("kafka.acks"));
		properties.put("retries",prop.getProperty("kafka.retries"));
		properties.put("batch.size", prop.getProperty("kafka.batch.size"));
		properties.put("linger.ms", prop.getProperty("kafka.linger.ms"));
		properties.put("max.request.size", prop.getProperty("kafka.max.request.size"));
		properties.put("compression.type", prop.getProperty("kafka.compression.type"));
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String , String> producer = new KafkaProducer<String, String >(properties);
		CreateKafkaStream createKafkaStream = new CreateKafkaStream();
		createKafkaStream
			.setCameraids(prop.getProperty("camera.id"))
			.setCameraUrls(prop.getProperty("camera.url"))
			.setProducer(producer)
			.setTopicname(prop.getProperty("kafka.topic"))
			.create();
		
	}

}
