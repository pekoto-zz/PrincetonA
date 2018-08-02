package com.pekoto.algorithms;

import java.util.HashMap;
import java.util.Random;

/*
 * A class to encode and decode URls.
 * There are a number of ways of doing this.
 * Using this random encoding approach has a number of advantages:
 * 1. Makes it hard to predict the URL
 * 2. Can guarantee each URL has the same length
 * 3. We can increase the number of encodings by extending the length of the encoded string,
 * 		reducing the probability of getting duplicate encodings
 * 
 * 
 * Number of URLs: (10 + 26 * 2)^6
 * (10 digits, 26 letters upper and lower case, choose 6)
 */
public class UrlEncoder {
	
	private HashMap<String, String> urlMap = new HashMap<String, String>();
	private String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Random rand = new Random();
	private String key = "";
	
	private int keyLen = 6;
	
	private String getRandomKey() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < keyLen; i++) {
			sb.append(alphabet.charAt(rand.nextInt(62)));
		}
		
		return sb.toString();
	}
	
	public String encode(String longUrl) {
		while(urlMap.containsKey(key) || key.equals("")) {
			key = getRandomKey();
		}
		
		urlMap.put(key, longUrl);
		
		return key;
	}
	
	public String decode(String shortUrl) {
		if(!urlMap.containsKey(shortUrl)) {
			return "http://myencodingsite.com/404";
		}
		
		return urlMap.get(shortUrl);
	}
}
