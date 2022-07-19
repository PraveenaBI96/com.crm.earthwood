package com.crm.erthwood.campaignsTest;

import org.testng.annotations.Test;

public class Parametrerized {

@Test
public void url() {
String url=System.getProperty("url");
System.out.println("hii");
}
@Test
public void name()
{
String name=System.getProperty("name");
System.out.println("name");
}}