package com.ink.user.util;

import java.util.Calendar;

public class SeqUtils {

	public SeqUtils() {
	}

	public static Long getId(SerialNumber serialNumber) {

		int seed = java.util.concurrent.ThreadLocalRandom.current()
				.nextInt(999) + 1;
		if (seed < 100 && seed > 9) {
			seed = 10 * seed;
		}
		if (seed < 10) {
			seed = 100 * seed;
		}
		int seed1 = java.util.concurrent.ThreadLocalRandom.current()
				.nextInt(99) + 1;
		if (seed1 < 10) {
			seed1 = seed1 * 10;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(serialNumber.getNcode());
		sb.append(getYearAndMon());
		sb.append(seed1);
		sb.append(getSec());
		sb.append(seed);
		return Long.valueOf(sb.toString());
	}

	private static Long getSec() {
		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		long min = hours * 60 + minutes;
		int x = (int) (Math.random() * 10);
		if (x == 0) {
			x = 1;
		}
		long sec = x * 100000 + (min * 60 + seconds);
		return sec;
	}

	private static String getYearAndMon() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		return "" + year;
	}

	public enum SerialNumber {
		MERCHANT("8000", "MERCHANT"), CHANNEL("9000", "CHANNEL"), CUSTACC(
				"1000", "CUSTACC"), INTERNALACC("2000", "INTERNALACC"),ORG("3000", "ORG");

		private String nCode;
		private String code;

		private SerialNumber(String _nCode, String code) {
			this.nCode = _nCode;
			this.code = code;
		}

		public String getNcode() {
			return this.nCode;
		}

		public String getCode() {
			return this.code;
		}

		@Override
		public String toString() {
			return String.valueOf(this.nCode + this.code);
		}
	}

	protected static String getSerialNumber(SerialNumber serialNumber) {
		return serialNumber.getNcode();
	}
	public static void main(String[] args) {
		System.out.println(getId(SerialNumber.CUSTACC));
	}

}
