/*************************************************************************
 Copyright (C) 2005  Steve Gee
 ioexcept@cox.net
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *************************************************************************/

package integrity.server;

import java.io.*;
import java.net.*;
import integrity.*;

public class IntegrityServer {
  private int SERVER_PORT = 1026;
  private TagReader tReader;
  private String configDir;
  private String applicationDir;
  private final String FILE_DIVIDER = System.getProperties().getProperty("file.separator");
  public IntegrityServer(String args[]){
    try {
      boolean shutdown = false;
      for(int loop = 0; loop < args.length;loop++){
        if(args[loop].equalsIgnoreCase("-shutdown")){
          shutdown = true;
          new ShutdownServer();
        }
      }
//----------- IF WE ARE NOT SHUTTING DOWN THE SERVER -----------
      if(!shutdown){
        boolean useDefinedConfig = false;
        String externalConfig = null;
        for( int loop = 0; loop < args.length; loop++){
          if(args[loop].equalsIgnoreCase("-c")) {
            externalConfig = args[++loop];
            System.out.println("Using external config file [" + externalConfig + "]");
            useDefinedConfig = true;
          }
        }
        if(useDefinedConfig){
          tReader = new TagReader(externalConfig);
        }else{
          tReader = new TagReader(this.getClass().getResourceAsStream("server.properties"));
        }

        SERVER_PORT = Integer.parseInt(tReader.getTagValue("server", "port"));
        configDir = tReader.getTagValue("server", "config-dir") + FILE_DIVIDER;
        applicationDir = tReader.getTagValue("server", "application-dir") + FILE_DIVIDER;

        startServer();
      }
    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }

    private void startServer() {
        try {
            ServerSocket sktServer = new ServerSocket(SERVER_PORT);
            System.out.println("Server Running on port " + SERVER_PORT + " of localhost");
            System.out.println("Server started : " + new java.util.Date().toString());
            IntegrityThread srvrThread = null;
            while (true) {
                srvrThread = new IntegrityThread(sktServer.accept(),configDir,applicationDir);
                srvrThread.start();
            } // end while
        } catch (Exception ioe) {
            System.out.println("################ JAUUSServer has thrown an exception ################");
            ioe.printStackTrace();
        } // end try-catch
    } // end startServer


  public static void main(String args[]){
    new IntegrityServer(args);
  }
}
