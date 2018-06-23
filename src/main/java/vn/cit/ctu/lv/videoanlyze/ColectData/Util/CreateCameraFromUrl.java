package vn.cit.ctu.lv.videoanlyze.ColectData.Util;

import org.apache.commons.lang.StringUtils;
import org.opencv.videoio.VideoCapture;

public class CreateCameraFromUrl {
	
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
			throw new IllegalArgumentException("Erorr camera not opend");
		}
		
	}
}
