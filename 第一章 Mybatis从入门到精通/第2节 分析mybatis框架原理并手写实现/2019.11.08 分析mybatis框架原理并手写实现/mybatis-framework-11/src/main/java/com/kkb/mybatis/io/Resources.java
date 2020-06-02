package com.kkb.mybatis.io;

import java.io.InputStream;
import java.io.Reader;

public class Resources {

	public static InputStream getResourceAsStream(String location) {
		return Resources.class.getClassLoader().getResourceAsStream(location);
	}

	public static Reader getResourceAsReader(String location) {
		return null;
	}
}
