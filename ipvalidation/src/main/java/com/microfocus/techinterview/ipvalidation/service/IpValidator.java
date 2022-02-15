package com.microfocus.techinterview.ipvalidation.service;

import static com.microfocus.techinterview.ipvalidation.util.IpValidatorUtil.between;
import static com.microfocus.techinterview.ipvalidation.util.IpValidatorUtil.binaryToDecimal;
import static com.microfocus.techinterview.ipvalidation.util.IpValidatorUtil.stringToBinary;
import static com.microfocus.techinterview.ipvalidation.util.IpValidatorUtil.stringToDecimal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

/**
 * Class {@code IpValidator} validates if provided IP address is in the range defined by cidrRange.
 * 
 * @author Yohannes
 * @since 1.0
 */

@Service
public class IpValidator {
	private static final String ip_regex = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
	private static final String cidr_regex = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(\\/([0-9]|[1-2][0-9]|3[0-2]))?$";
	
	/**
     * Implement the function that validates if provided IP address is in the range defined by cidrRange parameter.
     * You can read about CIDR here https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing
     * Also, you can use this page https://www.ipaddressguide.com/cidr to understand better CIDR concept.
     *
     * Code organization, implementing other functions that are called from validateAddress - all of this up to you.
     * The final code must be compilable, buildable and tested.
     *
     * You are free to use any build framework to build the code (maven, gradle, ant, etc.).
     *
     * You can use any java version for development (8, 9, 10, 11).
     *
     * You can use any libraries to simplify any auxiliary logic (e.g. string parsing).
     * But you cannot use any 3rd party libraries to implement validation itself.
     *
     * Testing framework selection is up to you. Testing the logic from "public static void main" is also OK.
     *
     * If you are familiar with Git, do your work in a branch and create pull request.
     *
     * @param ipAddress String representation of IP address that needs to be validated.
     *                  Function must verify that IP address definition itself is valid.
     *                  If IP address format is invalid, function must throw IllegalArgumentException.
     *                  E.g. 192.168.0.1 is correct definition of IP address.
     *                  256.0.0.1, 192,168.o.1, 192,168.0.1 are examples of invalid IP addresses.
     * @param cidrRange Classless Inter-Domain Routing (CIDR) definition that defines range of allowed IP addresses.
     *                  For example 11.1.0.0/24 CIDR defines IP addresses range from 11.1.0.0 to 11.1.0.255
     *                  Function must verify that cidrRange definition itself is valid.
     *                  If cidrRange format is invalid, function must throw IllegalArgumentException.
     *                  E.g. 192.168.0.0/24, 100.0.0.0/16, 10.10.0.0/32, 10.10.0.1/16 are valid definitions.
     *                  192.168.0.0/35, 300.0.0.0/16, 10.10,0.0/32, 10.10.0.256/16 are invalid definitions
     *                  If slash is omitted in cidrRange definition, you can assume that / 32 is used.
     *                  E.g. cidrRange 10.10.0.1 can be treated as 10.10.0.1/32
     * @throws IllegalArgumentException if either ipAddress or cidrRange definitions is invalid.
     * @return true if provided IP address is covered by the CIDR range; false otherwise.
     */
	public boolean validateIpAddress(String ipAddress, String cidrRange) throws IllegalArgumentException {

		if (!this.isValidIpAddress(ipAddress))
			throw new IllegalArgumentException("invalid ipAddress format");

		if (!this.isValidCidrRange(cidrRange))
			throw new IllegalArgumentException("invalid cidrRange format");

		String[] inputArray = cidrRange.split("/");
		String[] stringNidOctets = inputArray[0].split("\\.");
		int hostBits = 0;
		if (inputArray.length > 1)
			hostBits = 32 - Integer.parseInt(inputArray[1]);
		int[] binaryNidOctets = stringToBinary(stringNidOctets);
		int[] decimalNidOctets = getNetworkAddress(binaryNidOctets, hostBits);
		int[] decimalBidOctets = getBroadcastAddress(binaryNidOctets, hostBits);

		return isValidateIpAddress(ipAddress, decimalNidOctets, decimalBidOctets);
	}

	private int[] getNetworkAddress(int binary[], int hostBits) {
		int networkAddress[] = new int[32];
		for (int i = 0; i <= (31 - hostBits); i++)
			networkAddress[i] = binary[i];
		// Set 32-n bits to 0
		for (int i = 31; i > (31 - hostBits); i--)
			networkAddress[i] = 0;
		return binaryToDecimal(networkAddress);
	}

	private int[] getBroadcastAddress(int binary[], int hostBits) {
		int broadcastAddress[] = new int[32];
		for (int i = 0; i <= (31 - hostBits); i++)
			broadcastAddress[i] = binary[i];
		// Set 32-n bits to 1
		for (int i = 31; i > (31 - hostBits); i--)
			broadcastAddress[i] = 1;
		return binaryToDecimal(broadcastAddress);
	}

	private boolean isValidateIpAddress(String ipAddress, int[] nid, int[] bid) {
		int[] ip = stringToDecimal(ipAddress.split("\\."));
		StringBuffer stringNid = new StringBuffer();
		StringBuffer stringBid = new StringBuffer();
		StringBuffer stringIp = new StringBuffer();

		for (int i = 0; i <= 3; i++) {
			stringNid.append(nid[i]);
			stringBid.append(bid[i]);
			stringIp.append(ip[i]);
		}
		return between(Long.parseLong(stringIp.toString()), Long.parseLong(stringNid.toString()),
				Long.parseLong(stringBid.toString()));
	}

	private boolean isValidIpAddress(String ipAddress) {
		Pattern pattern = Pattern.compile(ip_regex);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

	private boolean isValidCidrRange(String cidrRange) {
		Pattern pattern = Pattern.compile(cidr_regex);
		Matcher matcher = pattern.matcher(cidrRange);
		return matcher.matches();
	}
}
