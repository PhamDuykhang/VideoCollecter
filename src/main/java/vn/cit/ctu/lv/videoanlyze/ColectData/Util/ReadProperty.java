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
	public static String getPropertyName() {
		return propertyName;
	}
	public static void setPropertyName(String propertyName) {
		ReadProperty.propertyName = propertyName;
	}
	public Properties readProperty() throws IOException {
		Properties prob = new Properties();
		InputStream input = ReadProperty.class.getClassLoader().getResourceAsStream(propertyName);
		prob.load(input);
		return prob;
	}
}
