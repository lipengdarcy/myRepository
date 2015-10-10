package com.runlion.shop.controller.home.center.help;

public class MailBinding {
	public String generateCode() {
		String codeValue = "";
		for (int i = 0; i < 20; i++) {
			String rand = getRandomChar();
			codeValue = codeValue.concat(rand);
		}
		return codeValue;
	}

	public static String getRandomChar() {
		int index = (int) Math.round(Math.random() * 2);
		String randChar = "";
		switch (index) {
		case 0:// 大写字符
			randChar = String
					.valueOf((char) Math.round(Math.random() * 25 + 65));
			break;
		case 1:// 小写字符
			randChar = String
					.valueOf((char) Math.round(Math.random() * 25 + 97));
			break;
		default:// 数字
			randChar = String.valueOf(Math.round(Math.random() * 9));
			break;
		}
		return randChar;
	}
}
