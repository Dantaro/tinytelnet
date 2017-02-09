# Tiny Telnet
#### A tiny customizable telnet server
[![Build Status](https://travis-ci.org/Dantaro/tinytelnet.svg?branch=master)](https://travis-ci.org/Dantaro/tinytelnet)

###What is TinyTelnet?
TinyTelnet is a small, lightweight Telnet server designed to be customizable and easily deployed in a code base.  Building out custom actions is as easy as building a class, and instantiating the server is done through a simple builder.

### How do I use TinyTelnet in my project?
TinyTelnet consists of two parts: The Telnet server, and an "Action" framework to facilitate building and deploying your own functionality.

#### How to create a server
Create a server by first calling TinyTelnetBuilder::getInstance. After this you can either set various options, or simply call TinyTelnetBuilder::build. A Map<String, TelnetAction> can be passed to give functionality to your server (Where the String is the command used to invoke the action). Passing no telnet actions causes TinyTelnet to act as an echo server.

### How to create a TelnetAction
A telnet action can be created by implementing the TelnetAction class. There are two methods that must be implemented: TelnetAction::execute and TinyTelnet::getCommand. Each action should be a singleton.

#### TelnetAction::execute
The execute method performs the actual action you want to implement. The method takes in three inputs:
String userInput - The unaltered user input.
Map<String, Object> stateMap - A state map which is locked to a thread and passed between all TelnetAction objects.
Socket userSocket - The current socket, to facilitate expanded actions.

The return type is Optional<String>. When a value is present, it will be printed out to the socket, otherwise nothing will be printed.

#### TelnetAction::getCommand
The getCommand method simply returns the expected command to be used to invoke the action. For example, "echo" or "help".  Command arguments should be handled by TelnetAction::execute.
