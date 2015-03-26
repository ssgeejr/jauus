# JAUUS

http://jauus.sourceforge.net/

Java Application Update Utility System (jauus) is a simple, easy to configure update utility for any language or type of files. It can be configured for remote or network based updates. 


What is JAUUS (Java Application Update Utility Software)?
Short answer: Jauus is a software patching programs that works over the internet by making a single TCP connection to the host server. It works on any java supported platform for -ANY- language or type file; ASCII, binary, java, VB, C/C++ Perl it doesn't matter. The client/server see each file as objects and pass them over the network as such.

Long answer: Appending to the short answer, jauus works like this. A connection is made to the server, some handshaking and communication is done and the server looks up the requested application file structure. A global checksum is calculated as each files checksum is generated. This information is then passed to the client who does the exact same thing on his end. The master checksums are compared. If they do not match then each file is individually checked against the master list. When the file fails to validate or does not exist that file is then downloaded from the server. When the validation process is over the client checks the config file and determines if the application is to be started and if so what command to execute.

What's the inspiration? I had written an application for the support division that was constantly being changed for one ( I didn't remember to I thought I told you about that ) reason or another. That's not so bad, I'm paid to do that but what got under my skin was not everyone one is support would read the update emails and they'd wine about "it still doesn't sort by blah-blah". Anyways...I needed a program that they could run that would verify the files they are using and make sure they -always- had the latest version. So Jauus was brought to life.
	