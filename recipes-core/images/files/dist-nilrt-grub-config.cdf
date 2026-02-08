<?xml version="1.0"?>
<?CDF VERSION="7.0"?>
<INSTALLATION>
   <STACK NAME="{%systemlink-guid%}" TYPE="GOLD" OPKGSYSTEM="YES" VERSION="%version%">
      <TITLE>Linux RT System Image %release%</TITLE>
      <ABSTRACT>For use with: %lvversions% and SystemLink. Provides the software necessary to manage an NI Linux Real-Time target. This software enables SSH, so it is highly recommended that you change your admin password after installation.</ABSTRACT>
      <STACKITEM TYPE="REQUIRED" NAME="{%guid%}" VERSION="%version%" OLDESTCOMPATIBLEVERSION="%version%" TITLE="SystemLink Base Image"/>
   </STACK>

   <SOFTPKG NAME="{%guid%}" VERSION="%version%" OLDESTCOMPATIBLEVERSION="%version%" PROVIDESOS="YES" TYPE="HIDDEN">
      <TITLE>SystemLink Base Image</TITLE>
      <IMPLEMENTATION>
         <OS VALUE="%osvalue%"><OSVERSION VALUE="%osversion%"/></OS>
         <CODEBASE FILENAME="%filename%" TYPE="TAR"/>
      </IMPLEMENTATION>
   </SOFTPKG>
</INSTALLATION>
