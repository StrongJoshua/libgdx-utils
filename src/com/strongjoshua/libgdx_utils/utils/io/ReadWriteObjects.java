/**
 * Copyright 2015 StrongJoshua (swampert_555@yahoo.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.strongjoshua.libgdx_utils.utils.io;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * I haven't tested these methods recently, but they allow for reading and writing of values to files.
 * 
 * @author StrongJoshua
 * 
 */
public class ReadWriteObjects
{
	private static Object endObject = new String("!end!");

	/**
	 * Reads a specific value from memory.
	 * 
	 * @param file The file to read from (done for efficiency).
	 * @param token The token assigned to the value.
	 * @return The value.
	 */
	public static Object readValue(String file, Object token)
	{
		FileHandle fh = Gdx.files.local(file);

		if(fh.length() == 0)
			return null;

		try
		{
			ObjectInputStream oi = new ObjectInputStream(fh.read());

			while(true)
			{
				Object o = oi.readObject();
				if(o.equals(endObject))
					break;
				Object v = oi.readObject();
				if(o.equals(token))
				{
					oi.close();
					return v;
				}
			}
			oi.close();
			return null;
		} catch(Exception e)
		{
			System.out.println("Could not read.\n");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Writes a specific value to memory.
	 * 
	 * @param file The file to write the value to (done for efficiency).
	 * @param token The token the value is to be assigned to.
	 * @param val The value to be written.
	 */
	public static void writeValue(String file, Object token, Object val)
	{
		FileHandle fh = Gdx.files.local(file);
		FileHandle tmp = FileHandle.tempFile("tmp");

		boolean exists = false;

		ObjectOutputStream oo = null;
		ObjectInputStream oi = null;

		try
		{
			oo = new ObjectOutputStream(tmp.write(false));

			if(fh.length() != 0)
			{
				oi = new ObjectInputStream(fh.read());

				while(true)
				{
					Object o = oi.readObject();
					if(o.equals(endObject))
					{
						if(exists)
							oo.writeObject(endObject);
						break;
					}
					Object v = oi.readObject();
					if(o.equals(token))
					{
						exists = true;
						v = val;
					}
					oo.writeObject(o);
					oo.writeObject(v);
				}
				oi.close();
			}

			if(!exists)
			{
				oo.writeObject(token);
				oo.writeObject(val);
				oo.writeObject(endObject);
			}

			oo.close();

			tmp.copyTo(fh);
		} catch(Exception e)
		{
			System.out.println("Could not save.");
			System.out.println();
			e.printStackTrace();
		}
	}

	/**
	 * Removes a value from the specified memory file.
	 * 
	 * @param file The file to be accessed.
	 * @param token The token for the value to be removed (is removed as well).
	 */
	public static void removeValue(String file, Object token)
	{
		FileHandle fh = Gdx.files.local(file);
		FileHandle tmp = FileHandle.tempFile("tmp");

		ObjectOutputStream oo = null;
		ObjectInputStream oi = null;

		try
		{
			oo = new ObjectOutputStream(tmp.write(false));

			if(fh.length() != 0)
			{
				oi = new ObjectInputStream(fh.read());

				while(true)
				{
					Object o = oi.readObject();
					if(o.equals(endObject))
					{
						oo.writeObject(endObject);
						break;
					}
					if(!o.equals(token))
					{
						oo.writeObject(o);
						oo.writeObject(oi.readObject());
					}
				}
				oi.close();
			}
			oo.close();
			tmp.copyTo(fh);
		} catch(Exception e)
		{
			System.out.println("Could not remove.\n");
			e.printStackTrace();
		}
	}
}
