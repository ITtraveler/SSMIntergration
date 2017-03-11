package com.hgs.ssm.mvc;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
/**
 * Êý¾Ý×ª»»
 * @author hgs
 *
 */
@Component
public class MyConverter implements Converter<String, User> {

	public User convert(String source) {
		
		String data[] = source.split("-");
		String name = data[0];
		String age = data[1];
		String address = data[2];
		System.out.println(name+ "   "+age+"  "+address);
		return new User(name, age, new Address(address));
	}

}
