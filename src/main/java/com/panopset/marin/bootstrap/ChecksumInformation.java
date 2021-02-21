package com.panopset.marin.bootstrap;

public class ChecksumInformation {

  public ChecksumInformation(String alg, String val) {
    this.alg = alg;
    this.val = val;
  }
  
  public ChecksumInformation() {}

  public String getAlg() {
    return alg;
  }
  public String getVal() {
    return val;
  }

  public void setAlg(String alg) {
    this.alg = alg;
  }

  public void setVal(String val) {
    this.val = val;
  }

  private String alg;
  private String val;

}
