package org.apache.maven.plugins.dependency;

import org.apache.maven.plugin.logging.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/** Helper to do assertions on what is being logged. */
public class CapturingLog
    implements Log
{
    StringBuilder sb = new StringBuilder();

    /** {@inheritDoc} */
    public void debug( CharSequence content )
    {
        print( "debug", content );
    }

    /** {@inheritDoc} */
    public void debug( CharSequence content, Throwable error )
    {
        print( "debug", content, error );
    }

    /** {@inheritDoc} */
    public void debug( Throwable error )
    {
        print( "debug", error );
    }

    /** {@inheritDoc} */
    public void info( CharSequence content )
    {
        print( "info", content );
    }

    /** {@inheritDoc} */
    public void info( CharSequence content, Throwable error )
    {
        print( "info", content, error );
    }

    /** {@inheritDoc} */
    public void info( Throwable error )
    {
        print( "info", error );
    }

    /** {@inheritDoc} */
    public void warn( CharSequence content )
    {
        print( "warn", content );
    }

    /** {@inheritDoc} */
    public void warn( CharSequence content, Throwable error )
    {
        print( "warn", content, error );
    }

    /** {@inheritDoc} */
    public void warn( Throwable error )
    {
        print( "warn", error );
    }

    /** {@inheritDoc} */
    public void error( CharSequence content )
    {
        System.err.println( "[error] " + content.toString() );
    }

    /** {@inheritDoc} */
    public void error( CharSequence content, Throwable error )
    {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter( sWriter );

        error.printStackTrace( pWriter );

        System.err.println( "[error] " + content.toString() + System.lineSeparator() + System.lineSeparator() + sWriter.toString() );
    }

    /**
     * @see Log#error(Throwable)
     */
    public void error( Throwable error )
    {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter( sWriter );

        error.printStackTrace( pWriter );

        System.err.println( "[error] " + sWriter.toString() );
    }

    /**
     * @see Log#isDebugEnabled()
     */
    public boolean isDebugEnabled()
    {
        // TODO: Not sure how best to set these for this implementation...
        return true;
    }

    /**
     * @see Log#isInfoEnabled()
     */
    public boolean isInfoEnabled()
    {
        return true;
    }

    /**
     * @see Log#isWarnEnabled()
     */
    public boolean isWarnEnabled()
    {
        return true;
    }

    /**
     * @see Log#isErrorEnabled()
     */
    public boolean isErrorEnabled()
    {
        return true;
    }

    private void print( String prefix, CharSequence content )
    {
        sb.append( "[" ).append( prefix ).append( "] " ).append( content.toString() ).append( System.lineSeparator() );
    }

    private void print( String prefix, Throwable error )
    {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter( sWriter );

        error.printStackTrace( pWriter );

        sb.append( "[" ).append( prefix ).append( "] " ).append( sWriter.toString() ).append( System.lineSeparator() );
    }

    private void print( String prefix, CharSequence content, Throwable error )
    {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter( sWriter );

        error.printStackTrace( pWriter );

        sb.append( "[" ).append( prefix ).append( "] " ).append( content.toString() ).append( System.lineSeparator() ).append( System.lineSeparator() ).append( sWriter.toString() ).append( System.lineSeparator() );
    }

    public String getContent()
    {
        return sb.toString();
    }
}
