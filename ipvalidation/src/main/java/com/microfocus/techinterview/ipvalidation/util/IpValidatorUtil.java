package com.microfocus.techinterview.ipvalidation.util;

import java.util.Stack;

public class IpValidatorUtil {

	public static int[] stringToBinary(String[] octets) {
		int binary[] = new int[32];
		setBinaryArray(binary, Integer.parseInt(octets[0]), 0);
		setBinaryArray(binary, Integer.parseInt(octets[1]), 1);
		setBinaryArray(binary, Integer.parseInt(octets[2]), 2);
		setBinaryArray(binary, Integer.parseInt(octets[3]), 3);
		return binary;
	}

	public static int[] binaryToDecimal(int[] octets) {
		int[] decimal = new int[4];
		setDecimalArray(octets, decimal, 0);
		setDecimalArray(octets, decimal, 1);
		setDecimalArray(octets, decimal, 2);
		setDecimalArray(octets, decimal, 3);
		return decimal;
	}

	public static int[] stringToDecimal(String[] octets) {
		int[] decimal = new int[4];
		decimal[0] = Integer.parseInt(octets[0]);
		decimal[1] = Integer.parseInt(octets[1]);
		decimal[2] = Integer.parseInt(octets[2]);
		decimal[3] = Integer.parseInt(octets[3]);
		return decimal;
	}

	public static boolean between(Long val, Long min, Long max) {
		return val > min && val < max;
	}

	private static void setBinaryArray(int binary[], int octet, int pos) {
		int start = pos * 8, end = start + 8 - 1;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = start; i <= end; i++) {
			stack.push(octet % 2);
			octet = octet / 2;
		}
		for (int i = start; i <= end; i++)
			binary[i] = stack.pop();
	}

	private static void setDecimalArray(int binaryOctet[], int decimal[], int pos) {
		int tmp = 0, index = 7, start = pos * 8, end = start + 8;
		for (int i = start; i < end; i++) {
			tmp = tmp + (int) (Math.pow(2, index)) * binaryOctet[i];
			index--;
		}
		decimal[pos] = tmp;
	}
}
