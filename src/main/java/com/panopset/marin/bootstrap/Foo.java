package com.panopset.marin.bootstrap;

public class Foo {

  public static void main(String[] args) {
    System.out.println(VersionHelper.getAvailableVersion());
    System.out.println(PanopsetJar.generateChecksums("windows"));

  }

}
