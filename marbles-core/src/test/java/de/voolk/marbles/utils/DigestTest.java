package de.voolk.marbles.utils;

import junit.framework.Assert;

import org.junit.Test;

public class DigestTest {

	@Test
	public void password() {
		String testDigest = new Digest().digest("test");
		Assert.assertEquals("098f6bcd4621d373cade4e832627b4f6", testDigest);
		
		System.out.println("test: " + testDigest);
	}
}
