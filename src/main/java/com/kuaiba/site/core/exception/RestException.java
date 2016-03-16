/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package com.kuaiba.site.core.exception;

/**
 * REST 请求异常
 * 
 * @author larry.qi
 *
 */
public class RestException extends SecurityException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception with an error code that maps to {@link ErrorIDs} and
	 * message text.
	 */
	public RestException(String msg) {
		super(ErrorIDs.REST_FAILIED, msg);
	}

	/**
	 * Create exception with error id, message and related exception.
	 */
	public RestException(String msg, Exception e) {
		super(ErrorIDs.REST_FAILIED, msg, e);
	}
}