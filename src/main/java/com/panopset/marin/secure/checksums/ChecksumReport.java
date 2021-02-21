package com.panopset.marin.secure.checksums;

import java.io.File;
import java.util.List;
import com.panopset.compat.Fileop;
import com.panopset.compat.Logop;
import com.panopset.compat.Nls;
import com.panopset.compat.Padop;
import com.panopset.compat.Stringop;
import com.panopset.util.ChecksumGenerator;
import com.panopset.util.rpg.TextProcessor;

public class ChecksumReport {

  private boolean firstTime = true;

  public void generateReport(File file, List<ChecksumType> types, TextProcessor textProcessor) {
    if (file == null) {
      Logop.warn(Nls.xlate("File is null, no checksum report generated."));
      return;
    }
    if (!file.exists()) {
      Logop.warn(
          String.join(":", Nls.xlate("File doesn't exist"), Fileop.getCanonicalPath(file)));
      return;
    }
    if (file.isDirectory()) {
      generateDirectoryReport(file, types, textProcessor);
    } else {
      generateFileReport(file, types, textProcessor);
    }
  }

  private Integer maxTitleLength;

  private int getMaxTitleSize(List<ChecksumType> types) {
    if (maxTitleLength == null) {
      int i = 0;
      for (ChecksumType cst : types) {
        if (cst.name().length() > i) {
          i = cst.name().length();
        }
      }
      maxTitleLength = i;
    }
    return maxTitleLength;
  }

  private void generateFileReport(File file, List<ChecksumType> types,
      TextProcessor textProcessor) {
    Logop.green(String.join(": ", Nls.xlate("Processing"), Fileop.getCanonicalPath(file)));
    if (types.size() > 1) {
      textProcessor.append(file.getName());
      textProcessor.append("\n");
    } else if (types.size() == 1) {
      if (firstTime) {
        textProcessor.append(types.get(0).name());
        textProcessor.append("\n");
        firstTime = false;
      }
    }
    for (ChecksumType cst : types) {
      if (types.size() > 1) {
        textProcessor.append(Padop.leftPad(cst.name(), getMaxTitleSize(types), ' '));
        textProcessor.append(":");
      }
      switch (cst) {
        case BYTES:
          textProcessor.append(ChecksumGenerator.byteCount(file));
          break;
        case MD5:
          textProcessor.append(ChecksumGenerator.md5(file));
          break;
        case SHA1:
          textProcessor.append(ChecksumGenerator.sha1(file));
          break;
        case SHA256:
          textProcessor.append(ChecksumGenerator.sha256(file));
          break;
        case SHA384:
          textProcessor.append(ChecksumGenerator.sha384(file));
          break;
        case SHA512:
          textProcessor.append(ChecksumGenerator.sha512(file));
          break;
        default:
          break;
      }
      if (types.size() > 1) {
        textProcessor.append("\n");
      } else {
        textProcessor.append(" ");
        textProcessor.append(file.getName());
      }
    }
    Logop.green(String.format("%s: %s", Nls.xlate("Completed"), Fileop.getCanonicalPath(file)));
  }

  private void generateDirectoryReport(File file, List<ChecksumType> types,
      TextProcessor textProcessor) {
    for (File f : file.listFiles()) {
      if (f.isFile()) {
        generateFileReport(f, types, textProcessor);
        textProcessor.append(Stringop.getEol());
      }
    }
    if (textProcessor.getText().isEmpty()) {
      Logop.warn(
          String.format(Stringop.CS, Nls.xlate("No files found in"), Fileop.getCanonicalPath(file)));
    } else {
      Logop.green(Nls.xlate("Completed."));
    }
  }
}
