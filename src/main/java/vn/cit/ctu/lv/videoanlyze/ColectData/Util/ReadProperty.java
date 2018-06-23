package vn.cit.ctu.lv.videoanlyze.ColectData.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperty {
	private static Properties prop;
	public static final String DEFAULT_PATCH="kafka-Producer.properties";
	private static String propertyName;
	public ReadProperty () {
		this.prop = new Properties();
		this.propertyName = "";
	}
	public String getPropertyName() {
		return propertyName;
	}
	public  ReadProperty setPropertyName(String propertyName) {
		ReadProperty.propertyName = propertyName;
		return this;
	}
	public Properties readProperty() throws IOException {
		Properties prob = new Properties();
		InputStream input = ReadProperty.class.getClassLoader().getResourceAsStream(propertyName);
		prob.load(input);
		return prob;
	}
}
