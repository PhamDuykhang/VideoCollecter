package vn.cit.ctu.lv.videoanlyze.ColectData.GenerateData;

import java.sql.Timestamp;
import java.util.Base64;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CreatData {
	private int col;
	private int row;
	private int with;
	private int height;
	private Mat frame;
	private Gson data;
	private int type;
	public CreatData() {
		data= new Gson();
	}
	public CreatData setWith(int with) {
		this.with = with;
		return this;
	}
	public CreatData setHeight(int height) {
		this.height = height;
		return this;
	}
	public CreatData setFrame(Mat frame) {
		this.frame = frame;
		return this;
	}
	public void clean() {
		this.frame=null;
	}
	public String create() {
		Imgproc.resize(this.frame, this.frame, new Size(with,height),0,0,Imgproc.INTER_CUBIC);
		this.col = frame.cols();
		this.row = frame.rows();
		this.type = frame.type();
		byte[] data = new byte[(int) (frame.total() * frame.channels())];
		this.frame.get(row, col, data);
		String timestamp = new Timestamp(System.currentTimeMillis()).toString();
		Imgcodecs.imwrite(timestamp+".jpg", this.frame);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("timestamp", timestamp);
		jsonObject.addProperty("rows",row);
		jsonObject.addProperty("col",col);
		jsonObject.addProperty("type",type);
		jsonObject.addProperty("data",Base64.getEncoder().encodeToString(data));
		String json = this.data.toJson(jsonObject);
		return json;
		
	}
}
