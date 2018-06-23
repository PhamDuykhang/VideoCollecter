package vn.cit.ctu.lv.videoanlyze.ColectData.Util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.opencv.videoio.VideoCapture;

import vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData.VideoGenerate;

public class CreateCameraFromUrl {
	private static final Logger logger = Logger.getLogger(CreateCameraFromUrl.class);
	public static VideoCapture create(String url) {
		
		if(StringUtils.isNumeric(url)) {
			return new VideoCapture(Integer.parseInt(url));
		}else {
			return new VideoCapture(url);
		}
	}
	public static boolean isWorking(VideoCapture camera,long waitTime,int retry) {
		boolean workingFlag=true;
		if(camera.isOpened()) {
			return workingFlag;
		}
		else{
			int i=0;
			while (i<retry) {
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(camera.isOpened()) {
					return workingFlag;
				}else {
					i++;
				}
			}
			logger.error("Camera: not working!! ");
			throw new IllegalArgumentException("Erorr camera not opend");
		}
		
	}
}
