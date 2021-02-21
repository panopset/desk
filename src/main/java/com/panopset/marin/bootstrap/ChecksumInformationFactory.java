package com.panopset.marin.bootstrap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.panopset.compat.AppVersion;
import com.panopset.util.ChecksumGenerator;

public class ChecksumInformationFactory {

  public static List<ChecksumInformation> createList(File srcFile) {
    List<ChecksumInformation> list = new ArrayList<ChecksumInformation>();
    list.add(new ChecksumInformation("version", AppVersion.getVersion()));
    list.add(new ChecksumInformation("bytes", ChecksumGenerator.byteCount(srcFile)));
//    list.add(new ChecksumInformation("md5", ChecksumGenerator.md5(srcFile)));
//    list.add(new ChecksumInformation("sha1", ChecksumGenerator.sha1(srcFile)));
//    list.add(new ChecksumInformation("sha256", ChecksumGenerator.sha256(srcFile)));
//    list.add(new ChecksumInformation("sha384", ChecksumGenerator.sha384(srcFile)));
    list.add(new ChecksumInformation("sha512", ChecksumGenerator.sha512(srcFile)));
    return list;
  }

}
