package com.panopset.marin.sw;

import java.io.File;
import com.panopset.compat.Stringop;

public class Reports {

  public static final File CC_IVR_PROJECT = new File(
      String.join(Stringop.FILE_SEP, Stringop.USER_HOME, "apps", "workspaces", "ivrws", "cc-ivr"));


  public static final File REPORT_WAV_LIST =
      new File(String.join(Stringop.FILE_SEP, Stringop.USER_HOME, "a", "wavlist.txt"));
  public static final File REPORT_ALL_WAVS =
      new File(String.join(Stringop.FILE_SEP, Stringop.USER_HOME, "a", "allwavs.txt"));
  public static final File REPORT_JAVA_WAVS =
      new File(String.join(Stringop.FILE_SEP, Stringop.USER_HOME, "a", "alljava.txt"));


  public static final File REPORT_USAGE =
      new File(String.join(Stringop.FILE_SEP, Stringop.USER_HOME, "a", "usage.txt"));
}
