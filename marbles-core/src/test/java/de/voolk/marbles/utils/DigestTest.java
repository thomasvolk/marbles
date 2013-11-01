/**
 * Copyright (C) 2010  Thomas Volk
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
