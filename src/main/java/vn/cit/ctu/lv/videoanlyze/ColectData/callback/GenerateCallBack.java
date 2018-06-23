package vn.cit.ctu.lv.videoanlyze.ColectData.callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

import vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData.VideoGenerate;

public class GenerateCallBack implements Callback {
	private static final Logger logger = Logger.getLogger(VideoGenerate.class);
	private String cameraID;
	public GenerateCallBack(String cameraid) {
		// TODO Auto-generated constructor stub
		super();
		this.cameraID= cameraid;
	}

	@Override
	public void onCompletion(RecordMetadata arg0, Exception arg1) {
		// TODO Auto-generated method stub
		if(arg0!=null) {
			logger.info("cameraId="+ this.cameraID + " partition=" + arg0.partition());

		}
		if(arg1!=null) {
			arg1.printStackTrace();
		}

	}

}
