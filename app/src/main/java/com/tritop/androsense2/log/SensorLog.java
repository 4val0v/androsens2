/*
    Copyright (C) 2013-2014 Christian Schneider
    christian.d.schneider@googlemail.com
    
    This file is part of Androsens 2.

    Androsens 2 is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Androsens 2 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Androsens 2.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.tritop.androsense2.log;

import java.io.IOException;
import java.util.LinkedList;

import android.content.Context;
import android.util.Log;

public class SensorLog extends LogItem {

	LinkedList<String> data= new LinkedList<String>();
	
	
	public SensorLog(String type) throws IOException {
		super(type);
	}
	
	
	public SensorLog(String sensorName, String directoryName) throws IOException {
		super(sensorName, directoryName);
		writeHeader();
	}

	
	/**
	 * Writes CVS header to our logfile
	 * @throws IOException
	 */
	public void writeHeader() throws IOException{
		this.setmLogId(false);
		this.setmLogTimestamp(false);
		this.data.clear();
		this.data.add("ID");
		this.data.add("TIMESTAMP");
		this.data.add("X");
		this.data.add("Y");
		this.data.add("Z");
		this.log(this.data);
		this.setmLogId(true);
		this.setmLogTimestamp(true);
	}
	
	
	/**
	 * Logs a new sensor event
	 * @param data
	 * @throws IOException
	 */
	public void logEvent(float[] data) throws IOException{
		this.data.clear();
		for(float event:data){
			this.data.add(String.valueOf(event));
		}
      this.log(this.data);
	}
	

	/**
	 * Init a rescan of the log directory.
	 * This will make the new files appear in MTP.
	 * @param ctx
	 */
	public void rescanDir(Context ctx){
		this.rescanFiles(this.getLogDirectory(), ctx);
	}
	
	
	/**
	 * Close Log file.
	 * @param ctx
	 * @throws IOException
	 */
	public void close(Context ctx) throws IOException{
		closeLogFile(ctx);
	}
	
	
}
